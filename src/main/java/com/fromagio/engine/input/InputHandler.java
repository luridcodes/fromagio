package com.fromagio.engine.input;

import com.fromagio.engine.Engine;
import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import org.tinylog.Logger;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class InputHandler {
    long windowHandle;
    int upKey, downKey, leftKey, rightKey;
    GameObject object;
    float speed;

    public InputHandler(GameObject object) {
        this.object = object;
    }

    public void setMovementKeys(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void update(long diffTimeMillis) {
        float deltaTime = diffTimeMillis / 1000.0f;
        if(object == null) {
            throw new RuntimeException("Input object is null");
        }

        if(speed == 0) {
            Logger.info("Speed for object has not been set; default speed of 200 set");
            this.speed = 200.0f;
        }

        if(Engine.getWindow().isKeyPressed(upKey)) {
            object.translate(0, -speed*deltaTime);
        }
        if(Engine.getWindow().isKeyPressed(downKey)) {
            object.translate(0, speed*deltaTime);
        }
        if(Engine.getWindow().isKeyPressed(leftKey)) {
            object.translate(-speed*deltaTime,0 );
        }
        if(Engine.getWindow().isKeyPressed(rightKey)) {
            object.translate(speed*deltaTime, 0);
        }
    }
}
