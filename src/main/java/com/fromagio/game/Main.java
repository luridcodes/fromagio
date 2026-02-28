package com.fromagio.game;

import com.fromagio.engine.*;
import com.fromagio.engine.gfx.Render;
import com.fromagio.engine.world.World;

public class Main implements IAppLogic {
    Player player;
    Background background;
    private InputHandler inputHandler;

    public static void main(String[] args) {
        Window.WindowOptions opts = new Window.WindowOptions();
        opts.height = 300;
        opts.width = 300;
        opts.compatibleProfile = false;

        Main main = new Main();
        Engine gameEng = new Engine("1.1.3", opts, main);
        gameEng.start();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, World world, Render render) {
        // initialise game-side abstractions once to re-use forever in game loop
        inputHandler = new InputHandler(window);

        player = new Player(world);
        background = new Background(world);
    }

    /*
    functions below are used in the game loop - don't initialise everything or you will
    be doing it 60 times a second...
     */
    @Override
    public void input(Window window, World scene, long diffTimeMillis) {
        inputHandler.run();
    }

    // called once every update loop
    @Override
    public void update(Window window, World world, long diffTimeMillis) {
        player.update(diffTimeMillis, window);
    }
}