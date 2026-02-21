package com.fromagio.engine;

import org.lwjgl.opengl.GL;
import com.fromagio.engine.Window;
import com.fromagio.engine.Scene;

import static org.lwjgl.opengl.GL11.*;

public class Render {

    public Render() {
        GL.createCapabilities();
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void cleanup() {
        // Nothing to be done here yet
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
