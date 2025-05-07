package com.ideabobo.game.entities.enemy;

import com.ideabobo.game.entities.player.Battle;
import com.ideabobo.game.core.GamePanel;
import com.ideabobo.game.entities.bullets.MoveAimingBullet;
import com.ideabobo.game.entities.bullets.MoveHomingBullet;
import com.ideabobo.game.entities.bullets.DirectionalBullet;
import com.ideabobo.game.entities.bullets.RandomNBullet;
import com.ideabobo.game.entities.effects.BigBurst;
import com.ideabobo.game.entities.player.BattleShot;
import com.ideabobo.game.entities.bullets.EnemyBeam;

/**
 * Third type of boss
 * Implements the most complex movement and attack patterns
 */
public class BossC extends Boss {
    private float vx;  // X velocity
    private float vy;  // Y velocity

    /**
     * Constructor
     * @param x Initial X position
     * @param y Initial Y position
     * @param battle Player reference
     */
    public BossC(float x, float y, Battle battle) {
        super(battle, GamePanel.bossImageC);
        this.x = x;
        this.y = y;
        vx = 0.0F;
        vy = 2.0F;
        power = 200;
    }

    /**
     * Movement method
     * Implements boss movement and attack patterns
     */
    public void move() {
        // Move vertically for first 150 frames
        if (counter < 150) {
            y += vy;
        } else {
            // Start complex movement pattern
            x += vx;
            y += Math.sin(counter * 0.05) * 3;
            if (x < 0 || x + WIDTH > app.getWidth()) {
                vx = -vx;
            }
        }

        // Update bullet interval counter
        if (bulletIntervalCount > 0) {
            bulletIntervalCount--;
        }

        // Attack patterns based on counter
        if (counter > 150) {
            if (counter % 60 == 0) {
                // Fire directional bullets in complex pattern
                for (int i = 0; i < 12; i++) {
                    GamePanel.addList(new DirectionalBullet(
                        x + WIDTH / 2.0F, y + HEIGHT, 
                        (i * 30 + counter * 2) % 360, battle));
                }
            } else if (counter % 100 == 0) {
                // Fire multiple aiming bullets
                for (int i = -2; i <= 2; i++) {
                    GamePanel.addList(new MoveAimingBullet(
                        x + WIDTH / 2.0F + i * 40F, y + HEIGHT, battle));
                }
            } else if (counter % 150 == 0) {
                // Fire multiple homing bullets
                for (int i = -1; i <= 1; i++) {
                    GamePanel.addList(new MoveHomingBullet(
                        x + WIDTH / 2.0F + i * 50F, y + HEIGHT, battle));
                }
            } else if (counter % 200 == 0) {
                // Fire random bullets
                GamePanel.addList(new RandomNBullet(
                    x + WIDTH / 2.0F, y + HEIGHT, 0.0F, 0.0F, battle));
            } else if (counter % 250 == 0) {
                // Fire multiple beams
                for (int i = -1; i <= 1; i++) {
                    GamePanel.addList(new EnemyBeam(
                        x + WIDTH / 2.0F + i * 60F, y + HEIGHT, battle, this));
                }
            }
        }

        counter++;
    }

    /**
     * Check collision with player's shots
     * @param chara Character to check collision with
     * @return true if collision occurred
     */
    protected boolean checkHit(Role chara) {
        if ((chara instanceof BattleShot) && 
            x + 50F > chara.x - WIDTH &&
            x - 50F < chara.x + chara.WIDTH &&
            y - 50F > chara.y - HEIGHT &&
            y + 50F < chara.y + chara.HEIGHT) {
            chara.dead();
            power--;
            if (power <= 0) {
                dead();
                GamePanel.largeBurst = new BigBurst(x, y);
            }
            return true;
        }
        return false;
    }
} 