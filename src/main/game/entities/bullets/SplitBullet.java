package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.player.Battle;

/**
 * Bullet that splits into multiple projectiles after traveling a certain distance
 * Initially moves towards player, then splits into N-way bullets
 */
public class SplitBullet extends EnemyShot {
    private float x_ziki;    // Player's X position
    private float y_ziki;    // Player's Y position
    private float x_enemy;   // Bullet's initial X position
    private float y_enemy;   // Bullet's initial Y position
    private float distance;  // Distance to player
    private float speed;     // Bullet speed
    private Battle ziki;     // Reference to player's battle object
    private int counter;     // Counter for split timing

    /**
     * Constructor for split bullet
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param ziki Reference to player's battle object
     */
    public SplitBullet(float x, float y, Battle ziki) {
        super(x, y, GamePanel.splitBulletImage);
        speed = 3F;
        x_ziki = ziki.x;
        y_ziki = ziki.y;
        x_enemy = x;
        y_enemy = y;
        
        // Calculate distance to player
        distance = (float) Math.sqrt(
            Math.pow(x_ziki - x_enemy, 2) +
            Math.pow(y_ziki - y_enemy, 2)
        );

        // Calculate initial velocity components
        if (distance != 0.0F) {
            vx = ((x_ziki - x_enemy) / distance) * speed;
            vy = ((y_ziki - y_enemy) / distance) * speed;
        } else {
            vx = 0.0F;
            vy = speed;
        }
        
        counter = 0;
    }

    /**
     * Update bullet position and handle splitting
     * Splits into N-way bullets after 50 frames
     */
    public void move() {
        x += vx;
        y += vy;
        
        // Split into N-way bullets after 50 frames
        if (counter == 50) {
            GamePanel.addList(new NBullets(x, y, vx / 2.0F, vy / 2.0F, ziki));
            dead();
        }
        
        // Check if bullet is out of screen
        if (x + WIDTH < 0.0F || x > (float) app.getWidth() || 
            y + HEIGHT < 0.0F || y > (float) app.getHeight())
            dead();
            
        counter++;
    }
} 