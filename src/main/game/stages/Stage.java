package com.ideabobo.game.stages;

import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.core.GameManager;
import com.ideabobo.game.entities.enemy.Enemy;
import com.ideabobo.game.entities.enemy.Boss;
import com.ideabobo.game.utils.ResourceLoader;

/**
 * Base class for all game stages
 * Manages enemy spawning and stage completion
 */
public abstract class Stage {
    protected GameManager gameManager;
    protected int enemyCount;
    protected int bossCount;
    protected boolean isComplete;
    
    public Stage() {
        gameManager = GameManager.getInstance();
        enemyCount = 0;
        bossCount = 0;
        isComplete = false;
    }
    
    /**
     * Initialize the stage
     */
    public abstract void init();
    
    /**
     * Update stage logic
     */
    public abstract void update();
    
    /**
     * Check if stage is complete
     */
    public abstract boolean isComplete();
    
    /**
     * Spawn an enemy at the specified position
     */
    protected void spawnEnemy(float x, float y) {
        gameManager.addEntity(new Enemy(
            ResourceLoader.getInstance().loadImage("image/enemyA.gif"),
            x, y
        ));
        enemyCount++;
    }
    
    /**
     * Spawn a boss at the specified position
     */
    protected void spawnBoss(float x, float y) {
        gameManager.addEntity(new Boss(
            ResourceLoader.getInstance().loadImage("image/bossA.gif"),
            x, y
        ));
        bossCount++;
    }
    
    /**
     * Check if all enemies and bosses are defeated
     */
    protected void checkStageComplete() {
        if (enemyCount == 0 && bossCount == 0) {
            isComplete = true;
        }
    }
} 