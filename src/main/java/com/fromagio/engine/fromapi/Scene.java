package com.fromagio.engine.fromapi;

import com.fromagio.engine.graph.Mesh;
import org.tinylog.Logger;

import java.util.*;

/**
 * Contains a {@link HashMap} of {@link Mesh} objects to be used for rendering
 */
public class Scene {
    private final Map<String, GameObject> gameObjectMap;

    public Scene() {
        gameObjectMap = new HashMap<>();
    }

    public void addObject(String meshID, GameObject gameObject) {
        gameObjectMap.put(meshID, gameObject);
    }

    public void cleanup() {
        gameObjectMap.forEach((key, gameObject) -> {gameObject.getMesh().cleanup();});
    }

    public Map<String, GameObject> getObjectMap() {
        return gameObjectMap;
    }

    public String[] getObjectIDs() {
        return gameObjectMap.keySet().toArray(new String[0]);
    }

}