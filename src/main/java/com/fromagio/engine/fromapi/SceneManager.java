package com.fromagio.engine.fromapi;

public class SceneManager {
    private Scene currentScene;

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}
