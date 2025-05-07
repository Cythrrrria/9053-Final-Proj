package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.entities.Role;
import java.awt.Image;

/**
 * Enemy's bullet class
 * Handles enemy bullet movement and collision detection
 */
public class EnemyBullet extends Role {
    protected float speed;
    protected int damage;
    protected float angle;

    /**
     * Constructor for EnemyBullet
     * @param x X coordinate
     * @param y Y coordinate
     * @param speed Bullet speed
     * @param damage Bullet damage
     * @param angle Bullet angle in radians
     * @param bulletImage Bullet's image
     */
    public EnemyBullet(float x, float y, float speed, int damage, float angle, Image bulletImage) {
        super(bulletImage);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.angle = angle;
    }

    /**
     * Update bullet position
     * Removes bullet if it goes off screen
     */
    public void move() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
        
        if (y - HEIGHT > (float) app.getHeight() || 
            x + WIDTH < 0.0F ||
            x - WIDTH > (float) app.getWidth() ||
            y + HEIGHT < 0.0F) {
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

    /**
     * Get bullet angle
     * @return Angle in radians
     */
    public float getAngle() {
        return angle;
    }
} 