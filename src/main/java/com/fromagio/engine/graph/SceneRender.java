package com.fromagio.engine.graph;

import com.fromagio.engine.scene.Scene;

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

    private ShaderProgram shaderProgram;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        shaderProgram.validate();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    /**
     *
     * @param scene
     */
    public void render(Scene scene) {
        shaderProgram.bind();

        scene.getMeshMap().values().forEach(mesh -> {
                    glBindVertexArray(mesh.getVaoID());
                    glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
                }
        );

        glBindVertexArray(0);

        shaderProgram.unbind();
    }
}