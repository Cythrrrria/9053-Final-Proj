package com.ideabobo.game.entities.enemy;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.player.Battle;

/**
 * 第二种敌人类型
 * 继承自Enemy类，实现发射光束的敌人
 */
public class EnemyB extends Enemy {
    private float vx;  // X方向速度
    private float vy;  // Y方向速度

    /**
     * 构造函数
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param battle 玩家引用
     * @param pattern 移动模式
     */
    public EnemyB(float x, float y, Battle battle, int pattern) {
        super(battle, GamePanel.enemyImageB);
        this._battle = battle;
        this.x = x;
        this.y = y;
        vx = 0.0F;
        vy = 2.0F;
        this.pattern = pattern;
        power = 25;
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
        }
        
        checkOutOfScreen();
        if (tamaIntCount > 0)
            tamaIntCount--;
            
        // 发射光束
        if (counter > 100 && counter < 280 && tamaIntCount <= 0) {
            GamePanel.addList(new com.ideabobo.game.entities.bullets.EnemyBeam(
                x + WIDTH / 2.0F, y + HEIGHT, _battle, this));
            tamaIntCount = 2;
        }
        
        counter++;
    }
} 