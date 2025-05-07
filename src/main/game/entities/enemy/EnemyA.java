package com.ideabobo.game.entities.enemy;

import com.ideabobo.game.entities.player.Battle;
import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.bullets.MoveAimingBullet;

/**
 * 第一种敌人类型
 * 继承自Enemy类，实现基本的移动和攻击模式
 */
public class EnemyA extends Enemy {
    private float vx;  // X方向速度
    private float vy;  // Y方向速度

    /**
     * 构造函数
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param battle 玩家引用
     * @param pattern 移动模式
     */
    public EnemyA(float x, float y, Battle battle, int pattern) {
        super(battle, GamePanel.enemyImageA);
        this._battle = battle;
        this.x = x;
        this.y = y;
        vx = 0.0F;
        vy = 2.0F;
        this.pattern = pattern;
        power = 10;
    }

    /**
     * 移动方法
     * 根据不同的移动模式实现不同的移动行为
     */
    public void move() {
        if (pattern == 0) {
            if (counter < 100) {
                y += vy;
            } else if (counter > 300) {
                y -= vy;
            }
        } else if (pattern == 1) {
            if (counter < 100) {
                x += vx;
                y += vy;
            } else if (counter > 300) {
                x -= vx;
                y -= vy;
            }
        }
        
        checkOutOfScreen();
        if (tamaIntCount > 0)
            tamaIntCount--;
            
        // 发射子弹
        if (counter > 100 && counter < 280 && tamaIntCount <= 0) {
            GamePanel.addList(new com.ideabobo.game.entities.bullets.EnemyShot(
                x + WIDTH / 2.0F, y + HEIGHT, 0.0F, 2.0F));
            tamaIntCount = 30;
        }
        
        counter++;
    }
} 