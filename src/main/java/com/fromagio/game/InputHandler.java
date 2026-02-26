package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

public class InputHandler {
    Window window;

    public InputHandler(Window window) {
        this.window = window;
    }

    public void run() {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            System.out.println("W pressed");
            // TODO: what if i wanted a function to end the game?
        }
    }
}
