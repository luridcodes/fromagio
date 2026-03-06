package com.fromagio.engine;

import com.fromagio.engine.fromapi.SceneManager;
import com.fromagio.engine.gfx.Render;
import com.fromagio.engine.fromapi.Scene;

public interface IAppLogic {
    void cleanup();
    void init(Window window, Render render, SceneManager sceneManager);
    void input(Window window, long diffTimeMillis);

    /**
     * Called once per update cycle.
     * @param window
     * @param diffTimeMillis the amount of time elapsed since the last update
     */
    void update(Window window, long diffTimeMillis);
}
