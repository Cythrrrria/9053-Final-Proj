package com.ideabobo.game.entities.player;

import com.ideabobo.game.entities.bullets.PlayerBeam;
import java.awt.Image;

/**
 * Beam weapon class for player's special attack
 */
public class BattleBeam extends PlayerBeam {
    /**
     * Constructor for BattleBeam
     * @param x X coordinate
     * @param y Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     */
    public BattleBeam(float x, float y, float vx, float vy) {
        super(x, y, 8.0F, 5, 30, GamePanel.beamImage);
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Update beam position
     * Removes beam if it goes off screen or duration expires
     */
    public void move() {
        x += vx;
        y += vy;
        currentDuration++;
        
        if (y + HEIGHT < 0.0F || currentDuration >= duration) {
            dead();
        }
    }
} 