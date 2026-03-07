package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Scene;
import com.fromagio.engine.fromapi.Texture;

public class EndScreen {
    private GameObject startObject;
    private Texture startTexture;
    private Scene startScene;

    public EndScreen(Window window) {
        startTexture = new Texture("src/resources/textures/end_screen.png");
        startObject = new GameObject(
                (float) window.getWidth() /2,
                (float) window.getHeight()/2,
                (float) window.getWidth(),
                (float) window.getHeight(),
                startTexture);
        startScene = new Scene();
        startScene.addObject("start screen", startObject);
    }

    public Scene getScene() {
        return startScene;
    }

}

