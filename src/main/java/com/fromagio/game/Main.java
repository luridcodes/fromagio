package com.fromagio.game;

import com.fromagio.engine.*;
import com.fromagio.engine.fromapi.SceneManager;
import com.fromagio.engine.gfx.Render;
import com.fromagio.engine.fromapi.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
enum state {
    START_SCREEN,
    GAME_SCREEN,
}
public class Main implements IAppLogic {
    state gameState;
    Scene startScene;
    GameWorld gameWorld;
    SceneManager sceneManager;

    static void main() {
        // use singleton design pattern
        Engine.init("title", 500, 500, new Main());
        Engine.start();
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
    }

    /*
    functions below are used in the game loop - don't initialise everything, or you will
    be doing it 60 times a second...
     */
    @Override
    public void input(Window window, long diffTimeMillis) {
        if(gameState == state.GAME_SCREEN) gameWorld.update(diffTimeMillis);
    }

    // called once every update loop
    @Override
    public void update(Window window, long diffTimeMillis) {
        if(window.isKeyPressed(GLFW_KEY_SPACE)) {
            gameWorld = new GameWorld();
            gameState = state.GAME_SCREEN;
            sceneManager.setCurrentScene(gameWorld.getScene());
        }
    }
}