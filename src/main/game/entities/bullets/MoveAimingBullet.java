package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.entities.player.Battle;

/**
 * Bullet that aims at the player
 * Calculates trajectory based on player's position
 */
public class MoveAimingBullet extends EnemyShot {
    private float x_ziki;  // Player's X position
    private float y_ziki;  // Player's Y position
    private float x_enemy; // Bullet's initial X position
    private float y_enemy; // Bullet's initial Y position
    private float distance; // Distance to player
    private float speed;   // Bullet speed

    /**
     * Constructor for aiming bullet
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param ziki Reference to player's battle object
     */
    public MoveAimingBullet(float x, float y, Battle ziki) {
        super(x, y);
        speed = 2.0F;
        x_ziki = ziki.x;
        y_ziki = ziki.y;
        x_enemy = x;
        y_enemy = y;
        
        // Calculate distance to player
        distance = (float) Math.sqrt(
            Math.pow(x_ziki + ziki.WIDTH / 2.0D - x_enemy, 2) +
            Math.pow(y_ziki - y_enemy, 2)
        );

        // Calculate velocity components
        if (distance != 0.0F) {
            vx = (float) (((x_ziki + ziki.WIDTH / 2.0D - x_enemy) / distance) * speed);
            vy = ((y_ziki - y_enemy) / distance) * speed;
        } else {
            vx = 0.0F;
            vy = speed;
        }
    }
} 