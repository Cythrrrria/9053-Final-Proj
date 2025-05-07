package com.ideabobo.game.entities.player;

import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.entities.GameObject;
import com.ideabobo.game.utils.ResourceLoader;

import java.awt.Graphics;
import java.awt.Image;

public class PlayerBeam extends GameObject {
    private float speedX;
    private float speedY;
    private int damage;
    private int width;
    private int height;
    
    public PlayerBeam(float x, float y, float speedX, float speedY) {
        super(ResourceLoader.getInstance().loadImage("image/beam2.gif"));
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.damage = GameConstants.BULLET_DAMAGE * 2;
        this.width = 20;
        this.height = 40;
    }
    
    @Override
    public void update() {
        x += speedX;
        y += speedY;
        
        if (y < -height || y > GameConstants.WINDOW_HEIGHT ||
            x < -width || x > GameConstants.WINDOW_WIDTH) {
            destroy();
        }
    }
    
    @Override
    public void render(Graphics g) {
        if (!isDestroyed) {
            g.drawImage(image, (int)x - width/2, (int)y, width, height, null);
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