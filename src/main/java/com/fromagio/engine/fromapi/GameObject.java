package com.fromagio.engine.fromapi;

import com.fromagio.engine.graph.Mesh;
import com.fromagio.engine.graph.MeshMaker;
import com.fromagio.engine.graph.Transform;
import org.tinylog.Logger;

/**
 * Each Game Object corresponds to an object in the game which is renderable. Game Objects are
 * intended to be created above the engine and handled by the engine, and contain a mesh and transform
 * matrix.
 *
 * <p> In the future, this object will contain other attributes </p>
 */
public class GameObject {
    private Mesh mesh;
    private Transform transform;  // Add transform

    public GameObject(float x, float y) {
        this.mesh = MeshMaker.genRectangle(1.0f, 1.0f, new Colour(Colour.RED));
        this.transform = new Transform(x, y);
        Logger.info("Created GameObject");
    }

    // Movement methods
    public void setPosition(float x, float y) {
        transform.setPosition(x, y);
    }

    public void setRotation(float degrees) { transform.setRotation(degrees); }
    public void setScale(float sx, float sy) { transform.setScale(sx, sy);}
    public void translate(float dx, float dy) {
        transform.translate(dx, dy);
    }

    // Getters
    public Mesh getMesh() {
        return mesh;
    }

    public Transform getTransform() {
        return transform;
    }

    public float getX() { return transform.getX(); }
    public float getY() { return transform.getY(); }
}
