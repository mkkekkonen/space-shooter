package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IPhysicsObject;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Ship implements IDrawable, IPhysicsObject {
    @Inject InputManager inputManager;

    float width, height, scale = 1;

    static final float speed = 5;

    Physics physics;

    Vector2 origin;
    Texture texture;

    @Inject
    Ship(ResourceManager resourceManager) {
        this.physics = new Physics(
                Gdx.graphics.getWidth() / 2,
                MathUtils.mToPx(4)
        );

        this.texture = resourceManager.getSprite("ship");

        float[] widthHeight = MathUtils.getSpriteWidthHeight(this.texture, 2);
        this.width = widthHeight[0];
        this.height = widthHeight[1];

        this.origin = new Vector2(
                this.texture.getWidth() / 2,
                this.texture.getHeight() / 2
        );
    }

    @Override
    public void update(float deltaTime) {
        ApplicationType appType = Gdx.app.getType();

        if (appType.equals(ApplicationType.Desktop)) {
            this.handleKeyboardInput();
        } else if (appType.equals(ApplicationType.Android)) {
            this.handleTouchInput();
        }

        this.physics.update(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        Vector2 position = this.physics.getPosition();

        batch.draw(
                new TextureRegion(this.texture),
                position.x - (this.width / 2),
                position.y - (this.height / 2),
                this.width / 2,
                this.height / 2,
                this.width,
                this.height,
                this.scale,
                this.scale,
                0
        );
    }

    private void handleKeyboardInput() {
        if (this.inputManager.isKeyLeftPressed()) {
            this.physics.setVelocity(new Vector2(-speed, 0));
        } else if (this.inputManager.isKeyRightPressed()) {
            this.physics.setVelocity((new Vector2(speed, 0)));
        } else {
            this.physics.setVelocity(new Vector2());
        }
    }

    private void handleTouchInput() {
        Vector2 touchLocation = this.inputManager.getTouchLocation();

        if (touchLocation != null) {
            this.handleTouchVelocity();
        } else {
            this.physics.setVelocity(new Vector2());
        }
    }

    private void handleTouchVelocity() {
        Vector2 currentPosition = this.physics.getPosition();
        Vector2 touchLocation = this.inputManager.getTouchLocation();

        if (touchLocation.x < currentPosition.x) {
            this.physics.setVelocity(new Vector2(-speed, 0));
        } else if (touchLocation.x > currentPosition.x) {
            this.physics.setVelocity(new Vector2(speed, 0));
        } else {
            this.physics.setVelocity(new Vector2());
        }
    }
}
