package com.ideabobo.game.core;

import java.io.Serializable;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int currentState;
    private int level;
    private int score;
    private int specialAttackCount;
    private boolean isPaused;
    private long lastSaveTime;
    
    public GameState() {
        currentState = GameConstants.STATE_MENU;
        level = 1;
        score = 0;
        specialAttackCount = 0;
        isPaused = false;
        lastSaveTime = System.currentTimeMillis();
    }
    
    // Getters and Setters
    public int getCurrentState() {
        return currentState;
    }
    
    public void setCurrentState(int state) {
        this.currentState = state;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getScore() {
        return score;
    }
    
    public void addScore(int points) {
        this.score += points;
    }
    
    public int getSpecialAttackCount() {
        return specialAttackCount;
    }
    
    public void setSpecialAttackCount(int count) {
        this.specialAttackCount = count;
    }
    
    public boolean isPaused() {
        return isPaused;
    }
    
    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }
    
    public long getLastSaveTime() {
        return lastSaveTime;
    }
    
    public void updateLastSaveTime() {
        this.lastSaveTime = System.currentTimeMillis();
    }
    
    public void reset() {
        currentState = GameConstants.STATE_MENU;
        level = 1;
        score = 0;
        specialAttackCount = 0;
        isPaused = false;
        lastSaveTime = System.currentTimeMillis();
    }
} 