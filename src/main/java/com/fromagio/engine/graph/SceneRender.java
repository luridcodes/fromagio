package com.fromagio.engine.graph;

import com.fromagio.engine.world.World;

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
     * @param world
     */
    public void render(World world) {
        shaderProgram.bind();

        world.getObjectMap().values().forEach(object -> {
                    glBindVertexArray(object.getMesh().getVaoID());
                    // drawElements because we are using indices and reading from an array
                    glDrawElements(GL_TRIANGLES, object.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);
                }
        );

        glBindVertexArray(0);

        shaderProgram.unbind();
    }
}