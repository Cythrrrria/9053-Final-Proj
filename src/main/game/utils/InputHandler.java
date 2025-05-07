package com.ideabobo.game.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private static InputHandler instance;
    private boolean[] keys;
    private boolean[] previousKeys;
    
    // Movement keys
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean slow;
    
    // Action keys
    public boolean shoot;
    public boolean beam;
    public boolean special;
    public boolean pause;
    
    private InputHandler() {
        keys = new boolean[256];
        previousKeys = new boolean[256];
    }
    
    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
        }
        return instance;
    }
    
    public void update() {
        // Movement keys
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        slow = keys[KeyEvent.VK_SHIFT];
        
        // Action keys
        shoot = keys[KeyEvent.VK_Z];
        beam = keys[KeyEvent.VK_X];
        special = keys[KeyEvent.VK_C];
        pause = keys[KeyEvent.VK_P];
        
        // Update previous keys
        System.arraycopy(keys, 0, previousKeys, 0, keys.length);
    }
    
    public boolean isKeyPressed(int keyCode) {
        return keys[keyCode] && !previousKeys[keyCode];
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
} 