package com.fromagio.engine.fromapi;

import com.fromagio.engine.graph.Mesh;
import com.fromagio.engine.graph.MeshMaker;
import com.fromagio.engine.graph.Transform;
import com.fromagio.engine.input.InputHandler;
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
    private Texture texture;
    private InputHandler inputHandler;

    public GameObject(float x, float y, float width, float height, Texture texture) {
        this.mesh = MeshMaker.genRectangle(width, height);
        this.transform = new Transform(x, y);
        this.texture = texture;
        Logger.info("Created GameObject with texture [{}]", texture.getTexturePath());
    }

    // Movement methods
    public void setPosition(float x, float y) {
        transform.setPosition(x, y);
    }
    public void setRotation(float degrees) { transform.setRotation(degrees); }
    public void setScale(float sx, float sy) { transform.setScale(sx, sy);}

    // Getters
    public Mesh getMesh() {
        return mesh;
    }
    public String getTexturePath() {
        return texture.getTexturePath();
    }
    public Texture getTexture() { return texture; }
    public float getX() { return transform.getX(); }
    public float getY() { return transform.getY(); }

    // Transform methods
    public void translate(float dx, float dy) {
        transform.translate(dx, dy);
    }
    public Transform getTransform() {
        return transform;
    }

    public void createInput() {
        inputHandler = new InputHandler(GameObject.this);
    }

    public InputHandler input() {
        return inputHandler;
    }
}
