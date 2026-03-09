package com.fromagio.engine.input;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.tinylog.Logger;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class Keyboard {
    long windowHandle;
    GLFWKeyCallback keyCallback;


    public Keyboard(long windowHandle) {
        this.windowHandle = windowHandle;

        // this callback should be automatically cleaned in the window class
        keyCallback = glfwSetKeyCallback(windowHandle,
                (window, key, scancode, action, mods) -> {
                    keyCallBack(key, action);
                });
        Logger.info("[input.Keyboard] Keyboard Service initialised successfully");
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }


    // TODO: ESCAPE is the default key -- how do i change this from outside the engine?
    // should probably separate this behavior from the engine layer
    public void keyCallBack(int key, int action) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(windowHandle, true); // We will detect this in the rendering loop
        }
    }
}
