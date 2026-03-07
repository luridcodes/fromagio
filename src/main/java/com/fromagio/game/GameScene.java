package com.fromagio.game;

import com.fromagio.engine.Engine;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Scene;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.physics.*;


/*
Using "worlds" (TODO find a better name for this
allows resources only to be loaded when a world is loaded instead of loading everything
at once
 */
public class GameScene {
    Player lucas;
    Scene gameScene;
    EvilCheeseman david;

    public GameScene() {
        lucas = new Player();
        david = new EvilCheeseman();
        gameScene = new Scene();
        gameScene.addObject("player", lucas.getGameObject());
        gameScene.addObject("evil cheeseman", david.getGameObject());
    }

    public Scene getScene() {
        return gameScene;
    }

    public void update(long diffTimeMillis) {
        double[] cursorPos = Engine.input().getMouse().getCursorPos();
        lucas.getGameObject().transform().setPosition(
                (long) cursorPos[0],
                (long) cursorPos[1]
        );
    }

    public boolean isGameOver() {
        return Collision.isCollision(david.getGameObject(), lucas.getGameObject());
    }
}

class Player {
    private GameObject playerObject;
    private Texture cheeseTexture;

    Player() {
        cheeseTexture = new Texture("src/resources/textures/cheesepng.png");
        playerObject = new GameObject(50, 50, 100, 100, cheeseTexture);
    }

    public GameObject getGameObject() {
        return playerObject;
    }

}

class EvilCheeseman {
    private GameObject evilObject;
    private Texture evilTexture;

    EvilCheeseman() {
        evilTexture = new Texture("src/resources/textures/evil_cheeseman.png");
        evilObject = new GameObject(50, 50, 100, 100, evilTexture);
    }

    public GameObject getGameObject() {
        return evilObject;
    }


}

