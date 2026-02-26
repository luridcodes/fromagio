package com.fromagio.engine.gfx;

import com.fromagio.engine.fromapi.GameObject;
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
    private final Matrix4f viewMatrix;
    private final Matrix4f projectionMatrix;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        shaderProgram.validate();

        /*
        viewMatrix controls the view to offset everything from the perspective of the camera
        projectionMatrix is just ortho - don't change for 2D????
         */
        viewMatrix = new Matrix4f().identity();
        projectionMatrix = new Matrix4f().identity();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(World world) {
        shaderProgram.bind();

        // Set view and projection
        shaderProgram.setUniform("view", viewMatrix);
        shaderProgram.setUniform("projection", projectionMatrix);

        // Render each game object
        for (GameObject obj : world.getObjectMap().values()) {
            // Get the transform matrix from the GameObject
            Matrix4f modelMatrix = obj.getTransform().getMatrix();
            shaderProgram.setUniform("model", modelMatrix);

            // Draw the mesh
            glBindVertexArray(obj.getMesh().getVaoID());
            glDrawElements(GL_TRIANGLES, obj.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);
        }

        glBindVertexArray(0);
        shaderProgram.unbind();
    }
}