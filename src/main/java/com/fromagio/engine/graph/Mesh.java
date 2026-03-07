package com.fromagio.engine.graph;

import com.fromagio.engine.gfx.SceneRender;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.*;
import org.tinylog.Logger;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
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
     * Creates a new Mesh containing vertex positions and other information to draw a quad
     *
     * @param positions array of floats containing positions of vertices to be added to the mesh
     * @param indices array of integers specifying how to read the positions array to draw triangles
     * @param texCoords array of floats for the texture coordinates corresponding to the position coordinates
     */
    public Mesh(float[] positions, float[] texCoords, int[] indices) {
        numVertices = indices.length;
        vboIdList = new ArrayList<>();

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);
        int vboId = glGenBuffers(); // Positions VBO
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

        // buffer for textuuure coordinates
        vboId = glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer texCoordsBuffer = MemoryUtil.memCallocFloat(texCoords.length);
        texCoordsBuffer.put(0, texCoords);
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, texCoordsBuffer, GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        // buffer for indices coordinates
        vboId =  glGenBuffers();
        vboIdList.add(vboId);
        IntBuffer indicesBuffer =  MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(0, indices);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer,  GL_STATIC_DRAW);

        // unbind VAO and VBO buffers
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        MemoryUtil.memFree(positionsBuffer);
        MemoryUtil.memFree(indicesBuffer);
        MemoryUtil.memFree(texCoordsBuffer);

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