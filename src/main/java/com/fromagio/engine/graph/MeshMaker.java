package com.fromagio.engine.graph;

import org.tinylog.Logger;

import java.util.Arrays;

public class MeshMaker {

    /**
     * Generates a new rectangular {@link Mesh} given a width and height in
     * pixels. The mesh is generated around the centre of the screen.
     *
     * <p>At present, the texture coordinates correspond to the entire
     * rectangle using the entire mesh. In the future, the MeshMaker should
     * support spritesheets in which tecture coordinates are only a
     * portion of the entire spritesheet </p>
     *
     * @param width width of the mesh
     * @param height height of the mesh
     * @return
     */
    public static Mesh genRectangle(float width, float height) {
        float halfW = width / 2;
        float halfH = height / 2;

        float[] positions = new float[]{
                -halfW, -halfH, 0.0f,  // top left
                halfW, -halfH, 0.0f,  // top right
                halfW, halfH, 0.0f,  // bottom right
                -halfW, halfH, 0.0f,  // bottom left
        };

        float[] texCoords = new float[]{
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };

        int[] indices = new int[]{
                0,1,3,3,1,2
        };

        Logger.info("[Mesh] New mesh created" + Arrays.toString(positions));
        return new Mesh(positions, texCoords, indices);
    }
}
