package com.fromagio.engine;

import com.fromagio.engine.fromapi.SceneManager;

public interface IAppLogic {
    void cleanup();
    void init(Window window, SceneManager sceneManager);
    void input(Window window, long diffTimeMillis);
    void update(Window window, long diffTimeMillis);
}
