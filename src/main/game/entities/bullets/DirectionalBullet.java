package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.entities.player.Battle;

/**
 * Bullet that moves in a specific direction
 * Uses angle in degrees to determine trajectory
 */
public class DirectionalBullet extends EnemyShot {
    private float speed;  // Bullet speed

    /**
     * Constructor for directional bullet
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param theta Angle in degrees (0 = right, 90 = down)
     * @param ziki Reference to player's battle object
     */
    public DirectionalBullet(float x, float y, float theta, Battle ziki) {
        super(x, y);
        speed = 2.0F;
        
        // Convert angle to radians and calculate velocity components
        double rad = Math.PI / 180.0D * theta;
        vx = (float) (Math.cos(rad) * speed);
        vy = (float) (Math.sin(rad) * speed);
    }
} 