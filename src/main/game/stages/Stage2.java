package com.ideabobo.game.stages;

import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.entities.enemy.Enemy;

/**
 * Second stage of the game
 * Features V-shaped enemy formations
 */
public class Stage2 extends Stage {
    private int spawnTimer;
    private int spawnDelay;
    private int waveCount;
    private int maxWaves;
    
    public Stage2() {
        super();
        spawnTimer = 0;
        spawnDelay = 45; // 0.75 seconds at 60 FPS
        waveCount = 0;
        maxWaves = 8;
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
        // Spawn enemies in a V formation
        for (int i = 0; i < 3; i++) {
            float x = GameConstants.WINDOW_WIDTH / 2.0f;
            float y = -50.0f - (i * 30.0f);
            spawnEnemy(x, y);
            
            if (i > 0) {
                // Left side
                x = GameConstants.WINDOW_WIDTH / 2.0f - (i * 50.0f);
                spawnEnemy(x, y);
                
                // Right side
                x = GameConstants.WINDOW_WIDTH / 2.0f + (i * 50.0f);
                spawnEnemy(x, y);
            }
        }
    }
} 