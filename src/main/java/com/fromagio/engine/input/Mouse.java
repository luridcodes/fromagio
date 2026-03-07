package com.fromagio.engine.input;

import com.fromagio.engine.Engine;
import com.fromagio.engine.Window;
import org.lwjgl.BufferUtils;
import org.tinylog.Logger;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class Mouse {
    long windowHandle;
    DoubleBuffer xPosBuf, yPosBuf;

    public Mouse(long windowHandle) {
        this.windowHandle = windowHandle;
        xPosBuf = BufferUtils.createDoubleBuffer(1);
        yPosBuf = BufferUtils.createDoubleBuffer(1);
        Logger.info("[input.Mouse] Mouse service initialised successfully");
    }

    public double[] getCursorPos() {
        xPosBuf.clear();
        yPosBuf.clear();
        glfwGetCursorPos(windowHandle, xPosBuf, yPosBuf);
        return new double[] { xPosBuf.get(), yPosBuf.get()};
    }
}
