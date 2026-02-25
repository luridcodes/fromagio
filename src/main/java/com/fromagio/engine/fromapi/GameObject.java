package com.fromagio.engine.fromapi;

import com.fromagio.engine.graph.Mesh;

import static org.lwjgl.opengl.GL30.*;

/**
 * Each Game Object corresponds to an object in the game which is renderable. Game Objects are
 * intended to be created above the engine and handled by the engine.
 *
 * <p> Currently, the GameObject needs to be initialised with a {@link Mesh}. In the future, game
 * objects should be initialisable without a mesh, possibly using only texture, position, and size
 * parameters. </p>
 */
public class GameObject {
    Mesh mesh;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
    }

    public Mesh getMesh() {
        return mesh;
    }
}
