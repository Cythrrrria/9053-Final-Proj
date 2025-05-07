package com.ideabobo.game.core;

public class GameConstants {
    // Window settings
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final String GAME_TITLE = "Thunder Fighter";
    
    // Player settings
    public static final float PLAYER_SPEED = 5.0f;
    public static final int PLAYER_INITIAL_POWER = 100;
    public static final int PLAYER_MAX_POWER = 100;
    
    // Enemy settings
    public static final float ENEMY_SPEED = 2.0f;
    public static final int ENEMY_HEALTH = 1;
    public static final int ENEMY_SCORE = 100;
    public static final int ENEMY_BASIC_DAMAGE = 10;
    public static final int ENEMY_BEAM_DAMAGE = 20;
    
    // Boss settings
    public static final float BOSS_SPEED = 1.5f;
    public static final int BOSS_HEALTH = 50;
    public static final int BOSS_SCORE = 1000;
    
    // Bullet settings
    public static final float BULLET_SPEED = 8.0f;
    public static final int BULLET_DAMAGE = 1;
    
    // Special attack settings
    public static final int SPECIAL_ATTACK_COUNT = 3;
    public static final int SPECIAL_ATTACK_DAMAGE = 50;
    
    // Game states
    public static final int STATE_MENU = 0;
    public static final int STATE_PLAYING = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_GAME_OVER = 3;
    public static final int STATE_LEVEL_COMPLETE = 4;
    
    // Level settings
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    
    // Resource paths
    public static final String IMAGE_PATH = "/images/";
    public static final String SOUND_PATH = "/sounds/";
} 