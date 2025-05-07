package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.entities.player.Hero;
import java.awt.Image;

/**
 * Base class for player bullets
 * Inherits from Hero class, providing basic bullet functionality
 */
public class BattleShot extends Hero {
    /**
     * Constructor
     * @param x Initial X coordinate
     * @param y Initial Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     * @param image Bullet image
     */
    public BattleShot(float x, float y, float vx, float vy, Image image) {
        super(x, y, vx, vy, image);
    }
} 