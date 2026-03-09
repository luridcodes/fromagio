package com.fromagio.engine.gfx;

import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.fromapi.Scene;
import org.joml.Matrix4f;
import org.tinylog.Logger;

import java.util.*;

import static org.lwjgl.opengl.GL30.*;

/**
 * Loads a list of shader files and creates an instance of {@link ShaderProgram}
 * to compile the shaders for rendering
 *
 */
public class SceneRender {

    private final ShaderProgram shaderProgram;
    private Matrix4f viewMatrix;
    private Matrix4f projectionMatrix;
    private UniformMap uniformMap;
    private int screenWidth, screenHeight;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        createUniforms();
    }

    public void updateDimensions(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        projectionMatrix = new Matrix4f().ortho(
                0, screenWidth, screenHeight, 0, -1, 1
        );
        Logger.info("[SceneRender] Screen Resize with width [{}] and height [{}]", screenWidth, screenHeight);
    }

    private void createUniforms() {
        viewMatrix = new Matrix4f().identity();

        uniformMap = new UniformMap(shaderProgram.getProgramID());
        uniformMap.createUniform("view");
        uniformMap.createUniform("projection");
        uniformMap.createUniform("model");
        uniformMap.createUniform("txtSampler");
        Logger.info("[SceneRender] Uniforms created: [{}]", uniformMap.getUniforms());
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene world) {
        shaderProgram.bind();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        uniformMap.setUniform("projection", projectionMatrix);
        uniformMap.setUniform("view", viewMatrix);
        uniformMap.setUniform("txtSampler", 0);

        // Render each game object
        for (GameObject obj : world.getObjectMap().values()) {
            // Get the transform matrix from the GameObject
            Matrix4f modelMatrix = obj.transform().getMatrix();
            uniformMap.setUniform("model", modelMatrix);

            glActiveTexture(GL_TEXTURE0); // sets the active texture unit - we only need one unit for one texture per draw call for now
            Texture texture = obj.texture();
            texture.bind();

            // Draw the mesh
            glBindVertexArray(obj.getMesh().getVaoID());
            glDrawElements(GL_TRIANGLES, obj.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);
        }

        glBindVertexArray(0);
        shaderProgram.unbind();
    }
}