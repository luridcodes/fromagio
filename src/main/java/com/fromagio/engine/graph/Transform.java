package com.fromagio.engine.graph;

import org.joml.Matrix4f;

/**
 * Minimal Transform class - just stores position and can create a matrix
 *
 * <p> In the future, this class should contain rotation! </p>
 */
public class Transform {
    private float x, y;
    private float scaleX, scaleY;
    private float rotation;

    public Transform() {
        this(0, 0);
    }

    public Transform(float x, float y) {
        this.x = x;
        this.y = y;
        this.scaleX = 1;
        this.scaleY = 1;
        this.rotation = 0;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setScale(float sx, float sy) {
        this.scaleX = sx;
        this.scaleY = sy;
    }

    public void setRotation(float degrees) {
        this.rotation = degrees;
    }

    /**
     * Creates a translation matrix from current position
     */
    public Matrix4f getMatrix() {
        return new Matrix4f()
                .translate(x, y, 0)
                .rotateZ((float)Math.toRadians(rotation))
                .scale(scaleX, scaleY, 1);
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
