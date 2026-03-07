package com.fromagio.game;

import com.fromagio.engine.*;
import com.fromagio.engine.fromapi.SceneManager;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;


public class Main implements IAppLogic {
    GameScene gameScene;
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
    public void init(Window window, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameScene = new GameScene();

        sceneManager.addScene("START_SCENE", new StartScreen(window).getScene());
        sceneManager.addScene("END_SCENE", new EndScreen(window).getScene());
        sceneManager.addScene("GAME_SCENE", gameScene.getScene());

        sceneManager.setCurrentScene("START_SCENE");
    }

    /*
    functions below are used in the game loop - don't initialise everything, or you will
    be doing it 60 times a second...
     */
    @Override
    public void input(Window window, long diffTimeMillis) {
        if(sceneManager.getCurrentSceneName() == "START_SCENE"
                && Engine.input().getKeyboard().isKeyPressed(GLFW_KEY_SPACE)) {
            sceneManager.setCurrentScene("GAME_SCENE");
        }
    }

    // called once every update loop
    @Override
    public void update(Window window, long diffTimeMillis) {
        if(sceneManager.getCurrentSceneName() == "GAME_SCENE") {
            gameScene.update(diffTimeMillis);
            if(gameScene.isGameOver()) {
                sceneManager.setCurrentScene("END_SCENE");
            }
        }
    }
}