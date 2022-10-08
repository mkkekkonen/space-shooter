package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IPhysicsObject;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Asteroid extends AbstractGameObject {
    @Inject
    Asteroid(ResourceManager resourceManager, RandomGenerator randomGenerator) {
        super(
                new Physics(
                        new Vector2(
                                randomGenerator.getRandomFloat(25, Gdx.graphics.getWidth() - 25),
                                Gdx.graphics.getHeight() + 50
                        ),
                        new Vector2(
                                0,
                                randomGenerator.getRandomFloat(-7, -3)
                        ),
                        randomGenerator.getRandomFloat(-15, 15)
                ),
                resourceManager.getSprite("roid" + randomGenerator.getRandomInt(1, 6 + 1)),
                randomGenerator.getRandomFloat(0.75f, 2.5f)
        );
    }

    @Override
    public void update(float deltaTime) {
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
                1,
                1,
                this.physics.getRotationDeg()
        );
    }

    public float getY() {
        return this.physics.getPosition().y;
    }
}
