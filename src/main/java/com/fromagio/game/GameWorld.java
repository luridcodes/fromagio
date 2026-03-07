package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Scene;
import com.fromagio.engine.fromapi.Texture;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

/*
Using "worlds" (TODO find a better name for this
allows resources only to be loaded when a world is loaded instead of loading everything
at once
 */
public class GameWorld {
    Player lucas;
    Scene gameScene;

    public GameWorld() {
        lucas = new Player();
        gameScene = new Scene();
        gameScene.addObject("player", lucas.getGameObject());
    }

    public Scene getScene() {
        return gameScene;
    }

    public void update(long diffTimeMillis) {
        lucas.getGameObject().inputHandler().update(diffTimeMillis);
    }
}

class Player {
    private GameObject playerObject;
    private Texture cheeseTexture;

    Player() {
        cheeseTexture = new Texture("src/resources/textures/cheesepng.png");
        playerObject = new GameObject(50,50, 100,100, cheeseTexture);
        playerObject.inputHandler().setMovementKeys(GLFW_KEY_W, GLFW_KEY_S, GLFW_KEY_A, GLFW_KEY_D);
        playerObject.inputHandler().setSpeed(200.0f);
    }

    public GameObject getGameObject() {
        return playerObject;
    }

}

