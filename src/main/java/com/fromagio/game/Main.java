package com.fromagio.game;

import com.fromagio.engine.*;
import com.fromagio.engine.graph.Render;
import com.fromagio.engine.world.World;
import org.tinylog.Logger;

import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

public class Main implements IAppLogic {

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

    // these Main functions are called by the engine; this prevents any of the parameters
    // from being explicitly used by Main() - rather, all Window, Scene and render parameters
    // are encapsulated in the engine instance, so the main function only needs to start the engine
    // once
    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, World world, Render render) {
        Scene scene = new Scene(world);
        // initialise game-side abstractions once to re-use forever in game loop
        inputHandler = new InputHandler(window);
    }

    /*
    functions below are used in the game loop - don't initialise everything or you will
    be doing it 60 times a second...
     */
    @Override
    public void input(Window window, World scene, long diffTimeMillis) {
        inputHandler.run();
    }

    // used to update physics and other things (might lag behind rendering)
    @Override
    public void update(Window window, World scene, long diffTimeMillis) {

    }
}