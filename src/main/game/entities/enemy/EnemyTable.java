package com.ideabobo.game.entities.enemy;

/**
 * 敌人配置表类
 * 用于存储敌人的生成配置
 */
public class EnemyTable {
    private int type;     // 敌人类型
    private int time;     // 生成时间
    private float x;      // 生成X坐标
    private float y;      // 生成Y坐标
    private int pattern;  // 移动模式

    /**
     * 构造函数
     * @param type 敌人类型
     * @param time 生成时间
     * @param x 生成X坐标
     * @param y 生成Y坐标
     * @param pattern 移动模式
     */
    public EnemyTable(int type, int time, float x, float y, int pattern) {
        this.type = type;
        this.time = time;
        this.x = x;
        this.y = y;
        this.pattern = pattern;
    }

    /**
     * 获取敌人类型
     * @return 敌人类型
     */
    public int getType() {
        return type;
    }

    /**
     * 获取生成时间
     * @return 生成时间
     */
    public int getTime() {
        return time;
    }

    /**
     * 获取生成X坐标
     * @return 生成X坐标
     */
    public float getX() {
        return x;
    }

    /**
     * 获取生成Y坐标
     * @return 生成Y坐标
     */
    public float getY() {
        return y;
    }

    /**
     * 获取移动模式
     * @return 移动模式
     */
    public int getPattern() {
        return pattern;
    }
} 