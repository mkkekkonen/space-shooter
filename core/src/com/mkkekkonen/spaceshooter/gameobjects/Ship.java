package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.animation.ExplosionAnimation;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.geometry.ShapeRendererWrapper;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IExplosionFactory;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.utils.DebugWrapper;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Ship extends AbstractGameObject {
    @Inject InputManager inputManager;
    @Inject ShapeRendererWrapper shapeRendererWrapper;

    static final float speed = 5;

    @Inject
    Ship(ResourceManager resourceManager, IExplosionFactory explosionFactory) {
        this.physics = new Physics(
                Gdx.graphics.getWidth() / 2,
                MathUtils.mToPx(4)
        );
        this.initTexture(resourceManager.getSprite("ship"),2);
        this.explosion = explosionFactory.create(this);
    }

    @Override
    public void update(float deltaTime) {
        if (this.state == State.NORMAL) {
            ApplicationType appType = Gdx.app.getType();

            if (appType.equals(ApplicationType.Desktop)) {
                this.handleKeyboardInput();
            } else if (appType.equals(ApplicationType.Android)) {
                this.handleTouchInput();
            }
        }

        super.update(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        if (this.state == State.NORMAL && DebugWrapper.DEBUG) {
            batch.end();

//                if (this.collided) this.shapeRendererWrapper.setColor(1, 0, 0, 1);
            Vector2[] points = this.getTriangleVectors();
            this.shapeRendererWrapper.drawTriangle(points[0], points[1], points[2]);
//                if (this.collided) this.shapeRendererWrapper.setColor(0, 1, 0, 1);

            batch.begin();
        }
    }

    public Vector2[] getTriangleVectors() {
        float x = this.getX();
        float y = this.getY();

        float widthHalved = this.width / 2;
        float heightHalved = this.height / 2;

        float topY = y + heightHalved;

        Vector2 topCenter = new Vector2(x, topY);

        float bottomLeftX = x - widthHalved;
        float bottomY = y - heightHalved;

        Vector2 bottomLeft = new Vector2(bottomLeftX, bottomY);

        float bottomRightX = x + widthHalved;

        Vector2 bottomRight = new Vector2(bottomRightX, bottomY);

        return new Vector2[] {topCenter, bottomRight, bottomLeft};
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
