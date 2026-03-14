package com.fromagio.engine.fromapi;

import org.lwjgl.system.MemoryStack;
import org.tinylog.Logger;

import java.nio.*;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

/**
 * Contains all necessary information to load an OpenGL texture
 * for a {@link GameObject} object.
 */
public class Texture {

    private int textureId;
    private String texturePath;
    private int width;
    private int height;

    public Texture(int width, int height, ByteBuffer buf) {
        this.texturePath = "";
        generateTexture(width, height, buf);
    }

    public Texture(String texturePath) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            this.texturePath = texturePath;
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer buf = stbi_load(texturePath, w, h, channels, 4);
            if (buf == null) {
                throw new RuntimeException("Image file [" + texturePath + "] not loaded: " + stbi_failure_reason());
            }

            this.width = w.get();
            this.height = h.get();

            generateTexture(this.width, this.height, buf);

            stbi_image_free(buf);

            Logger.info("[Texture] New texture [{}] loaded", texturePath);
        }
    }

    /**
     * Allows textures to be loaded from a spritesheet based on their
     * coordinates in pixels, width and height.
     *
     * <p> The parameters are loaded in an integer array to make it easier to
     * create static variables for re-used textures </p>
     * @param texturePath string containing relative or absolute path to the
     *                    spritesheet file
     * @param params an array of 4 integers containing (in order) x,y,width, and height
     * @deprecated use {@link SpriteSheet} instead for better performance
     */
    public Texture(String texturePath, int[] params) {
        this.texturePath = texturePath;

        int spriteX = params[0];
        int spriteY = params[1];
        int spriteWidth =  params[2];
        int spriteHeight = params[3];

        if  (params.length != 4) {
            throw new RuntimeException("Texture parameters array length [" + params.length + "] not correct.");
        }

        try(MemoryStack stack = MemoryStack.stackPush()) {
            this.texturePath = texturePath;
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer fullSheet = stbi_load(texturePath, w, h, channels, 4);
            if(fullSheet == null) {
                throw new RuntimeException("Image file [" + texturePath + "] not loaded: " + stbi_failure_reason());
            }

            int sheetWidth = w.get();
            int sheetHeight = h.get();

            // validate parameters
            if(spriteX < 0 || spriteY < 0 || spriteX + spriteWidth > sheetWidth
                    || spriteY + spriteHeight > sheetHeight) {
                stbi_image_free(fullSheet);
                throw new RuntimeException("Sprite coordinates out of bounds");
            }

            ByteBuffer spriteBuffer = stack.malloc(spriteWidth * spriteHeight * 4);

            for (int row = 0; row < spriteHeight; row++) {
                int sourcePos = ((spriteY + row) * sheetWidth + spriteX) * 4;
                int destPos = row * spriteWidth * 4;

                fullSheet.clear();
                fullSheet.position(sourcePos);
                fullSheet.limit(sourcePos + (spriteWidth * 4));

                spriteBuffer.position(destPos);
                spriteBuffer.put(fullSheet);
            }

            // resets position to 0 and sets limit to the current pos (stores info written)
            spriteBuffer.flip();

            this.width = spriteWidth;
            this.height = spriteHeight;

            generateTexture(this.width, this.height, spriteBuffer);

            stbi_image_free(fullSheet);
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public void cleanup() {
        glDeleteTextures(textureId);
    }

    private void generateTexture(int width, int height, ByteBuffer buf) {
        textureId = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, textureId);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
                GL_RGBA, GL_UNSIGNED_BYTE, buf);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public String getTexturePath() {
        return texturePath;
    }
}