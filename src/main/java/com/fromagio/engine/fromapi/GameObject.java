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
        Logger.info("[GameObject] Created GameObject with texture [{}]", texture.getTexturePath());
    }

    // access to components

    /**
     * Used to access the {@link Transform} instance bound to this GameObject. The Transform
     * class is used to handle operations related to position, size and rotation
     *
     * <p> Coordinates are given in pixels, with the top left corner being (0,0), and angles 
     * are given in degrees. </p>
     * 
     * <p> A transform class is created upon initialising a GameObject. The transform class is
     * responsible in generating the Model uniform matrix to calculate the position, size and
     * rotation of the object when being rendered. (See {@link Transform#getMatrix()}) </p>
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

    /**
     * Used to create and access the {@link InputHandler} 
     * 
     * <p> If no InputHandler has been initialised before, accessing the InputHandler 
     * will automatically create a new instance. Each Object can only have one 
     * InputHandler. </p>
     * 
     * <p> The InputHandler can be configured to set movement keys (up, down, left, right) 
     * and the speed of the object. Once configured, the 
     * {@link InputHandler#update(long diffTimeMillis)} method must be called each Input 
     * cycle in the game loop. (diffTimeMillis is necessary to calculate the displacement 
     * of the object using the speed</p>
     * 
     * <p> In the future, this method should also take other forms of input like mouse 
     * movements </p>
     * @return An {@link InputHandler}
     * @see InputHandler#setMovementKeys(int upKey, int downKey, int leftKey, int rightKey)
     * @see InputHandler#update(long diffTimeMillis) 
     */
    public InputHandler inputHandler() {
        if (inputHandler == null) {
            Logger.info("[GameObject] Create new InputHandler");
            inputHandler = new InputHandler(GameObject.this);
            return inputHandler;
        }
        else return inputHandler;
    }

    // Getters
    public Mesh getMesh() {
        return mesh;
    }
}
