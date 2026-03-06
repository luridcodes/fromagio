package com.fromagio.engine.gfx;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import org.tinylog.Logger;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class UniformMap {
    private final int programID;
    private Map<String, Integer> uniformsMap;

    public UniformMap(int programID) {
        this.programID = programID;
        uniformsMap = new HashMap<>();
    }

    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(programID, uniformName);
        if (uniformLocation < 0) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "] in shader program [" +
                    programID + "]");
        }
        uniformsMap.put(uniformName, uniformLocation);
        Logger.info("New Uniform [{}] created", uniformName);
    }

    private int getUniformLocation(String uniformName) {
        Integer location = uniformsMap.get(uniformName);
        if (location == null) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "]");
        }
        return location.intValue();
    }

    public void setUniform(String uniformName, Matrix4f matrix) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix4fv(getUniformLocation(uniformName), false, matrix.get(stack.mallocFloat(16)));
        }
        // Logger.info("Uniform [{}] set", uniformName);
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(getUniformLocation(uniformName), value);
        // Logger.info("Uniform [{}] set", uniformName);
    }

    public Map<String, Integer> getUniforms() {
        return uniformsMap;
    }
}
