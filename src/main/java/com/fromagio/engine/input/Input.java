package com.fromagio.engine.input;

import org.tinylog.Logger;

/**
 * Contains static methods which can be accessed through the
 * {@link com.fromagio.engine.Engine} for the game layer to access keyboard
 * and mouse information through the input service
 *
 * @see com.fromagio.engine.Engine
 */
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
