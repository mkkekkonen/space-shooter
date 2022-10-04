package com.mkkekkonen.spaceshooter.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InputManager {
    private boolean keyLeft;
    private boolean keyRight;
    private Vector2 touchLocation;

    @Inject
    InputManager() {}

    public void getInput() {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT),
                rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if (leftPressed) {
            this.keyLeft = true;
        } else {
            this.keyLeft = false;
        }

        if (!this.keyLeft && rightPressed) {
            this.keyRight = true;
        } else if (!rightPressed) {
            this.keyRight = false;
        }
    }

    public boolean isKeyLeftPressed() {
        return keyLeft;
    }

    public boolean isKeyRightPressed() {
        return keyRight;
    }

    public Vector2 getTouchLocation() {
        return touchLocation;
    }
}
