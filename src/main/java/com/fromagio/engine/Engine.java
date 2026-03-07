package com.fromagio.engine;

import com.fromagio.engine.fromapi.SceneManager;
import com.fromagio.engine.gfx.Render;
import com.fromagio.engine.input.InputHandler;
import com.fromagio.engine.fromapi.Scene;
import com.fromagio.game.Main;
import org.tinylog.Logger;

import java.util.Arrays;

public class Engine {

    public static final int TARGET_UPS = 30;
    private final IAppLogic appLogic;
    private final Window window;
    private InputHandler inputHandler;
    private Render render;
    private boolean running;
    private int targetFps;
    private int targetUps;
    private SceneManager sceneManager;
    public static Engine instance; // singleton design pattern

    /**
     * Enforces one engine per game
     */
    public static void init(String windowTitle, int windowHeight, int windowWidth, IAppLogic appLogic) {
        Window.WindowOptions opts = new Window.WindowOptions();
        opts.height = windowHeight;
        opts.width = windowWidth;
        opts.compatibleProfile = false;

        if (instance == null) {
            instance = new Engine("1.1.3", opts, appLogic);
        } else {
            Logger.error("Engine instance already created!");
        }
    }

    public Engine(String windowTitle, Window.WindowOptions opts, IAppLogic appLogic) {
        window = new Window(windowTitle, opts, () -> {
            resize();
            return null;
        });
        targetFps = opts.fps;
        targetUps = opts.ups;
        this.appLogic = appLogic;
        render = new Render();
        render.updateDimensions(window.getWidth(), window.getHeight());
        sceneManager = new SceneManager();
        appLogic.init(window, render, sceneManager);
        running = true;

        Logger.info("[Engine] Engine initialised for Window [{}]", windowTitle);
    }

    private void cleanup() {
        appLogic.cleanup();
        render.cleanup();
        sceneManager.getCurrentScene().cleanup();
        window.cleanup();
        Logger.info("all resources cleaned.");
    }

    /**
     * This function is called every time the window is resized
     * @see Window
     */
    private void resize() {
        render.updateDimensions(window.getWidth(), window.getHeight());
    }

    private void run() {
        long initialTime = System.currentTimeMillis();
        float timeU = 1000.0f / targetUps;
        float timeR = targetFps > 0 ? 1000.0f / targetFps : 0;
        float deltaUpdate = 0;
        float deltaFps = 0;

        long updateTime = initialTime;
        while (running && !window.windowShouldClose()) {
            window.pollEvents();

            long now = System.currentTimeMillis();
            deltaUpdate += (now - initialTime) / timeU;
            deltaFps += (now - initialTime) / timeR;

            if (targetFps <= 0 || deltaFps >= 1) {
                appLogic.input(window, now - initialTime);
            }

            if (deltaUpdate >= 1) {
                long diffTimeMillis = now - updateTime;
                appLogic.update(window, diffTimeMillis);
                updateTime = now;
                deltaUpdate--;
            }

            if (targetFps <= 0 || deltaFps >= 1) {
                render.render(window, sceneManager.getCurrentScene());
                deltaFps--;
                window.update();
            }
            initialTime = now;
        }

        cleanup();
    }

    public static void start() {
        instance.running = true;
        instance.run();
    }

    public void stop() {
        running = false;
    }

    public SceneManager getSceneManager() {
        return instance.sceneManager;
    }

    public static Window getWindow() {
        return instance.window;
    }

}
