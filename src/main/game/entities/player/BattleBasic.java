package com.ideabobo.game.entities.player;

import com.ideabobo.game.entities.bullets.PlayerBullet;
import java.awt.Image;

/**
 * Basic bullet class for player's normal attack
 */
public class BattleBasic extends PlayerBullet {
    /**
     * Constructor for BattleBasic
     * @param x X coordinate
     * @param y Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     */
    public BattleBasic(float x, float y, float vx, float vy) {
        super(x, y, 8.0F, 1, GamePanel.bulletImage);
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Update bullet position
     * Removes bullet if it goes off screen
     */
    public void move() {
        x += vx;
        y += vy;
        if (y + HEIGHT < 0.0F) {
            dead();
        }
    }
} 