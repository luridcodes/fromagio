package com.fromagio.engine.fromapi;

// each colour can be initialised using RGBA
public class Colour {
    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    public static float[] RED = {
            1,0,0,1,
    };
    public static float[] GREEN = {
            0,1,0,1,
    };

    public Colour(float[] RGBA)  {
        red = RGBA[0];
        green = RGBA[1];
        blue = RGBA[2];
        alpha = RGBA[3];
    }

    public float[] getColour() {
        return new float[]{red, green, blue, alpha};
    }

    public float getRed() {
        return red;
    }
    public float getGreen() {
        return green;
    }
    public float getBlue() {
        return blue;
    }
    public float getAlpha() {
        return alpha;
    }
}
