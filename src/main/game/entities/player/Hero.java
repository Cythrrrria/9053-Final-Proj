package com.ideabobo.game.leidian.entities.player;

import com.ideabobo.game.GamePanel;
import com.ideabobo.game.entities.Role;
import java.awt.Image;

/**
 * Base class for player characters
 * Extends Role class and provides basic player functionality
 */
public class Hero extends Role {
    protected float vx;          // X velocity
    protected float vy;          // Y velocity
    protected int bulletCount;   // Bullet firing counter
    protected int power;         // Health points
    protected int score;         // Player score
    protected boolean isInvincible;  // Invincibility state
    protected int invincibleTime;    // Invincibility duration

    /**
     * Constructor
     * @param x Initial X position
     * @param y Initial Y position
     * @param vx Initial X velocity
     * @param vy Initial Y velocity
     * @param image Player image
     */
    public Hero(float x, float y, float vx, float vy, Image image) {
        super(image);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.bulletCount = 0;
        this.power = 3;
        this.score = 0;
        this.isInvincible = false;
        this.invincibleTime = 0;
    }

    /**
     * Update player position and state
     */
    public void move() {
        x += vx;
        y += vy;

        // Keep player within screen bounds
        if (x < 0) x = 0;
        if (x + WIDTH > app.getWidth()) x = app.getWidth() - WIDTH;
        if (y < 0) y = 0;
        if (y + HEIGHT > app.getHeight()) y = app.getHeight() - HEIGHT;

        // Update invincibility state
        if (isInvincible) {
            invincibleTime--;
            if (invincibleTime <= 0) {
                isInvincible = false;
            }
        }

        // Update bullet counter
        if (bulletCount > 0) {
            bulletCount--;
        }
    }

    /**
     * Set player velocity
     * @param vx X velocity
     * @param vy Y velocity
     */
    public void setVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Take damage from enemy
     */
    public void takeDamage() {
        if (!isInvincible) {
            power--;
            if (power <= 0) {
                dead();
            } else {
                // Set temporary invincibility
                isInvincible = true;
                invincibleTime = 60;  // 1 second at 60 FPS
            }
        }
    }

    /**
     * Add score
     * @param points Points to add
     */
    public void addScore(int points) {
        score += points;
    }

    /**
     * Get player score
     * @return Current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get player power (health)
     * @return Current power
     */
    public int getPower() {
        return power;
    }

    /**
     * Check if player is invincible
     * @return Invincibility state
     */
    public boolean isInvincible() {
        return isInvincible;
    }

    /**
     * Check collision with other characters
     * @param chara Character to check collision with
     * @return false as hero doesn't check collisions
     */
    public boolean checkHit(Role chara) {
        return false;
    }
} 