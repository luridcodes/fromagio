package com.fromagio.engine.fromapi;

import com.fromagio.engine.graph.Mesh;
import org.tinylog.Logger;

import java.util.Arrays;

/**
 * Drawer is intended to generate meshes. Currently, it is called from the game as an engine API.
 * In the future, meshes should be handled by the engine only and not by the game layer.
 *
 * <p> In the future, the positions and size should be: 1. generated through transform matrices
 * rather than baked into the mesh; 2. initialised through a Transform class stored in the
 * GameObject rather than intialised with the mesh. Initialising a game object should
 * automatically intiialise a mesh (if no such mesh exists beforehand) </p>
 */
public class Drawer {
    public static Mesh genRectangle(float posX, float posY, float width, float height, Colour colour) {
        float[] positions = new float[]{
                posX+width, posY, 0.0f,
                posX, posY, 0.0f,
                posX, posY-height, 0.0f,
                posX+width, posY-height, 0.0f,
        };

        float[] colors = new float[]{
                colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha(),
                colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha(),
                colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha(),
                colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha(),
        };

        int[] indices = new int[]{
                0,1,3,3,1,2
        };

        Logger.info("New mesh created" + Arrays.toString(positions));
        return new Mesh(positions, colors, indices);
    }
}
