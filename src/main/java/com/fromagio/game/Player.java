package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.world.World;
import org.tinylog.Logger;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Player {
    private final GameObject playerObject;

    public Player(World world) {
        playerObject = new GameObject(0,0);
        world.addObject("player", playerObject);
    }

    public void update(long diffTimeMillis, Window window) {
        float deltaTime = diffTimeMillis / 1000.0f;

        // Move based on WASD - DIRECTLY in update!
        float speed = 2.0f;
        if (window.isKeyPressed(GLFW_KEY_W)) {
            playerObject.translate(0, speed * deltaTime);
            Logger.info("W pressed - position: (" + playerObject.getX() + ", " + playerObject.getY() + ")");
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            playerObject.translate(0, -speed * deltaTime);
            Logger.info("S pressed - position: (" + playerObject.getX() + ", " + playerObject.getY() + ")");
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            playerObject.translate(-speed * deltaTime, 0);
            Logger.info("A pressed - position: (" + playerObject.getX() + ", " + playerObject.getY() + ")");
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            playerObject.translate(speed * deltaTime, 0);
            Logger.info("D pressed - position: (" + playerObject.getX() + ", " + playerObject.getY() + ")");
        }

        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            playerObject.setPosition(0,0);
            Logger.info("Space pressed - position reset to: (" + playerObject.getX() + ", " + playerObject.getY() + ")");
        }
    }
}

