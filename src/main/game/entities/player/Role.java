package com.ideabobo.game.leidian.entities.player;

import com.ideabobo.game.GamePanel;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 游戏角色基类
 * 提供基本的角色功能，包括位置、大小、碰撞检测等
 */
public abstract class Role {
    protected static GamePanel app;  // 游戏面板引用
    protected Image img;             // 角色图像
    protected float x;              // X坐标
    protected float y;              // Y坐标
    protected float WIDTH;          // 宽度
    protected float HEIGHT;         // 高度
    private boolean dead;           // 死亡状态

    /**
     * 构造函数
     * @param img 角色图像
     */
    protected Role(Image img) {
        this.img = img;
        WIDTH = img.getWidth(app);
        HEIGHT = img.getHeight(app);
        dead = false;
    }

    /**
     * 检查角色是否死亡
     * @return 死亡状态
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * 设置角色死亡
     */
    public void dead() {
        dead = true;
    }

    /**
     * 移动方法，由子类实现
     */
    public abstract void move();

    /**
     * 碰撞检测
     * @param chara 另一个角色
     * @return 是否发生碰撞
     */
    protected boolean checkHit(Role chara) {
        return x > chara.x - WIDTH && x < chara.x + chara.WIDTH
            && y > chara.y - HEIGHT && y < chara.y + chara.HEIGHT;
    }

    /**
     * 绘制角色
     * @param g 图形上下文
     */
    public void draw(Graphics g) {
        g.drawImage(img, (int) x, (int) y, app);
    }

    /**
     * 绘制爆炸效果
     * @param g 图形上下文
     */
    public void drawBurst(Graphics g) {
        g.drawImage(GamePanel.burstImage[0], (int) x, (int) y, app);
    }
} 