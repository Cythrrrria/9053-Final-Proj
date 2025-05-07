package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;

/**
 * Basic player bullet class
 * Used for player's basic attack
 */
public class BattleBasic extends BattleShot {
    /**
     * Constructor
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     */
    public BattleBasic(float x, float y, float vx, float vy) {
        super(x, y, vx, vy, GamePanel.flagImage);
    }
} 