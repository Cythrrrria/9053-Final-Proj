package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.player.Hero;
import java.awt.Image;

/**
 * Base class for all enemy bullets
 * Extends Hero class to inherit basic movement and collision detection
 */
public class EnemyShot extends Hero {
    /**
     * Constructor for basic enemy shot
     * @param x X coordinate
     * @param y Y coordinate
     */
    public EnemyShot(float x, float y) {
        super(x, y, GamePanel.enemyTamaImage);
    }

    /**
     * Constructor for enemy shot with velocity
     * @param x X coordinate
     * @param y Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     */
    public EnemyShot(float x, float y, float vx, float vy) {
        super(x, y, vx, vy, GamePanel.enemyTamaImage);
    }

    /**
     * Constructor for enemy shot with custom image
     * @param x X coordinate
     * @param y Y coordinate
     * @param vx X velocity
     * @param vy Y velocity
     * @param image Custom bullet image
     */
    public EnemyShot(float x, float y, float vx, float vy, Image image) {
        super(x, y, vx, vy, image);
    }

    /**
     * Constructor for enemy shot with custom image and no velocity
     * @param x X coordinate
     * @param y Y coordinate
     * @param image Custom bullet image
     */
    public EnemyShot(float x, float y, Image image) {
        super(x, y, image);
    }
} 