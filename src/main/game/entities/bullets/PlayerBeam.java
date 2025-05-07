package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.entities.Role;
import java.awt.Image;

/**
 * Player's beam weapon class
 * A powerful beam that can damage multiple enemies
 */
public class PlayerBeam extends Role {
    protected float speed;
    protected int damage;
    protected int duration;
    protected int currentDuration;

    /**
     * Constructor for PlayerBeam
     * @param x X coordinate
     * @param y Y coordinate
     * @param speed Beam speed
     * @param damage Beam damage
     * @param duration Beam duration in frames
     * @param beamImage Beam's image
     */
    public PlayerBeam(float x, float y, float speed, int damage, int duration, Image beamImage) {
        super(beamImage);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.duration = duration;
        this.currentDuration = 0;
    }

    /**
     * Update beam position and duration
     * Removes beam if duration expires or it goes off screen
     */
    public void move() {
        y -= speed;
        currentDuration++;
        
        if (y + HEIGHT < 0.0F || currentDuration >= duration) {
            dead();
        }
    }

    /**
     * Check collision with other characters
     * @param chara Character to check collision with
     * @return true if collision occurred
     */
    public boolean checkHit(Role chara) {
        return super.checkHit(chara);
    }

    /**
     * Get beam damage
     * @return Damage value
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Get remaining duration
     * @return Frames remaining
     */
    public int getRemainingDuration() {
        return duration - currentDuration;
    }
} 