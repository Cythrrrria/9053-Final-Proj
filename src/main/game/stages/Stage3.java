package com.ideabobo.game.stages;

import com.ideabobo.game.core.GameConstants;
import com.ideabobo.game.entities.enemy.Enemy;
import com.ideabobo.game.entities.enemy.Boss;

/**
 * Third stage of the game
 * Features circular enemy formations and a boss battle
 */
public class Stage3 extends Stage {
    private int spawnTimer;
    private int spawnDelay;
    private int waveCount;
    private int maxWaves;
    private boolean bossSpawned;
    
    public Stage3() {
        super();
        spawnTimer = 0;
        spawnDelay = 60; // 1 second at 60 FPS
        waveCount = 0;
        maxWaves = 6;
        bossSpawned = false;
    }
    
    @Override
    public void init() {
        gameManager.clearEntities();
        gameManager.clearEffects();
        spawnTimer = 0;
        waveCount = 0;
        bossSpawned = false;
        isComplete = false;
    }
    
    @Override
    public void update() {
        if (!bossSpawned && waveCount < maxWaves) {
            spawnTimer++;
            if (spawnTimer >= spawnDelay) {
                spawnWave();
                spawnTimer = 0;
                waveCount++;
            }
        } else if (!bossSpawned && waveCount >= maxWaves) {
            spawnBoss();
            bossSpawned = true;
        }
        
        checkStageComplete();
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    private void spawnWave() {
        // Spawn enemies in a circle formation
        int numEnemies = 8;
        float radius = 100.0f;
        float centerX = GameConstants.WINDOW_WIDTH / 2.0f;
        float centerY = -50.0f;
        
        for (int i = 0; i < numEnemies; i++) {
            float angle = (float) (2 * Math.PI * i / numEnemies);
            float x = centerX + radius * (float) Math.cos(angle);
            float y = centerY + radius * (float) Math.sin(angle);
            spawnEnemy(x, y);
        }
    }
    
    private void spawnBoss() {
        float x = GameConstants.WINDOW_WIDTH / 2.0f;
        float y = -100.0f;
        Boss boss = new Boss(x, y);
        gameManager.addEntity(boss);
    }
} 