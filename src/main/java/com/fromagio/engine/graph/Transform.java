package com.fromagio.engine.graph;

import org.joml.Matrix4f;

/**
 * Minimal Transform class - just stores position and can create a matrix
 *
 * <p> In the future, this class should contain rotation! </p>
 */
public class Transform {
    private float x, y;

    public Transform() {
        this(0, 0);
    }

    public Transform(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Creates a translation matrix from current position
     */
    public Matrix4f getMatrix() {
        return new Matrix4f().translate(x, y, 0);
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
