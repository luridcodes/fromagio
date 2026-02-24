package com.fromagio.engine.graph;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

/**
 * Mesh objects store vertex information about a mesh, which is generally information about
 * a quad (rectangle). Meshes can contain position, colour and indices arrays
 *
 * <p> Arrays in a mesh are written to GPU memory to enable rendering using {@link SceneRender}.
 * Each array is written to a {@link java.nio.Buffer} object, then bound and written to an
 * OpenGL buffer using a VBO. A VAO is also initialised for each VBO.  </p>
 *
 * @author Lucas
 */
public class Mesh {

    private int numVertices;
    private int vaoId;
    private List<Integer> vboIdList;

    /**
     * Creates a new Mesh containing vertex positions and other information
     *
     * @param positions array of floats containing positions of vertices to be added to the mesh
     * @param numVertices number of floats for each vertex (1-4)
     */
    public Mesh(float[] positions, int numVertices) {
        this.numVertices = numVertices;
        vboIdList = new ArrayList<>();

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Positions VBO
        int vboId = glGenBuffers();
        vboIdList.add(vboId);

        // creates dynamic mem buffer to store vertices
        FloatBuffer positionsBuffer = MemoryUtil.memCallocFloat(positions.length);
        positionsBuffer.put(0, positions);

        // binds and writes buffer to GPU memory
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);

        // VAO can only be created after VBO is bound
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // unbind VAO and VBO buffers
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        MemoryUtil.memFree(positionsBuffer);
    }

    public void cleanup() {
        vboIdList.forEach(GL30::glDeleteBuffers);
        glDeleteVertexArrays(vaoId);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public final int getVaoID() {
        return vaoId;
    }
}