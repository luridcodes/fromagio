package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.fromapi.Scene;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Player {
    private GameObject playerObject;
    private Texture cheeseTexture;

    public Player() {
        cheeseTexture = new Texture("src/resources/textures/cheesepng.png");
        playerObject = new GameObject(50,50, 100,100, cheeseTexture);
        playerObject.createInput();
        playerObject.input().setMovementKeys(GLFW_KEY_W, GLFW_KEY_S, GLFW_KEY_A, GLFW_KEY_D);
        playerObject.input().setSpeed(200.0f);
    }

    public GameObject getGameObject() {
        return playerObject;
    }

    public void update(long diffTimeMillis, Window window) {
        playerObject.input().update(diffTimeMillis);
    }
}

