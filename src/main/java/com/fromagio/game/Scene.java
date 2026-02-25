package com.fromagio.game;

import com.fromagio.engine.fromapi.Colour;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.graph.Mesh;
import com.fromagio.engine.fromapi.Drawer;
import com.fromagio.engine.world.World;

import static com.fromagio.engine.fromapi.Colour.*;

public class Scene {

    public Scene(World world) {

        Mesh mesh = Drawer.genRectangle(-0.5f, 0.5f, 0.4f, 1, new Colour(RED));
        GameObject obj = new GameObject(mesh);
        world.addObject("REDRECTANGLE", obj);

        Mesh mesh2 = Drawer.genRectangle(0.1f, 0.5f, 0.4f, 1, new Colour(GREEN));
        obj = new GameObject(mesh2);
        world.addObject("GREENRECTANGLE", obj);
    }
}

