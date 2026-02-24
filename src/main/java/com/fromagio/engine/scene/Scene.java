package com.fromagio.engine.scene;

import com.fromagio.engine.graph.Mesh;

import java.util.*;

/**
 * Contains a {@link HashMap} of {@link Mesh} objects to be used for rendering
 */
public class Scene {
    private Map<String, Mesh> meshMap;

    public Scene() {
        meshMap = new HashMap<>();
    }

    public void addMesh(String meshID, Mesh mesh) {
        meshMap.put(meshID, mesh);
    }

    public void cleanup() {
        meshMap.values().forEach(Mesh::cleanup);
    }

    public Map<String, Mesh> getMeshMap() {
        return meshMap;
    }

}