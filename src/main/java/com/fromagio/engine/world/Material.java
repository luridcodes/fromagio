package com.fromagio.engine.world;

import com.fromagio.engine.graph.Mesh;

import java.util.*;
/**
 * Each material corresponds to one texture; multiple meshes can be of
 * the same material and therefore use the same texture
 */
public class Material {
    private List<Mesh> meshList;
    private String texturePath;

    public Material() {
        meshList = new ArrayList<>();
    }

    public void cleanup() {
        meshList.forEach(Mesh::cleanup);
    }

    public List<Mesh> getMeshList() {
        return meshList;
    }

    public String  getTexturePath() {
        return texturePath;
    }
    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }
}
