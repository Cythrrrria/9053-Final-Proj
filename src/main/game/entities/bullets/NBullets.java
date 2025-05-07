package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.player.Battle;

/**
 * Bullet that splits into multiple projectiles in a fan pattern
 * Creates a spread of bullets at specified angles
 */
public class NBullets extends EnemyShot {
    private float[] vxNWay;  // Array of X velocities for each bullet
    private float[] vyNWay;  // Array of Y velocities for each bullet
    private float theta;     // Angle between bullets
    private int n;           // Number of bullets
    private float vx1;       // Base X velocity
    private float vy1;       // Base Y velocity

    /**
     * Constructor for N-way bullets
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param vx0 Initial X velocity
     * @param vy0 Initial Y velocity
     * @param ziki Reference to player's battle object
     */
    public NBullets(float x, float y, float vx0, float vy0, Battle ziki) {
        super(x, y);
        theta = 15F;  // Angle between bullets in degrees
        n = 12;       // Number of bullets
        
        // Initialize velocity arrays
        vxNWay = new float[n];
        vyNWay = new float[n];
        
        // Calculate angle step in radians
        float rad_step = (float) (Math.PI / 180.0D * theta);
        float rad = (float) (((-n / 2) + 0.5D) * rad_step);
        
        // Create bullets in a fan pattern
        for (int i = 0; i < n; i++) {
            float c = (float) Math.cos(rad);
            float s = (float) Math.sin(rad);
            
            // Calculate velocity components for each bullet
            vxNWay[i] = vx0 * c - vy0 * s;
            vyNWay[i] = vx0 * s + vy0 * c;
            
            // Add new bullet to game
            GamePanel.addList(new EnemyShot(x, y, vxNWay[i], vyNWay[i]));
            
            rad += rad_step;
        }

        // Set initial velocity for this bullet
        vx = vxNWay[0];
        vy = vyNWay[0];
    }
} 