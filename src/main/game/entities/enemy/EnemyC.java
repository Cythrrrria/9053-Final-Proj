package com.ideabobo.game.entities.enemy;

import com.ideabobo.game.entities.player.Battle;
import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.bullets.SplitBullet;

/**
 * 第三种敌人类型
 * 继承自Enemy类，实现发射分裂子弹的敌人
 */
public class EnemyC extends Enemy {
    private float vx;  // X方向速度
    private float vy;  // Y方向速度

    /**
     * 构造函数
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param battle 玩家引用
     * @param pattern 移动模式
     */
    public EnemyC(float x, float y, Battle battle, int pattern) {
        super(battle, GamePanel.enemyImageC);
        vx = 0.0F;
        vy = 3F;
        this._battle = battle;
        this.x = x;
        this.y = y;
        this.pattern = pattern;
        power = 5;
    }

    /**
     * 移动方法
     * 根据不同的移动模式实现不同的移动行为
     */
    public void move() {
        if (pattern == 0) {
            if (counter < 60) {
                x += vx;
                y += vy;
            } else if (counter > 250) {
                x += vx;
                y -= vy;
            }
        } else if (pattern == 1) {
            if (counter < 60) {
                x -= vx;
                y += vy;
            } else if (counter > 150) {
                x += vx;
                y -= vy;
            }
        }
        
        checkOutOfScreen();
        if (tamaIntCount > 0)
            tamaIntCount--;
            
        // 发射分裂子弹
        if (tamaIntCount <= 0) {
            GamePanel.addList(new com.ideabobo.game.entities.bullets.SplitBullet(
                x + WIDTH / 2.0F, y + HEIGHT, _battle));
            tamaIntCount = 90;
        }
        
        counter++;
    }
} 