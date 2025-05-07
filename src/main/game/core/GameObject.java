package com.ideabobo.game.leidian.core;

import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected Image image;
    protected boolean isDestroyed;
    
    public GameObject(Image image) {
        this.image = image;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.isDestroyed = false;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    public abstract boolean checkCollision(GameObject other);
    
    public void destroy() {
        isDestroyed = true;
    }
    
    public boolean isDestroyed() {
        return isDestroyed;
    }
    
    protected boolean checkBounds(GameObject other) {
        return (x + width) - 14F > other.x && 
               x + 14F < other.x + other.width &&
               (y + height) - 12F > other.y &&
               y + 12F < other.y + other.height;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getWidth() {
        return width;
    }
    
    public float getHeight() {
        return height;
    }
} 