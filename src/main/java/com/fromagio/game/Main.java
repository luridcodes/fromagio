package com.fromagio.game;

import com.fromagio.engine.*;
import com.fromagio.engine.fromapi.SceneManager;
import com.fromagio.engine.gfx.Render;
import com.fromagio.engine.fromapi.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Main implements IAppLogic {
    Scene startScene;
    Scene gameScene;
    Player lucas;
    SceneManager sceneManager;

    static void main() {
        Window.WindowOptions opts = new Window.WindowOptions();
        opts.height = 500;
        opts.width = 1000;
        opts.compatibleProfile = false;

        Main main = new Main();
        Engine gameEng = new Engine("1.1.3", opts, main);
        gameEng.start();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, Render render, SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        startScene = new Scene();
        StartScreen startScreen = new StartScreen(window);
        startScene.addObject("Start Scene Text", startScreen.getGameObject());
        sceneManager.setCurrentScene(startScene);

        gameScene = new Scene();
        lucas = new Player();
        gameScene.addObject("player", lucas.getGameObject());
    }

    /*
    functions below are used in the game loop - don't initialise everything, or you will
    be doing it 60 times a second...
     */
    @Override
    public void input(Window window, long diffTimeMillis) {
        if(sceneManager.getCurrentScene() == gameScene) lucas.update(diffTimeMillis, window);
    }

    // called once every update loop
    @Override
    public void update(Window window, long diffTimeMillis) {
        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
           sceneManager.setCurrentScene(gameScene);
        }
    }
}