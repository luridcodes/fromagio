package com.fromagio.game;

import com.fromagio.engine.Engine;
import com.fromagio.engine.fromapi.GameObject;
import com.fromagio.engine.fromapi.Scene;
import com.fromagio.engine.fromapi.SpriteSheet;
import com.fromagio.engine.fromapi.Texture;
import com.fromagio.engine.physics.*;
import com.fromagio.game.spritesheets.MCBlocks;


/*
Using "worlds" (TODO find a better name for this
allows resources only to be loaded when a world is loaded instead of loading everything
at once
 */
public class GameScene {
    Player lucas;
    Scene gameScene;
    EvilCheeseman david;
    static SpriteSheet sheet = new SpriteSheet("src/resources/textures/MCBlocksBlackOutline.png");

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

    public void update(long DiffTimeMillis) {
        double[] cursorPos = Engine.input().getMouse().getCursorPos();
        lucas.getGameObject().transform().setPosition(
                (long) cursorPos[0],
                (long) cursorPos[1]
        );
    }

    public boolean isGameOver() {
        return Collision.isCollision(
                david.getGameObject(), lucas.getGameObject());
    }
}

class Player {
    private final GameObject playerObject;

    Player() {
        Texture cheeseTexture = new Texture(
                "src/resources/textures/cheesepng.png");
        playerObject = new GameObject(
                50, 50, 100, 100, cheeseTexture);
    }

    public GameObject getGameObject() {
        return playerObject;
    }

}

class EvilCheeseman {
    private final GameObject evilObject;

    EvilCheeseman() {
        int[] params = {64,0,32,32};
        Texture evilTexture = GameScene.sheet.getTexture(MCBlocks.GRASS_DIRT);
        evilObject = new GameObject(50, 50, 100, 100, evilTexture);
    }

    public GameObject getGameObject() {
        return evilObject;
    }


}

