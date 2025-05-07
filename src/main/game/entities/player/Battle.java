package com.ideabobo.game.leidian.entities.player;

import com.ideabobo.game.entities.enemy.Enemy;
import com.ideabobo.game.entities.enemy.Boss;
import com.ideabobo.game.entities.bullets.EnemyBullet;
import com.ideabobo.game.entities.effects.Burst;
import com.ideabobo.game.GamePanel;
import com.ideabobo.game.utils.InputHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * Main player battle class
 * Handles player movement, shooting and collision detection
 */
public class Battle extends Role {
    private static Battle battle = new Battle();
    private int tamaIntCount;
    private float speed;
    private float oldx;
    private float oldy;
    public static float vx;
    public static float vy;
    public static boolean keygo = false;
    private float tv[] = { -1F, -7F, 0.0F, -8F, 1.0F, -7F };
    public int power;
    public int powerMax;
    private boolean isShooting;      // Shooting state
    private int shootInterval;       // Shooting interval
    private int shootCount;          // Shooting counter
    private int powerLevel;          // Power level (affects shooting pattern)
    private int maxPowerLevel;       // Maximum power level
    private float x;
    private float y;
    private static final float WIDTH = 32.0F;
    private static final float HEIGHT = 32.0F;
    private GamePanel app;

    /**
     * Constructor
     * @param x Initial X position
     * @param y Initial Y position
     */
    public Battle(float x, float y) {
        super(x, y, 0.0F, 0.0F, com.ideabobo.game.GamePanel.playerImage);
        this.isShooting = false;
        this.shootInterval = 5;
        this.shootCount = 0;
        this.powerLevel = 1;
        this.maxPowerLevel = 4;
        speed = 3F;
        tamaIntCount = 0;
        power = 600;
        powerMax = power;
    }

    /**
     * Get singleton instance
     * @return Battle instance
     */
    public static Battle getInstance() {
        return battle;
    }

    /**
     * Update player position and handle shooting
     */
    public void move() {
        oldx = x;
        oldy = y;
        
        // Handle movement
        if (InputHandler.isLeftPressed()) {
            if (InputHandler.isSlowPressed())
                x -= speed / 4F;
            else
                x -= speed;
            if (x <= 0.0F)
                x = 0.0F;
        }
        if (InputHandler.isRightPressed()) {
            if (InputHandler.isSlowPressed())
                x += speed / 4F;
            else
                x += speed;
            if (x + WIDTH >= (float) app.getWidth())
                x = (float) app.getWidth() - WIDTH;
        }
        if (InputHandler.isDownPressed()) {
            if (InputHandler.isSlowPressed())
                y += speed / 4F;
            else
                y += speed;
            if (y + HEIGHT >= (float) app.getHeight())
                y = (float) app.getHeight() - HEIGHT;
        }
        if (InputHandler.isUpPressed()) {
            if (InputHandler.isSlowPressed())
                y -= speed / 4F;
            else
                y -= speed;
            if (y <= 0.0F)
                y = 0.0F;
        }
        
        // Calculate velocity
        vx = x - oldx;
        vy = y - oldy;
        
        // Handle shooting
        if (isShooting && shootCount <= 0) {
            shoot();
            shootCount = shootInterval;
        }
        if (shootCount > 0) {
            shootCount--;
        }
        
        // Handle special attack
        if (InputHandler.isSpecialPressed()) {
            if (!keygo) {
                GamePanel.skillCount--;
            }
            app.skillAnime();
            
            if (GamePanel.skillCount < 0) {
                GamePanel.skillCount = 0;
            }
            if (GamePanel.skillCount > 0) {
                for (int i = 0; i < GamePanel.list.size(); i++) {
                    Role chara1 = (Role) GamePanel.list.get(i);
                    if (!(chara1 instanceof Battle) && chara1.x > 0 && chara1.y > 0 && 
                        !(chara1 instanceof Boss)) {
                        GamePanel.list.remove(i);
                    } else if (chara1 instanceof Boss) {
                        Boss cb = (Boss)chara1;
                        cb.power -= 50;
                    }
                }
            }
            keygo = true;
        }
        
        if (!InputHandler.isSpecialPressed()) {
            keygo = false;
        }
    }

