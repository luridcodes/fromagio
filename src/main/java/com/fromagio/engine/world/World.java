package com.fromagio.engine.world;

import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.graph.Mesh;

import java.util.*;

/**
 * Contains a {@link HashMap} of {@link Mesh} objects to be used for rendering
 */
public class World {
    private final Map<String, GameObject> gameObjectMap;

    public World() {
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