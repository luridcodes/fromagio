package com.fromagio.engine.fromapi;

import org.tinylog.Logger;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Scene currentScene;
    private Map<String, Scene> sceneMap;

    public SceneManager() {
        sceneMap = new HashMap<>();
    }

    public void setCurrentScene(String sceneName) {
        if (currentScene != null) {
            currentScene.cleanup();
            Logger.info("[SceneManager] Scene [{}] cleaned", getCurrentSceneName());
        }
        this.currentScene = sceneMap.get(sceneName);
        Logger.info("[SceneManager] Current Scene Set to: [{}]", sceneName);
    }

    public String getCurrentSceneName() {
        for (Map.Entry<String, Scene> entry : sceneMap.entrySet()) {
            if (entry.getValue() == currentScene) {
                return entry.getKey();
            }
        }
        return null; // or throw an exception
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void addScene(String sceneName, Scene scene) {
        sceneMap.put(sceneName, scene);
        Logger.info("[SceneManager] Scene Added: [{}]", sceneName);
    }
}
