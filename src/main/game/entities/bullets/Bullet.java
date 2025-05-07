package com.ideabobo.game.leidian.entities.bullets;

import com.ideabobo.game.core.GameObject;
import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.utils.ResourceLoader;

import java.awt.Graphics;
import java.awt.Image;

public class Bullet extends GameObject {
    private float speed;
    private int damage;
    
    public Bullet(Image image, float x, float y) {
        super(image);
        this.x = x;
        this.y = y;
        this.speed = GameConstants.BULLET_SPEED;
        this.damage = GameConstants.BULLET_DAMAGE;
    }
    
    @Override
    public void update() {
        y -= speed;
        if (y < -height) {
            destroy();
        }
    }
    
    @Override
    public void render(Graphics g) {
        if (!isDestroyed) {
            g.drawImage(image, (int)x, (int)y, null);
        }
    }
    
    @Override
    public boolean checkCollision(GameObject other) {
        if (isDestroyed) return false;
        return checkBounds(other);
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }
} 