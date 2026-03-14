package com.fromagio.engine.fromapi;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

public class SpriteSheet {
    private final ByteBuffer fullSheetBuffer;
    private final String spriteSheetPath;
    private final int sheetWidth;
    private final int sheetHeight;

    public SpriteSheet(String spriteSheetPath) {
        this.spriteSheetPath = spriteSheetPath;
        // Texture fullSheetTex = new Texture(spriteSheetPath);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer buf = stbi_load(spriteSheetPath, w, h, channels, 4);
            if (buf == null) {
                throw new RuntimeException("Image file [" + spriteSheetPath + "] not loaded: " + stbi_failure_reason());
            }

            this.sheetWidth = w.get();
            this.sheetHeight = h.get();

            fullSheetBuffer = MemoryUtil.memAlloc(buf.capacity());
            // rewind resets position to 0 for copying
            fullSheetBuffer.put(buf.duplicate().rewind());
            fullSheetBuffer.flip();

            stbi_image_free(buf);
        }
    }


    /**
     * Overloaded method to allow passing of an integer array instead of
     * individual parameters to allow for static definitions of texture params
     * in the game layer
     *
     * <p> e.g.
     * {@code static int[] TEXTURE_NAME = {0,0,32,32}
     * spriteSheet.getTexture(TEXTURE_NAME)}
     * </p>
     *
     * @param params array of 4 integers containing (in order) x, y, width and
     *               height
     * @return {@link Texture} instance of the desired texture
     */
    public Texture getTexture(int[] params) {
        return getTexture(params[0], params[1], params[2], params[3]);
    }

    public Texture getTexture(int x, int y, int width, int height) {
        // i need to extract the bytebuffer containing the data for the sprite only

        // each pixel contains 4 bytes
        ByteBuffer spriteBuffer = MemoryUtil.memAlloc(width * height * 4);

        if (x < 0 || y < 0 || x + width > sheetWidth || y + height > sheetHeight) {
            throw new RuntimeException("Sprite coordinates out of bounds");
        }

        for (int row = 0; row < height; row++) {
            int sourcePos = ((y + row) * sheetWidth + x) * 4;
            int destPos = row * width * 4;

            fullSheetBuffer.clear();
            fullSheetBuffer.position(sourcePos);
            fullSheetBuffer.limit(sourcePos + (width * 4));

            spriteBuffer.position(destPos);
            spriteBuffer.put(fullSheetBuffer);
        }

        spriteBuffer.flip();

        return new Texture(width, height, spriteBuffer);
    }

    public String getSpriteSheetPath() {
        return spriteSheetPath;
    }
}
