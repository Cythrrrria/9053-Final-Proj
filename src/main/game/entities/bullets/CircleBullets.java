package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;

/**
 * Bullet that creates a circular pattern of projectiles
 * Generates bullets in a full 360-degree spread
 */
public class CircleBullets extends EnemyShot {
    private float speed;           // Bullet speed
    private float[] vxCircle;      // Array of X velocities for circle pattern
    private float[] vyCircle;      // Array of Y velocities for circle pattern
    private int tamaNum;           // Number of bullets in circle

    /**
     * Constructor for circle bullets
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param flag If true, starts from 0 degrees, otherwise starts from half step
     */
    public CircleBullets(float x, float y, boolean flag) {
        super(x, y);
        speed = 1.0F;
        tamaNum = 24;  // Number of bullets in circle
        
        // Initialize velocity arrays
        vxCircle = new float[tamaNum];
        vyCircle = new float[tamaNum];
        
        // Calculate angle step in radians
        float rad_step = (float) (2.0D * Math.PI / tamaNum);
        float rad;
        
        // Set initial angle based on flag
        if (flag)
            rad = 0.0F;
        else
            rad = rad_step / 2.0F;
        
        // Create bullets in a circle pattern
        for (int i = 0; i < tamaNum; i++) {
            // Calculate velocity components for each bullet
            vxCircle[i] = (float) (Math.cos(rad) * speed);
            vyCircle[i] = (float) (Math.sin(rad) * speed);
            
            // Add new bullet to game
            GamePanel.addList(new EnemyShot(x, y, vxCircle[i], vyCircle[i]));
            
            rad += rad_step;
        }

        // Set initial velocity for this bullet
        vx = vxCircle[0];
        vy = vyCircle[0];
    }
} 