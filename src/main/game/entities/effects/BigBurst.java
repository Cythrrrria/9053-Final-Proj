package com.ideabobo.game.entities.effects;

import com.ideabobo.game.core.GamePanel;
import java.awt.Graphics;

/**
 * 大型爆炸效果类
 * 用于显示Boss死亡时的大型爆炸动画
 */
public class BigBurst extends Thread {
    private float x;      // 爆炸效果的X坐标
    private float y;      // 爆炸效果的Y坐标
    private int count;    // 动画帧计数器

    /**
     * 构造函数
     * @param x 爆炸效果的X坐标
     * @param y 爆炸效果的Y坐标
     */
    public BigBurst(float x, float y) {
        this.x = x;
        this.y = y;
        count = 0;
        start();
    }

    /**
     * 动画线程运行方法
     * 每10毫秒更新一帧动画
     */
    public void run() {
        do {
            count++;
            if (count == 16)
                return;
            try {
                Thread.sleep(10L);
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
        if (count < 16) {
            g.drawImage(GamePanel.largeBurstImage[count], (int) x, (int) y, null);
        }
    }
} 