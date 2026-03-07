package com.fromagio.engine.input;

import org.tinylog.Logger;

public class Input {
    long windowHandle;
    Keyboard keyboard;
    Mouse mouse;

    public Input(long windowHandle) {
        this.windowHandle = windowHandle;
        keyboard = new Keyboard(windowHandle);
        mouse = new Mouse(windowHandle);
        Logger.info("[Input] Input service initialised");
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }
}
