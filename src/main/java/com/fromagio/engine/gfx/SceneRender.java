package com.fromagio.engine.gfx;

import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.world.World;
import org.joml.Matrix4f;

import java.util.*;

import static org.lwjgl.opengl.GL30.*;

/**
 * Loads a list of shader files and creates an instance of {@link ShaderProgram} to compile
 * the shaders for rendering
 *
 * <p> In the future, this class should handle rendering of different Scenes, such as the
 * main menu scene, game scene, etc. Consider implementing this as an interface instead </p>
 */
public class SceneRender {

    private final ShaderProgram shaderProgram;
    private Matrix4f viewMatrix;
    private Matrix4f projectionMatrix;
    private UniformMap uniformMap;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("src/resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        createUniforms();
    }

    private void createUniforms() {
        viewMatrix = new Matrix4f().identity();
        projectionMatrix = new Matrix4f().identity();

        uniformMap = new UniformMap(shaderProgram.getProgramID());
        uniformMap.createUniform("view");
        uniformMap.createUniform("projection");
        uniformMap.createUniform("model");
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(World world) {
        shaderProgram.bind();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


        uniformMap.setUniform("projection", projectionMatrix);
        uniformMap.setUniform("view", viewMatrix);

        // Render each game object
        for (GameObject obj : world.getObjectMap().values()) {
            // Get the transform matrix from the GameObject
            Matrix4f modelMatrix = obj.getTransform().getMatrix();
            uniformMap.setUniform("model", modelMatrix);

            glActiveTexture(GL_TEXTURE0); // sets the active texture unit - we only need one unit for one texture per draw call for now
            Texture texture = obj.getTexture();
            texture.bind();

            // Draw the mesh
            glBindVertexArray(obj.getMesh().getVaoID());
            glDrawElements(GL_TRIANGLES, obj.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);
        }

        glBindVertexArray(0);
        shaderProgram.unbind();
    }
}