package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.SceneManager;
import com.fromagio.engine.fromapi.Texture;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class StartScreen {
    private GameObject startObject;
    private Texture startTexture;

    public StartScreen(Window window) {
        startTexture = new Texture("src/resources/textures/start_screen.png");
        startObject = new GameObject(
                (float) window.getWidth() /2,
                (float) window.getHeight()/2,
                (float) window.getWidth(),
                (float) window.getHeight(),
                startTexture);
    }

    public GameObject getGameObject() {
        return startObject;
    }

}

