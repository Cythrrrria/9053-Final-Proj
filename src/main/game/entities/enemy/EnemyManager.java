package com.ideabobo.game.entities.enemy;

import com.ideabobo.game.GamePanel;
import com.ideabobo.game.entities.player.Battle;
import java.util.ArrayList;
import java.util.List;

/**
 * Enemy manager class
 * Used to manage enemy generation and updates
 */
public class EnemyManager {
    private GamePanel gamePanel;
    private Battle battle;
    private List<EnemyTable> enemyTables;
    private int counter;
    private boolean isBossStage;

    /**
     * Constructor
     * @param gamePanel Game panel
     * @param battle Player battle object
     */
    public EnemyManager(GamePanel gamePanel, Battle battle) {
        this.gamePanel = gamePanel;
        this.battle = battle;
        this.enemyTables = new ArrayList<>();
        this.counter = 0;
        this.isBossStage = false;
    }

    /**
     * Add enemy configuration
     * @param type Enemy type
     * @param time Generation time
     * @param x Generation X coordinate
     * @param y Generation Y coordinate
     * @param pattern Movement pattern
     */
    public void addEnemyTable(int type, int time, float x, float y, int pattern) {
        enemyTables.add(new EnemyTable(type, time, x, y, pattern));
    }

    /**
     * Update enemies
     * Generate enemies based on configuration table
     */
    public void update() {
        if (isBossStage) {
            return;
        }

        counter++;
        for (EnemyTable table : enemyTables) {
            if (table.getTime() == counter) {
                createEnemy(table);
            }
        }
    }

    /**
     * Create enemy
     * @param table Enemy configuration
     */
    private void createEnemy(EnemyTable table) {
        Enemy enemy = null;
        switch (table.getType()) {
            case 1:
                enemy = new EnemyA(table.getX(), table.getY(), battle, table.getPattern());
                break;
            case 2:
                enemy = new EnemyB(table.getX(), table.getY(), battle, table.getPattern());
                break;
            case 3:
                enemy = new EnemyC(table.getX(), table.getY(), battle, table.getPattern());
                break;
        }
        if (enemy != null) {
            gamePanel.addEnemy(enemy);
        }
    }

    /**
     * Set boss stage
     * @param isBossStage Whether it is a boss stage
     */
    public void setBossStage(boolean isBossStage) {
        this.isBossStage = isBossStage;
    }

    /**
     * Clear enemy configuration table
     */
    public void clearEnemyTables() {
        enemyTables.clear();
    }

    /**
     * Reset counter
     */
    public void resetCounter() {
        counter = 0;
    }
} 