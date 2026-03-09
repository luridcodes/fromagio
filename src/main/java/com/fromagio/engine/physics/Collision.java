package com.fromagio.engine.physics;

import com.fromagio.engine.fromapi.GameObject;

public class Collision {

    /**
     * Static method to check whether two gameobjects have hitboxes that are
     * colliding or overlapping.
     *
     * <p> Currently uses the width and height of the objects to check collision
     * . Should support hitbox checking in the future instead of using the same
     * parameters as the object itself </p>
     * @param object1 the first {@link GameObject} to be checked
     * @param object2 the second {@link GameObject}
     * @return true if object hitboxes are colliding/overlapping
     */
    public static boolean isCollision(GameObject object1, GameObject object2) {
        return (object1.getX() < object2.getX() + object2.getWidth() &&
                object1.getX() + object1.getWidth() > object2.getX() &&
                object1.getY() < object2.getY() + object2.getHeight() &&
                object1.getY() + object1.getHeight() > object2.getY());
    }
}
