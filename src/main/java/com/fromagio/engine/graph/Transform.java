package com.fromagio.engine.graph;

import org.joml.Matrix4f;
import org.tinylog.Logger;

/**
 * Minimal Transform class - just stores position and can create a matrix
 *
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
        // it is okay for the CPU to do math here bcus it's simple: here
        // preparing the matrix for the GPU to do per-vertex math, which is
        // FAR worse than whatever is going on here
        return new Matrix4f()
                .translate(x, y, 0)
                .scale(scaleX, scaleY, 1)
                .rotateZ((float)Math.toRadians(rotation));
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
