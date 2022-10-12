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
    }

    public float getY() {
        return this.physics.getPosition().y;
    }
}
