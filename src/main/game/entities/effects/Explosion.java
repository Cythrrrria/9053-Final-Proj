package com.ideabobo.game.leidian.entities.effects;

import com.ideabobo.game.entities.player.Role;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Explosion effect class
 * Handles explosion animation and rendering
 */
public class Explosion extends Role {
    protected Image[] frames;
    protected int currentFrame;
    protected int maxFrame;
    protected int frameDelay;
    protected int frameCounter;

    /**
     * Constructor for Explosion
     * @param x X coordinate
     * @param y Y coordinate
     * @param frames Array of explosion animation frames
     * @param frameDelay Delay between frames
     */
    public Explosion(float x, float y, Image[] frames, int frameDelay) {
        super(frames[0]);
        this.x = x;
        this.y = y;
        this.frames = frames;
        this.maxFrame = frames.length;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.frameCounter = 0;
    }

    /**
     * Update explosion animation
     * Removes explosion when animation completes
     */
    public void update() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++;
            if (currentFrame >= maxFrame) {
                dead();
            } else {
                img = frames[currentFrame];
            }
        }
    }

    /**
     * Render explosion effect
     * @param g Graphics context
     */
    public void render(Graphics g) {
        if (!isDead) {
            g.drawImage(img, (int)x, (int)y, null);
        }
    }

    /**
     * Check collision with other characters
     * @param chara Character to check collision with
     * @return false as explosions don't check collisions
     */
    public boolean checkHit(Role chara) {
        return false;
    }
} 