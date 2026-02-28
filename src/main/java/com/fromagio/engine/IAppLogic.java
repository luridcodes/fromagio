package com.fromagio.engine;

import com.fromagio.engine.gfx.Render;
import com.fromagio.engine.world.World;

public interface IAppLogic {
    void cleanup();
    void init(Window window, World scene, Render render);
    void input(Window window, World scene, long diffTimeMillis);

    /**
     * Called once per update cycle.
     * @param window
     * @param scene
     * @param diffTimeMillis the amount of time elapsed since the last update
     */
    void update(Window window, World scene, long diffTimeMillis);
}
