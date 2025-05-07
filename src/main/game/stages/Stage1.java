package com.ideabobo.game.stages;

import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.entities.enemy.Enemy;

/**
 * First stage of the game
 * Features linear enemy formations
 */
public class Stage1 extends Stage {
    private int spawnTimer;
    private int spawnDelay;
    private int waveCount;
    private int maxWaves;
    
    public Stage1() {
        super();
        spawnTimer = 0;
        spawnDelay = 60; // 1 second at 60 FPS
        waveCount = 0;
        maxWaves = 5;
    }
    
    @Override
    public void init() {
        gameManager.clearEntities();
        gameManager.clearEffects();
        spawnTimer = 0;
        waveCount = 0;
        isComplete = false;
    }
    
    @Override
    public void update() {
        if (waveCount < maxWaves) {
            spawnTimer++;
            if (spawnTimer >= spawnDelay) {
                spawnWave();
                spawnTimer = 0;
                waveCount++;
            }
        }
        
        checkStageComplete();
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    private void spawnWave() {
        // Spawn enemies in a line formation
        for (int i = 0; i < 5; i++) {
            float x = (GameConstants.WINDOW_WIDTH / 6.0f) * (i + 1);
            float y = -50.0f;
            spawnEnemy(x, y);
        }
    }
} 