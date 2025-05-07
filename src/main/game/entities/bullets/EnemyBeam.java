package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.enemy.Enemy;
import com.ideabobo.game.entities.player.Battle;

/**
 * 敌人光束类
 * 用于发射激光攻击
 */
public class EnemyBeam extends EnemyShot {
    private Battle ziki;      // 玩家引用
    private Enemy enemy;      // 发射光束的敌人
    private int counter;      // 计数器

    /**
     * 构造函数
     * @param x 光束的X坐标
     * @param y 光束的Y坐标
     * @param ziki 玩家引用
     * @param enemy 发射光束的敌人
     */
    public EnemyBeam(float x, float y, Battle ziki, Enemy enemy) {
        super(x, y, GamePanel.enemyBeamImage);
        this.ziki = ziki;
        this.enemy = enemy;
        counter = 0;
    }

    /**
     * 更新光束位置和状态
     */
    public void move() {
        // 光束跟随发射者移动
        x = enemy.x + enemy.WIDTH / 2.0F;
        y = enemy.y + enemy.HEIGHT;
        
        counter++;
        // 光束持续30帧后消失
        if (counter > 30) {
            dead();
        }
    }
} 