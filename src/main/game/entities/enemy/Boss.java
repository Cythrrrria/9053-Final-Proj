package com.ideabobo.game.leidian.entities.enemy;

import com.ideabobo.game.GamePanel;
import com.ideabobo.game.entities.player.Role;
import com.ideabobo.game.entities.bullets.BattleShot;
import com.ideabobo.game.entities.player.Battle;
import com.ideabobo.game.entities.effects.BigBurst;
import java.awt.Image;

/**
 * Base class for all boss enemies
 * Extends Role class and provides basic boss functionality
 */
public abstract class Boss extends Role {
    protected int bulletIntervalCount;  // Bullet firing interval counter
    protected Battle battle;            // Player reference
    protected int power;                // Health points
    protected int counter;              // General counter
    protected float vx;                 // X velocity
    protected float vy;                 // Y velocity

    /**
     * Constructor
     * @param battle Player reference
     * @param bossImage Boss image
     */
    public Boss(Battle battle, Image bossImage) {
        super(bossImage);
        this.battle = battle;
        bulletIntervalCount = 0;
        counter = 0;
    }

    /**
     * Movement method to be implemented by subclasses
     */
    public abstract void move();

    /**
     * Check if boss is out of screen bounds
     */
    public void checkOutOfScreen() {
        if (y - 100F > (float) app.getHeight() || x + WIDTH + 100F < 0.0F
            || x - 100F > (float) app.getWidth()
            || y + HEIGHT + 100F < 0.0F)
            dead();
    }

    /**
     * Check for collisions with player shots
     * @param chara Character to check collision with
     * @return true if collision occurred
     */
    protected boolean checkHit(Role chara) {
        if ((chara instanceof BattleShot) && super.checkHit(chara)) {
            chara.dead();
            power--;
            if (power <= 0) {
                GamePanel.bigBurst = new com.ideabobo.game.leidian.entities.effects.BigBurst(x, y);
                dead();
            }
            return true;
        } else {
            return false;
        }
    }
} 