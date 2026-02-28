package com.fromagio.game;

import com.fromagio.engine.Window;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.world.World;
import org.tinylog.Logger;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Background {
    private final GameObject backgroundObject;

    public Background(World world) {
        backgroundObject = new GameObject(0,0);
        backgroundObject
                .setPosition(0, 0);
        backgroundObject.setScale(0.5f, 0.5f);
        backgroundObject.setRotation(10);
        world.addObject("background", backgroundObject);

    }
}
