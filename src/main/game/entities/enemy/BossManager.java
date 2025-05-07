package com.ideabobo.game.entities.enemy;

import com.ideabobo.game.GamePanel;
import com.ideabobo.game.entities.player.Battle;

/**
 * Boss manager class
 * Used to manage boss generation and updates
 */
public class BossManager {
    private GamePanel gamePanel;
    private Battle battle;
    private Boss currentBoss;
    private int bossType;
    private boolean isBossStage;

    /**
     * Constructor
     * @param gamePanel Game panel
     * @param battle Player battle object
     */
    public BossManager(GamePanel gamePanel, Battle battle) {
        this.gamePanel = gamePanel;
        this.battle = battle;
        this.currentBoss = null;
        this.bossType = 0;
        this.isBossStage = false;
    }

    /**
     * Set boss type
     * @param bossType Boss type
     */
    public void setBossType(int bossType) {
        this.bossType = bossType;
    }

    /**
     * Start boss stage
     */
    public void startBossStage() {
        isBossStage = true;
        createBoss();
    }

    /**
     * Create boss
     */
    private void createBoss() {
        switch (bossType) {
            case 1:
                currentBoss = new BossA(gamePanel.getWidth() / 2, -100, battle);
                break;
            case 2:
                currentBoss = new BossB(gamePanel.getWidth() / 2, -100, battle);
                break;
            case 3:
                currentBoss = new BossC(gamePanel.getWidth() / 2, -100, battle);
                break;
        }
        if (currentBoss != null) {
            gamePanel.addBoss(currentBoss);
        }
    }

    /**
     * Update boss
     */
    public void update() {
        if (!isBossStage || currentBoss == null) {
            return;
        }

        if (currentBoss.isDead()) {
            endBossStage();
        }
    }

    /**
     * End boss stage
     */
    public void endBossStage() {
        isBossStage = false;
        currentBoss = null;
    }

    /**
     * Check if it is a boss stage
     * @return true if it is a boss stage, false otherwise
     */
    public boolean isBossStage() {
        return isBossStage;
    }

    /**
     * Get current boss
     * @return Current boss
     */
    public Boss getCurrentBoss() {
        return currentBoss;
    }
} 