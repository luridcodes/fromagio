package com.fromagio.engine.graph;

import com.fromagio.engine.fromapi.Colour;
import org.tinylog.Logger;

import java.util.Arrays;

/**
 * Contains static methods for generating a {@link Mesh}.
 *
 * <p> Meshes are created based on NDC. Static methods should take width, height
 * and {@link Colour} parameters. Each method creates arrays of floats to pass into
 * the Mesh object. </p>
 */
public class MeshMaker {

    /**
     * Generates a new rectangular {@link Mesh} given a width and height
     *
     * <p> In the future, there should be a game coordinates system instead of usuing
     * OpenGL's NDC </p>
     *
     * @param width width of the mesh
     * @param height height of the mesh
     * @param colour {@link Colour} of the mesh
     * @return
     */
    public static Mesh genRectangle(float width, float height, Colour colour) {
        float halfW = width / 2;
        float halfH = height / 2;

        float[] positions = new float[]{
                -halfW,  halfH, 0.0f,  // top left
                halfW,  halfH, 0.0f,  // top right
                halfW, -halfH, 0.0f,  // bottom right
                -halfW, -halfH, 0.0f,  // bottom left
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
