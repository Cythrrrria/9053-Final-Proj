package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.player.Battle;
import java.awt.Image;

/**
 * Bullet that continuously tracks the player
 * Updates its trajectory periodically to follow player movement
 */
public class MoveHomingBullet extends EnemyShot {
    private float speed;    // Bullet speed
    private int count;      // Counter for trajectory updates
    private float vx1;      // Temporary X velocity
    private float vy1;      // Temporary Y velocity
    private Battle ziki;    // Reference to player's battle object

    /**
     * Constructor for homing bullet
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param vx Initial X velocity
     * @param vy Initial Y velocity
     * @param ziki Reference to player's battle object
     * @param img Bullet image
     */
    public MoveHomingBullet(float x, float y, float vx, float vy, Battle ziki, Image img) {
        super(x, y, img);
        speed = 6F;
        count = 0;
        this.ziki = ziki;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Update bullet position and trajectory
     * Periodically recalculates path to player
     */
    public void move() {
        if (count > 0)
            count--;

        // Update trajectory every 50 frames
        if (count <= 0) {
            // Calculate distance to player
            float distance = (float) Math.sqrt(
                Math.pow(ziki.x + ziki.WIDTH / 2.0D - x, 2) +
                Math.pow(ziki.y - y, 2)
            );

            // Calculate new velocity components
            if (distance != 0.0F) {
                vx1 = (float) (((ziki.x + ziki.WIDTH / 2.0D - x) / distance) * speed);
                vy1 = ((ziki.y - y) / distance) * speed;
            } else {
                vx1 = 0.0F;
                vy1 = speed;
            }

            // Smoothly adjust current velocity towards new direction
            float currentSpeed = (float) Math.sqrt(vx * vx + vy * vy);
            vx = (vx / currentSpeed) * speed;
            vy = (vy / currentSpeed) * speed;
            vx = (float) (vx * 0.2D + vx1 * 0.8D);
            vy = (float) (vy * 0.2D + vy1 * 0.8D);
            
            count = 50;
        }

        // Update position
        x += vx;
        y += vy;

        // Check if bullet is out of screen
        if (x + WIDTH < 0.0F || x > (float) app.getWidth() || 
            y + HEIGHT < 0.0F || y > (float) app.getHeight())
            dead();
    }
} 