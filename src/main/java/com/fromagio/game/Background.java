package com.fromagio.game;

import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.world.World;


public class Background {
    private final GameObject backgroundObject1;

    public Background(World world) {
        Texture texture = new Texture("src/resources/textures/cheese.jpg");
        backgroundObject1 = new GameObject(0,0, texture);
        backgroundObject1.setScale(0.5f, 0.5f);
        backgroundObject1.setRotation(180);

        GameObject backgroundObject2 = new GameObject(-0.5f,-0.5f, texture);
        backgroundObject2.setScale(0.5f, 0.5f);

        world.addObject("background1", backgroundObject1);
        world.addObject("background2", backgroundObject2);

    }
}
