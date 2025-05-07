package com.ideabobo.game.leidian.utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Resource loader utility class
 * Handles loading and caching of game resources (images, sounds)
 */
public class ResourceLoader {
    private static final Map<String, Image> imageCache = new HashMap<>();
    private static final Map<String, Clip> soundCache = new HashMap<>();

    /**
     * Load image from resource path
     * @param path Resource path
     * @return Loaded image
     * @throws IOException if image loading fails
     */
    public static Image loadImage(String path) throws IOException {
        // Check cache first
        if (imageCache.containsKey(path)) {
            return imageCache.get(path);
        }

        // Load image from resource
        try (InputStream is = ResourceLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            Image image = ImageIO.read(is);
            imageCache.put(path, image);
            return image;
        }
    }

    /**
     * Load sound from resource path
     * @param path Resource path
     * @return Loaded sound clip
     * @throws Exception if sound loading fails
     */
    public static Clip loadSound(String path) throws Exception {
        // Check cache first
        if (soundCache.containsKey(path)) {
            return soundCache.get(path);
        }

        // Load sound from resource
        try (InputStream is = ResourceLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            soundCache.put(path, clip);
            return clip;
        }
    }

    /**
     * Play sound from resource path
     * @param path Resource path
     * @param loop Whether to loop the sound
     * @throws Exception if sound loading or playing fails
     */
    public static void playSound(String path, boolean loop) throws Exception {
        Clip clip = loadSound(path);
        if (clip != null) {
            clip.setFramePosition(0);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    /**
     * Stop playing sound
     * @param path Resource path
     */
    public static void stopSound(String path) {
        Clip clip = soundCache.get(path);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Clear all cached resources
     */
    public static void clearCache() {
        // Stop all playing sounds
        for (Clip clip : soundCache.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        }

        // Clear caches
        imageCache.clear();
        soundCache.clear();
    }

    /**
     * Get cached image
     * @param path Resource path
     * @return Cached image or null if not found
     */
    public static Image getCachedImage(String path) {
        return imageCache.get(path);
    }

    /**
     * Get cached sound
     * @param path Resource path
     * @return Cached sound clip or null if not found
     */
    public static Clip getCachedSound(String path) {
        return soundCache.get(path);
    }
} 