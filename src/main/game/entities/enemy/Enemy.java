package com.ideabobo.game.leidian.entities.enemy;

import com.ideabobo.game.GamePanel;
import com.ideabobo.game.entities.player.Role;
import com.ideabobo.game.entities.bullets.BattleShot;
import com.ideabobo.game.entities.player.Battle;
import com.ideabobo.game.entities.effects.Burst;
import java.awt.Image;

/**
 * 敌人基类
 * 继承自Role类，提供基本的敌人功能
 */
public abstract class Enemy extends Role {
    protected int tamaIntCount;  // 子弹发射间隔计数器
    protected Battle _battle;    // 玩家引用
    protected int power;         // 生命值
    protected int counter;       // 通用计数器
    protected int pattern;       // 移动模式
    protected float vx;          // X方向速度
    protected float vy;          // Y方向速度

    // 移动模式常量
    protected final int pattern0 = 0;
    protected final int pattern1 = 1;
    protected final int pattern2 = 2;
    protected final int pattern3 = 3;
    protected final int pattern4 = 4;
    protected final int pattern5 = 5;
    protected final int pattern6 = 6;
    protected final int pattern7 = 7;

    /**
     * 构造函数
     * @param battle 玩家引用
     * @param enemyImage 敌人图像
     */
    public Enemy(Battle battle, Image enemyImage) {
        super(enemyImage);
        this._battle = battle;
        tamaIntCount = 0;
        counter = 0;
    }

    /**
     * 移动方法，由子类实现
     */
    public abstract void move();

    /**
     * 检查是否超出屏幕
     */
    public void checkOutOfScreen() {
        if (y - 100F > (float) app.getHeight() || x + WIDTH + 100F < 0.0F
            || x - 100F > (float) app.getWidth()
            || y + HEIGHT + 100F < 0.0F)
            dead();
    }

    /**
     * 碰撞检测
     * @param chara 另一个角色
     * @return 是否发生碰撞
     */
    protected boolean checkHit(Role chara) {
        if ((chara instanceof BattleShot) && super.checkHit(chara)) {
            chara.dead();
            power--;
            if (power <= 0) {
                GamePanel.burst = new Burst(x, y);
                dead();
            }
            return true;
        } else {
            return false;
        }
    }
} 