package com.ideabobo.game.core;

import com.ideabobo.game.entities.GameObject;
import com.ideabobo.game.entities.effects.Explosion;
import com.ideabobo.game.entities.player.Player;
import com.ideabobo.game.entities.player.PlayerBeam;
import com.ideabobo.game.entities.player.PlayerBullet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameManager {
    private static GameManager instance;
    private List<GameObject> entities;
    private List<GameObject> effects;
    private Player player;
    private int specialAttackCount;
    
    private GameManager() {
        entities = new ArrayList<>();
        effects = new ArrayList<>();
        player = Player.getInstance();
        specialAttackCount = GameConstants.SPECIAL_ATTACK_COUNT;
    }
    
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    public void update() {
        // Update player
        player.update();
        
        // Update entities
        Iterator<GameObject> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            GameObject entity = entityIterator.next();
            entity.update();
            
            // Check collisions
            if (entity.checkCollision(player)) {
                entity.destroy();
            }
            
            // Remove destroyed entities
            if (entity.isDestroyed()) {
                entityIterator.remove();
            }
        }
        
        // Update effects
        Iterator<GameObject> effectIterator = effects.iterator();
        while (effectIterator.hasNext()) {
            GameObject effect = effectIterator.next();
            effect.update();
            
            if (effect.isDestroyed()) {
                effectIterator.remove();
            }
        }
    }
    
    public void render(java.awt.Graphics g) {
        // Render entities
        for (GameObject entity : entities) {
            entity.render(g);
        }
        
        // Render effects
        for (GameObject effect : effects) {
            effect.render(g);
        }
        
        // Render player
        player.render(g);
    }
    
    public void addEntity(GameObject entity) {
        entities.add(entity);
    }
    
    public void addEffect(GameObject effect) {
        effects.add(effect);
    }
    
    public void clearEntities() {
        entities.clear();
    }
    
    public void clearEffects() {
        effects.clear();
    }
    
    public void useSpecialAttack() {
        if (specialAttackCount > 0) {
            specialAttackCount--;
            // Add special attack effects
            for (int i = 0; i < 10; i++) {
                addEffect(new Explosion(
                    (float) (Math.random() * GameConstants.WINDOW_WIDTH),
                    (float) (Math.random() * GameConstants.WINDOW_HEIGHT)
                ));
            }
        }
    }
    
    public int getSpecialAttackCount() {
        return specialAttackCount;
    }
    
    public void resetSpecialAttackCount() {
        specialAttackCount = GameConstants.SPECIAL_ATTACK_COUNT;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void skillAnimation() {
        // 实现技能动画逻辑
    }
} 