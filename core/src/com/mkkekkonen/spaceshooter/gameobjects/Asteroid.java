package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.geometry.ShapeRendererWrapper;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IExplosionFactory;
import com.mkkekkonen.spaceshooter.interfaces.IPhysicsObject;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.utils.DebugWrapper;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Asteroid extends AbstractGameObject {
    @Inject ShapeRendererWrapper shapeRendererWrapper;

    @Inject
    Asteroid(
            ResourceManager resourceManager,
            RandomGenerator randomGenerator,
            IExplosionFactory explosionFactory
    ) {
        Vector2 initialPosition = new Vector2(
                randomGenerator.getRandomFloat(25, Gdx.graphics.getWidth() - 25),
                Gdx.graphics.getHeight() + 50
        );
        Vector2 initialVelocity = new Vector2(
                0,
                randomGenerator.getRandomFloat(-7, -3)
        );
        float angularVelocity = randomGenerator.getRandomFloat(-15, 15);

        this.physics = new Physics(initialPosition, initialVelocity, angularVelocity);

        Texture texture = resourceManager.getSprite("roid" + randomGenerator.getRandomInt(1, 6 + 1));
        float width = randomGenerator.getRandomFloat(0.75f, 2.5f);
        this.initTexture(texture, width);

        this.explosion = explosionFactory.create(this);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        if (this.state == State.NORMAL) {
            if (DebugWrapper.DEBUG) {
                batch.end();

                //            if (this.collided) this.shapeRendererWrapper.setColor(1, 0, 0, 1);
                this.shapeRendererWrapper.drawCircle(
                        new Vector2(this.getX(), this.getY()),
                        this.width / 2
                );
                //            if (this.collided) this.shapeRendererWrapper.setColor(0, 1, 0, 1);

                batch.begin();
            }
        }
    }

    public float getRadius() {
        return this.width / 2;
    }

    public void setState(State state) {
        this.state = state;
    }
}
