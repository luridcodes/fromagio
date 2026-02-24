package com.fromagio.engine;

import com.fromagio.engine.graph.Render;
import com.fromagio.engine.scene.Scene;

public interface IAppLogic {
    void cleanup();
    void init(Window window, Scene scene, Render render);
    void input(Window window, Scene scene, long diffTimeMillis);
    void update(Window window, Scene scene, long diffTimeMillis);
}
