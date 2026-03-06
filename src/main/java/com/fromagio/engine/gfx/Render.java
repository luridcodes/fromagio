package com.fromagio.engine.gfx;

import org.lwjgl.opengl.GL;
import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.Scene;
import org.tinylog.Logger;

import static org.lwjgl.opengl.GL11.*;

/**
 * Handles the main rendering pipeline by clearing the screen and calling {@link SceneRender}
 * to render the current screen, and resets the viewpoint
 *
 * <p> This class should only be called in {@link com.fromagio.engine.Engine} and nowhere else </p>
 */
public class Render {

    private final SceneRender sceneRender;

    /**
     * Creates a {@link Render} instance. This instance should only be created once in
     * {@link com.fromagio.engine.Engine}
     */
    public Render() {
        GL.createCapabilities();
        sceneRender = new SceneRender();
        Logger.info("Created Render instance");
    }

    public void cleanup() {
        sceneRender.cleanup();
    }

    /**
     * this method is called during the {@link com.fromagio.engine.Engine} gameloop
     *
     * <p> Clears the framebuffers to black(?) before calling {@link SceneRender}.
     * Also reset the viewport each call </p>
     *
     * @param window a {@link Window} instance
     * @param scene a {@link Scene} instance
     */
    public void render(Window window, Scene scene) {
        glClearColor(0, 0, 0, 1);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glViewport(0, 0, window.getWidth(), window.getHeight());

        sceneRender.render(scene);
    }

    public void updateDimensions(int width, int height) {
        sceneRender.updateDimensions(width, height);
    }
}