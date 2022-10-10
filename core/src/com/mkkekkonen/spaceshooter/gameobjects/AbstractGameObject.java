package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IPhysicsObject;
import com.mkkekkonen.spaceshooter.math.MathUtils;

import javax.inject.Inject;

public abstract class AbstractGameObject implements IDrawable, IPhysicsObject {
    float width, height, scale;

    Physics physics;

    Vector2 origin;
    Texture texture;
    TextureRegion textureRegion;

    protected AbstractGameObject(Physics physics, Vector2 origin, Texture texture, float width) {
        this.physics = physics;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);

        float[] widthHeight = MathUtils.getSpriteWidthHeight(this.texture, width);
        this.width = widthHeight[0];
        this.height = widthHeight[1];

        this.origin = origin;

        this.scale = 1;
    }

    protected AbstractGameObject(Physics physics, Texture texture, float width) {
        this(
                physics,
                new Vector2(
                        texture.getWidth() / 2,
                        texture.getHeight() / 2
                ),
                texture,
                width
        );
    }

    public void update(float deltaTime) {
        this.physics.update(deltaTime);
    }

    public void draw(SpriteBatch batch) {
        Vector2 position = this.physics.getPosition();

        batch.draw(
                this.textureRegion,
                position.x - (this.width / 2),
                position.y - (this.height / 2),
                this.width / 2,
                this.height / 2,
                this.width,
                this.height,
                this.scale,
                this.scale,
                this.physics.getRotationDeg()
        );
    }

    public float getX() {
        return this.physics.getPosition().x;
    }

    public float getY() {
        return this.physics.getPosition().y;
    }
}
