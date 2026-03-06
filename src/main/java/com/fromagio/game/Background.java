package com.fromagio.game;

import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.fromapi.Scene;


public class Background {
    private GameObject backgroundObject1;

    public GameObject Background() {
        Texture texture = new Texture("src/resources/textures/cheese.jpg");
        backgroundObject1 = new GameObject(250f,250f, 100, 100,  texture);
        backgroundObject1.setScale(5,5);
        backgroundObject1.setRotation(180);
        return backgroundObject1;
    }
}
