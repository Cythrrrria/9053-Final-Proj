package com.ideabobo.game.entities.effects;

import com.ideabobo.game.core.GamePanel;
import java.awt.Graphics;

/**
 * 基础爆炸效果类
 * 用于显示小型爆炸动画
 */
public class Burst extends Thread {
    private float x;      // 爆炸效果的X坐标
    private float y;      // 爆炸效果的Y坐标
    private int count;    // 动画帧计数器
    private int MAX;      // 最大动画帧数

    /**
     * 构造函数
     * @param x 爆炸效果的X坐标
     * @param y 爆炸效果的Y坐标
     */
    public Burst(float x, float y) {
        this.x = x;
        this.y = y;
        count = 0;
        MAX = GamePanel.burstImage.length;
        start();
    }

    /**
     * 动画线程运行方法
     * 每20毫秒更新一帧动画
     */
    public void run() {
        do {
            count++;
            if (count == MAX)
                return;
            try {
                Thread.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    /**
     * 绘制爆炸效果
     * @param g 图形上下文
     */
    public void draw(Graphics g) {
        if (count < MAX) {
            g.drawImage(GamePanel.burstImage[count], (int) x, (int) y, null);
        }
    }
} 