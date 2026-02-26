package com.fromagio.engine.gfx;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL30;
import java.util.*;
import static org.lwjgl.opengl.GL30.*;

import com.fromagio.engine.Utils;

public class ShaderProgram {
    private final int programID;

    /**
     * Creates a new shader program from provided list of shader modules
     *
     * <p> Iterates through the list to parse the shader code to compile
     * and link the code into the created shader program. </p>
     * @param shaderModuleDataList List of {@link ShaderModuleData} (file path + type)
     * @throws RuntimeException if program fails to be created
     */
    public ShaderProgram(List<ShaderModuleData> shaderModuleDataList) {
        programID = glCreateProgram();
        if(programID == 0) {
            throw new RuntimeException("Failed to create shader program");
        }

        List<Integer> shaderIDList = new ArrayList<>();
        for(ShaderModuleData s : shaderModuleDataList) {
            int temp = createShader(Utils.readFile(s.shaderFile), s.shaderType);
            shaderIDList.add(temp);
        }

        linkProgram(shaderIDList);
    }

    /**
     * Creates OpenGL shader module, compiles shader, and attaches shader
     * to the current programID.
     *
     * @param shaderCode String parsed from a shader file
     * @param shaderType OpenGL shaderType
     * @return integer corresponding to openGL shaderID
     * @throws RuntimeException if shader module fails to be created or the shader
     * fails to compile
     */
    protected int createShader(String shaderCode, int shaderType) {
        int shaderID = glCreateShader(shaderType);
        if(shaderID == 0) {
            throw new RuntimeException("Failed to create shader " + shaderCode);
        }
            glShaderSource(shaderID, shaderCode);
            glCompileShader(shaderID);

            if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == 0) {
                throw new RuntimeException("Failed to compile shader " + shaderCode);
            }

            glAttachShader(programID, shaderID);
            return shaderID;
    }

    private void linkProgram(List<Integer> shaderIDList) {
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == 0) {
            throw  new RuntimeException("Failed to link program " + programID);
        }

        shaderIDList.forEach(s -> glDetachShader(programID, s));
        shaderIDList.forEach(GL30::glDeleteShader);
    }

    public int getProgramID() {
        return programID;
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        // sets the current OpenGL program to nothing
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programID != 0) glDeleteProgram(programID);
    }

    public void validate() {
        glValidateProgram(programID);
        if(glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) {
            throw new RuntimeException("Failed to validate program " + programID);
        }
    }

    /**
     * Used to set the uniforms for the matrices for transform, camera and projection
     * @param name name of the mat4 uniform in the shader
     * @param matrix the matrix to be set to the name
     * @throws RuntimeException if uniform location does not exist
     */
    public void setUniform(String name, Matrix4f matrix) {
        int location = glGetUniformLocation(programID, name);
        if (location != -1) {
            float[] buffer = new float[16];
            matrix.get(buffer);
            glUniformMatrix4fv(location, false, buffer);
        } else throw new RuntimeException("Failed to set uniform " + name);
    }

    public record ShaderModuleData(String shaderFile, int shaderType){}
}
