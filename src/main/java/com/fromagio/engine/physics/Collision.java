package com.fromagio.engine.physics;

import com.fromagio.engine.fromapi.GameObject;

public class Collision {
    public static boolean isCollision(GameObject object1, GameObject object2) {
        return (object1.getX() < object2.getX() + object2.getWidth() &&
                object1.getX() + object1.getWidth() > object2.getX() &&
                object1.getY() < object2.getY() + object2.getHeight() &&
                object1.getY() + object1.getHeight() > object2.getY());
    }
}
