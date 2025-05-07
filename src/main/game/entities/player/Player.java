package com.ideabobo.game.entities.player;

import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.entities.effects.Explosion;
import com.ideabobo.game.entities.enemy.Enemy;
import com.ideabobo.game.entities.enemy.EnemyBullet;
import com.ideabobo.game.entities.boss.Boss;
import com.ideabobo.game.utils.InputHandler;
import com.ideabobo.game.utils.ResourceLoader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Player extends GameObject {
    private static Player instance;
    private int bulletCooldown;
    private float speed;
    private float oldX;
    private float oldY;
    private float[] bulletPattern = { -1F, -7F, 0.0F, -8F, 1.0F, -7F };
    private int power;
    private int powerMax;
    private boolean specialAttackUsed;

    public Player() {
        super(ResourceLoader.loadImage("image/this.gif"));
        speed = GameConstants.PLAYER_SPEED;
        bulletCooldown = 0;
        x = (GameConstants.WINDOW_WIDTH - width) / 2.0F;
        y = GameConstants.WINDOW_HEIGHT - height * 2.0F;
        power = GameConstants.PLAYER_INITIAL_POWER;
        powerMax = power;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    @Override
    public void update() {
        oldX = x;
        oldY = y;
        
        // Movement
        if (InputHandler.left) {
            float moveSpeed = InputHandler.slow ? speed / 4 : speed;
            x -= moveSpeed;
            if (x < 0) x = 0;
        }
        if (InputHandler.right) {
            float moveSpeed = InputHandler.slow ? speed / 4 : speed;
            x += moveSpeed;
            if (x + width > GameConstants.WINDOW_WIDTH) {
                x = GameConstants.WINDOW_WIDTH - width;
            }
        }
        if (InputHandler.up) {
            float moveSpeed = InputHandler.slow ? speed / 4 : speed;
            y -= moveSpeed;
            if (y < 0) y = 0;
        }
        if (InputHandler.down) {
            float moveSpeed = InputHandler.slow ? speed / 4 : speed;
            y += moveSpeed;
            if (y + height > GameConstants.WINDOW_HEIGHT) {
                y = GameConstants.WINDOW_HEIGHT - height;
            }
        }

        // Shooting
        if (bulletCooldown > 0) {
            bulletCooldown--;
        }
        
        if (InputHandler.shoot && bulletCooldown <= 0) {
            for (int i = 0; i < bulletPattern.length; i += 2) {
                GameManager.addEntity(new PlayerBullet(
                    x + width / 2.0F, 
                    y, 
                    bulletPattern[i],
                    bulletPattern[i + 1]
                ));
            }
            bulletCooldown = 8;
        }
        
        if (InputHandler.beam && !InputHandler.shoot && bulletCooldown <= 0) {
            GameManager.addEntity(new PlayerBeam(
                x + width / 2.0F,
                y,
                0.0F,
                -8F
            ));
            bulletCooldown = 2;
        }

        // Special attack
        if (InputHandler.special) {
            if (!specialAttackUsed) {
                GameManager.useSpecialAttack();
            }
            GameManager.skillAnimation();
            specialAttackUsed = true;
        } else {
            specialAttackUsed = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
        drawPowerBar(g);
        drawSpecialAttackCount(g);
    }

    public void drawPowerBar(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(380, 450, 50, 15);
        g.setColor(Color.RED);
        g.fillRect(381, 451,
            (int)((50.0 / powerMax) * power) - 1,
            14);
    }

    public void drawSpecialAttackCount(Graphics g) {
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Full Screen Attack: " + GameManager.getSpecialAttackCount(), 0, 450);
    }

    @Override
    public boolean checkCollision(GameObject other) {
        if (other instanceof Enemy || other instanceof EnemyBullet) {
            if (checkBounds(other)) {
                other.destroy();
                if (other instanceof EnemyBullet) {
                    power -= GameConstants.ENEMY_BEAM_DAMAGE;
                }
                power -= GameConstants.ENEMY_BASIC_DAMAGE;
                if (power <= 0) {
                    destroy();
                    GameManager.addEffect(new Explosion(x, y));
                }
                return true;
            }
        } else if (other instanceof Boss) {
            if (checkBossCollision((Boss)other)) {
                power -= GameConstants.ENEMY_BEAM_DAMAGE;
                if (power <= 0) {
                    destroy();
                    GameManager.addEffect(new Explosion(x, y));
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkBossCollision(Boss boss) {
        return (x + width) - 14F > boss.getX() + 50F &&
               x + 14F < (boss.getX() + boss.getWidth()) - 50F &&
               (y + height) - 12F > boss.getY() + 50F &&
               y + 12F < (boss.getY() + boss.getHeight()) - 80F;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public int getPowerMax() {
        return powerMax;
    }
} 