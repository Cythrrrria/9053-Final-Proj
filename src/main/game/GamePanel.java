package com.ideabobo.game.leidian;

import com.ideabobo.game.entities.Role;
import com.ideabobo.game.entities.player.Battle;
import com.ideabobo.game.entities.enemy.EnemyManager;
import com.ideabobo.game.entities.enemy.BossManager;
import com.ideabobo.game.utils.ResourceLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Main game panel class
 * Handles game loop, rendering, input and game state management
 */
public class GamePanel extends JPanel implements KeyListener, Runnable {
    // Game constants
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FPS = 60;
    
    // Game state
    private boolean isRunning;
    private boolean isPaused;
    private int gameState;  // 0: Menu, 1: Playing, 2: Game Over
    
    // Game objects
    private Battle player;
    private EnemyManager enemyManager;
    private BossManager bossManager;
    private static List<Role> gameObjects;  // Made static
    
    // Resources
    public static Image playerImage;
    public static Image backgroundImage;
    public static Image menuImage;
    public static Image gameOverImage;
    
    // Game stats
    private int score;
    private int highScore;
    private int level;
    private int skillCount;
    
    /**
     * Constructor
     * Initializes game components and loads resources
     */
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        // Initialize game objects
        gameObjects = new ArrayList<>();
        player = new Battle(WIDTH / 2, HEIGHT - 100);
        enemyManager = new EnemyManager(this, player);
        bossManager = new BossManager(this, player);
        
        // Load resources
        try {
            loadResources();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        // Initialize game state
        gameState = 0;
        score = 0;
        highScore = 0;
        level = 1;
        skillCount = 3;
    }
    
    /**
     * Load game resources
     * @throws Exception if resource loading fails
     */
    private void loadResources() throws Exception {
        playerImage = ResourceLoader.loadImage("image/this.gif");
        backgroundImage = ResourceLoader.loadImage("image/background.png");
        menuImage = ResourceLoader.loadImage("image/menu.png");
        gameOverImage = ResourceLoader.loadImage("image/gameover.png");
    }
    
    /**
     * Start game loop
     */
    public void startGame() {
        if (!isRunning) {
            isRunning = true;
            new Thread(this).start();
        }
    }
    
    /**
     * Main game loop
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerFrame = 1000000000.0 / FPS;
        
        while (isRunning) {
            long now = System.nanoTime();
            double delta = (now - lastTime) / nsPerFrame;
            
            if (delta >= 1) {
                if (!isPaused) {
                    update();
                }
                repaint();
                lastTime = now;
            }
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Update game state
     */
    private void update() {
        if (gameState == 1) {
            // Update player
            player.move();
            
            // Update enemies
            enemyManager.update();
            
            // Update boss if in boss stage
            if (bossManager.isBossStage()) {
                bossManager.update();
            }
            
            // Update all game objects
            for (int i = gameObjects.size() - 1; i >= 0; i--) {
                Role obj = gameObjects.get(i);
                obj.move();
                
                // Check collisions
                if (obj.checkHit(player)) {
                    if (obj instanceof Battle) {
                        continue;
                    }
                    gameObjects.remove(i);
                }
            }
            
            // Check level progression
            if (enemyManager.isLevelComplete() && !bossManager.isBossStage()) {
                level++;
                if (level % 3 == 0) {
                    bossManager.startBossStage();
                } else {
                    enemyManager.nextLevel();
                }
            }
        }
    }
    
    /**
     * Render game
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background
        g2d.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);
        
        switch (gameState) {
            case 0:  // Menu
                drawMenu(g2d);
                break;
            case 1:  // Playing
                drawGame(g2d);
                break;
            case 2:  // Game Over
                drawGameOver(g2d);
                break;
        }
    }
    
    /**
     * Draw menu screen
     */
    private void drawMenu(Graphics2D g) {
        g.drawImage(menuImage, 0, 0, WIDTH, HEIGHT, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Press SPACE to Start", WIDTH / 2 - 100, HEIGHT / 2);
    }
    
    /**
     * Draw game screen
     */
    private void drawGame(Graphics2D g) {
        // Draw game objects
        for (Role obj : gameObjects) {
            obj.draw(g);
        }
        
        // Draw player
        player.draw(g);
        
        // Draw HUD
        drawHUD(g);
    }
    
    /**
     * Draw game over screen
     */
    private void drawGameOver(Graphics2D g) {
        g.drawImage(gameOverImage, 0, 0, WIDTH, HEIGHT, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Game Over", WIDTH / 2 - 60, HEIGHT / 2 - 50);
        g.drawString("Score: " + score, WIDTH / 2 - 60, HEIGHT / 2);
        g.drawString("Press SPACE to Restart", WIDTH / 2 - 120, HEIGHT / 2 + 50);
    }
    
    /**
     * Draw heads-up display
     */
    private void drawHUD(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Level: " + level, 20, 60);
        g.drawString("Skills: " + skillCount, 20, 90);
        
        // Draw player's power bar
        player.drawPower(g);
    }
    
    /**
     * Add game object to the game
     */
    public static void addList(Role obj) {
        if (gameObjects == null) {
            gameObjects = new ArrayList<>();
        }
        gameObjects.add(obj);
    }
    
    /**
     * Remove game object from the game
     */
    public static void removeList(Role obj) {
        if (gameObjects != null) {
            gameObjects.remove(obj);
        }
    }
    
    // KeyListener implementation
    @Override
    public void keyPressed(KeyEvent e) {
        if (gameState == 1) {
            player.keyPressed(e.getKeyCode());
            
            if (e.getKeyCode() == KeyEvent.VK_P) {
                isPaused = !isPaused;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameState == 0) {
                gameState = 1;
            } else if (gameState == 2) {
                resetGame();
                gameState = 1;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (gameState == 1) {
            player.keyReleased(e.getKeyCode());
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
    
    /**
     * Reset game state
     */
    private void resetGame() {
        score = 0;
        level = 1;
        skillCount = 3;
        gameObjects.clear();
        player = new Battle(WIDTH / 2, HEIGHT - 100);
        enemyManager.reset();
        bossManager.reset();
    }
    
    // Getters and setters
    public int getScore() { return score; }
    public void addScore(int points) { score += points; }
    public int getLevel() { return level; }
    public int getSkillCount() { return skillCount; }
    public void setSkillCount(int count) { skillCount = count; }
    public Battle getPlayer() { return player; }
} 