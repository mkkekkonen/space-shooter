package com.mkkekkonen.spaceshooter.input;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InputManager {
    private boolean keyLeft = false;
    private boolean keyRight = false;
    private boolean shooting = false;

    private Vector2 touchLocation;
    private Vector2 clickLocation;

    @Inject
    InputManager() {}

    public void getInput() {
        ApplicationType appType = Gdx.app.getType();

        if (appType.equals(ApplicationType.Desktop)) {
            this.getKeyboardInput();
            this.getMouseInput();
        } else if (appType.equals(ApplicationType.Android)) {
            this.getTouchInput();
        }
    }

    private void getKeyboardInput() {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT),
                rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT),
                spacePressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);

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

        if (spacePressed) {
            this.shooting = true;
        } else {
            this.shooting = false;
        }
    }

    private void getTouchInput() {
        this.touchLocation = this.getConditionalInputLocation(Gdx.input.isTouched());
        this.clickLocation = this.getConditionalInputLocation(Gdx.input.justTouched());
    }

    private void getMouseInput() {
        this.clickLocation = this.getConditionalInputLocation(
                Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)
        );
    }

    private float getTouchY() {
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }

    private Vector2 getInputLocation() {
        return new Vector2(
                Gdx.input.getX(),
                this.getTouchY()
        );
    }

    private Vector2 getConditionalInputLocation(boolean condition) {
        return condition ? this.getInputLocation() : null;
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

    public Vector2 getClickLocation() {
        return clickLocation;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
