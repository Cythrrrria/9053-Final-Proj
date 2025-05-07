package com.ideabobo.game.entities;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Base class for all game entities
 * Provides basic functionality for game objects
 */
public class Role {
    protected float x;
    protected float y;
    protected float vx;
    protected float vy;
    protected Image image;
    protected boolean isDestroyed;

    /**
     * Constructor
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     * @param image Entity image
     */
    public Role(float x, float y, float vx, float vy, Image image) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.image = image;
        this.isDestroyed = false;
    }

    /**
     * Update entity position
     */
    public void move() {
        x += vx;
        y += vy;
    }

    /**
     * Render entity
     * @param g Graphics context
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    /**
     * Check if entity is destroyed
     * @return true if destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Mark entity as destroyed
     */
    public void destroy() {
        isDestroyed = true;
    }
}
