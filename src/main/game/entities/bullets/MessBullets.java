package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.entities.player.Battle;

/**
 * 混乱子弹类
 * 用于发射带有随机偏移的子弹
 */
public class MessBullets extends EnemyShot {
    private float x_ziki;    // 玩家X坐标
    private float y_ziki;    // 玩家Y坐标
    private float x_enemy;   // 子弹初始X坐标
    private float y_enemy;   // 子弹初始Y坐标
    private float distance;  // 到玩家的距离
    private float speed;     // 子弹速度
    private int counter;     // 计数器

    /**
     * 构造函数
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param ziki 玩家引用
     */
    public MessBullets(float x, float y, Battle ziki) {
        super(x, y);
        speed = 3F;
        counter = 0;
        x_ziki = ziki.x;
        y_ziki = ziki.y;
        x_enemy = x;
        y_enemy = y;
        
        // 计算到玩家的距离
        distance = (float) Math.sqrt(
            Math.pow(x_ziki - x_enemy, 2) +
            Math.pow(y_ziki - y_enemy, 2)
        );

        // 计算初始速度分量
        if (distance != 0.0F) {
            vx = ((x_ziki - x_enemy) / distance) * speed;
            vy = ((y_ziki - y_enemy) / distance) * speed;
        } else {
            vx = 0.0F;
            vy = speed;
        }

        // 添加随机偏移
        for (int i = 20; i >= 0; i--) {
            if (counter % 7 == 0) {
                vx += i;
            }
            counter++;
        }
    }
} 