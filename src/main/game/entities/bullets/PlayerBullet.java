package com.ideabobo.game.leidian.entities.bullets;

import com.ideabobo.game.entities.player.Role;
import java.awt.Image;

/**
 * Player's bullet class
 * Handles bullet movement and collision detection
 */
public class PlayerBullet extends Role {
    protected float speed;
    protected int damage;

    /**
     * Constructor for PlayerBullet
     * @param x X coordinate
     * @param y Y coordinate
     * @param speed Bullet speed
     * @param damage Bullet damage
     * @param bulletImage Bullet's image
     */
    public PlayerBullet(float x, float y, float speed, int damage, Image bulletImage) {
        super(bulletImage);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    /**
     * Update bullet position
     * Removes bullet if it goes off screen
     */
    public void move() {
        y -= speed;
        if (y + HEIGHT < 0.0F) {
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
     * Get bullet damage
     * @return Damage value
     */
    public int getDamage() {
        return damage;
    }
} 