package com.ideabobo.game.utils;

import com.ideabobo.game.core.GameState;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private static final String SAVE_DIR = "saves";
    private static final String SAVE_EXTENSION = ".sav";
    private static SaveManager instance;
    
    private SaveManager() {
        // Ensure save directory exists
        File saveDir = new File(SAVE_DIR);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
    }
    
    public static SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
    }
    
    public void saveGame(GameState gameState, String saveName) {
        try {
            File saveFile = new File(SAVE_DIR, saveName + SAVE_EXTENSION);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
            
            // Save game state
            oos.writeObject(gameState);
            oos.close();
            
            System.out.println("Game saved to: " + saveFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }
    
    public GameState loadGame(String saveName) {
        try {
            File saveFile = new File(SAVE_DIR, saveName + SAVE_EXTENSION);
            if (!saveFile.exists()) {
                System.err.println("Save file not found: " + saveFile.getAbsolutePath());
                return null;
            }
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
            GameState gameState = (GameState) ois.readObject();
            ois.close();
            
            System.out.println("Game loaded from: " + saveFile.getAbsolutePath());
            return gameState;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }
    
    public List<String> getSaveFiles() {
        List<String> saveFiles = new ArrayList<>();
        File saveDir = new File(SAVE_DIR);
        
        if (saveDir.exists()) {
            File[] files = saveDir.listFiles((dir, name) -> name.endsWith(SAVE_EXTENSION));
            if (files != null) {
                for (File file : files) {
                    String name = file.getName();
                    saveFiles.add(name.substring(0, name.length() - SAVE_EXTENSION.length()));
                }
            }
        }
        
        return saveFiles;
    }
    
    public boolean deleteSave(String saveName) {
        File saveFile = new File(SAVE_DIR, saveName + SAVE_EXTENSION);
        if (saveFile.exists()) {
            return saveFile.delete();
        }
        return false;
    }
} 