    /**
     * Handle key press events
     * @param keyCode Key code of pressed key
     */
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                vx = -5.0F;
                break;
            case KeyEvent.VK_RIGHT:
                vx = 5.0F;
                break;
            case KeyEvent.VK_UP:
                vy = -5.0F;
                break;
            case KeyEvent.VK_DOWN:
                vy = 5.0F;
                break;
            case KeyEvent.VK_Z:
                isShooting = true;
                break;
        }
    }

    /**
     * Handle key release events
     * @param keyCode Key code of released key
     */
    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                vx = 0.0F;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                vy = 0.0F;
                break;
            case KeyEvent.VK_Z:
                isShooting = false;
                break;
        }
    }

    /**
     * Shoot bullets based on power level
     */
    private void shoot() {
        switch (powerLevel) {
            case 1:
                // Single shot
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F, y, 0.0F, -8.0F));
                break;
            case 2:
                // Double shot
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F - 10F, y, -1.0F, -8.0F));
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F + 10F, y, 1.0F, -8.0F));
                break;
            case 3:
                // Triple shot
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F, y, 0.0F, -8.0F));
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F - 15F, y, -2.0F, -8.0F));
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F + 15F, y, 2.0F, -8.0F));
                break;
            case 4:
                // Quad shot
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F - 20F, y, -3.0F, -8.0F));
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F - 10F, y, -1.0F, -8.0F));
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F + 10F, y, 1.0F, -8.0F));
                GamePanel.addList(new BattleBasic(
                    x + WIDTH / 2.0F + 20F, y, 3.0F, -8.0F));
                break;
        }
    }

    /**
     * Increase power level
     */
    public void powerUp() {
        if (powerLevel < maxPowerLevel) {
            powerLevel++;
        }
    }

    /**
     * Get current power level
     * @return Power level
     */
    public int getPowerLevel() {
        return powerLevel;
    }

    /**
     * Get maximum power level
     * @return Maximum power level
     */
    public int getMaxPowerLevel() {
        return maxPowerLevel;
    }

    /**
     * Check collision with enemies and their projectiles
     * @param chara Character to check collision with
     * @return true if collision occurred
     */
    public boolean checkHit(Role chara) {
        if (chara instanceof Enemy || chara instanceof EnemyBullet) {
            if ((x + WIDTH) - 14F > chara.x && x + 14F < chara.x + chara.WIDTH &&
                (y + HEIGHT) - 12F > chara.y && y + 12F < chara.y + chara.HEIGHT) {
                chara.dead();
                power -= 50;
                if (power <= 0) {
                    dead();
                    GamePanel.burst = new Burst(x, y);
                }
                return true;
            }
        } else if (chara instanceof Boss) {
            if ((x + WIDTH) - 14F > chara.x + 50F && x + 14F < (chara.x + chara.WIDTH) - 50F &&
                (y + HEIGHT) - 12F > chara.y + 50F && y + 12F < (chara.y + chara.HEIGHT) - 80F) {
                power--;
                if (power <= 0) {
                    dead();
                    GamePanel.burst = new Burst(x, y);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Draw player's power bar
     * @param g Graphics context
     */
    public void drawPower(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(200, 20, 150, 15);
        g.setColor(Color.green);
        g.fillRect(201, 21, (int)((150D / (double)powerMax) * (double)power) - 1, 14);
    }

    /**
     * Draw special attack count
     * @param g Graphics context
     */
    public void drawSkillCount(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Special: " + GamePanel.skillCount, 20, 40);
    }

    // Getters and setters
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public float getWidth() { return WIDTH; }
    public float getHeight() { return HEIGHT; }
    public float getX() { return x; }
    public float getY() { return y; }
    public Image getImage() { return img; }
    public int getPower() { return power; }
    public int getPowerMax() { return powerMax; }
    public void setPower(int power) { this.power = power; }
} 