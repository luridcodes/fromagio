package com.fromagio.engine.fromapi;

import com.fromagio.engine.graph.Mesh;
import com.fromagio.engine.graph.MeshMaker;
import com.fromagio.engine.graph.Transform;
import org.tinylog.Logger;

/**
 * Each Game Object corresponds to an object in the game which is renderable.
 * Game Objects are intended to be created above the engine and handled by the
 * engine, and contain a mesh and transform matrix.
 *
 * <p> Contains getters for {@link Transform} and {@link Texture} classes (more
 * components may be added in the future). Each of these classes acts as a
 * component which controls a specific attribute of the object, with methods
 * that can be accessed through these classes to modify these attributes</p>
 *
 * @see Transform
 * @see Texture
 * @see Mesh
 */
public class GameObject {
    private final Mesh mesh;
    private final Transform transform;  // Add transform
    private final Texture texture;
    private final float getWidth;
    private final float height;

    public GameObject(float x, float y, float width, float height, Texture texture) {
        this.mesh = MeshMaker.genRectangle(width, height);
        this.transform = new Transform(x, y);
        this.texture = texture;
        this.getWidth = width;
        this.height = height;
        Logger.info("[GameObject] Created GameObject with texture [{}]", texture.getTexturePath());
    }

    // access to components

    /**
     * Used to access the {@link Transform} instance bound to this GameObject.
     * The Transform class is used to handle operations related to position,
     * size and rotation
     *
     * <p> Coordinates are given in pixels, with the top left corner being (0,0)
     * , and angles are given in degrees. </p>
     * 
     * <p> A transform class is created upon initialising a GameObject. The
     * transform class is responsible in generating the Model uniform matrix to
     * calculate the position, size and rotation of the object when being
     * rendered. (See {@link Transform#getMatrix()}) </p>
     *
     * @return The {@link Transform} instance linked to this object
     */
    public Transform transform() { return transform;}

    /**
     * Used to access the {@link Texture} instance bound to this object. 
     * 
     * <p> Mostly used for internal accessing of the texture for rendering</p>
     * @return The {@link Texture} instance 
     */
    public Texture texture() { return texture;}

    // Getters
    public Mesh getMesh() {
        return mesh;
    }

    // for convenience purposes, the transform methods are wrapped here
    public float getWidth() { return getWidth; }
    public float getHeight() { return height; }
    public float getX() { return transform.getX(); }
    public float getY() { return transform.getY(); }
}
