package com.ideabobo.game.entities.bullets;

import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.player.Battle;

/**
 * 随机N向子弹类
 * 用于发射多个随机方向的子弹
 */
public class RandomNBullet extends EnemyShot {
    private float speed;      // 子弹速度
    private int n;           // 子弹数量
    private float[] vxNWay;  // X方向速度数组
    private float[] vyNWay;  // Y方向速度数组

    /**
     * 构造函数
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param vx0 初始X速度
     * @param vy0 初始Y速度
     * @param ziki 玩家引用
     */
    public RandomNBullet(float x, float y, float vx0, float vy0, Battle ziki) {
        super(x, y);
        speed = 2.0F;
        n = 8;  // 发射8个子弹
        
        // 初始化速度数组
        vxNWay = new float[n];
        vyNWay = new float[n];
        
        // 为每个子弹生成随机角度
        for (int i = 0; i < n; i++) {
            double angle = Math.random() * 2 * Math.PI;  // 随机角度
            vxNWay[i] = (float) (Math.cos(angle) * speed);
            vyNWay[i] = (float) (Math.sin(angle) * speed);
            
            // 添加新子弹到游戏
            GamePanel.addList(new EnemyShot(x, y, vxNWay[i], vyNWay[i]));
        }

        // 设置当前子弹的初始速度
        vx = vxNWay[0];
        vy = vyNWay[0];
    }
} 