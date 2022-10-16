package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.animation.Animation;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IPhysicsObject;
import com.mkkekkonen.spaceshooter.math.MathUtils;

import javax.inject.Inject;

public abstract class AbstractGameObject implements IDrawable, IPhysicsObject {
    protected float width = 0, height = 0, scale = 1;

    protected Physics physics;
    protected Animation animation;

    protected Vector2 origin = new Vector2();
    protected Texture texture;
    protected TextureRegion textureRegion;

    protected State state = State.NORMAL;

    public void update(float deltaTime) {
        if (this.state == State.NORMAL) {
            this.physics.update(deltaTime);
        }
    }

    public void draw(SpriteBatch batch) {
        if (this.state == State.NORMAL) {
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
        } else if (this.state == State.EXPLODING && this.animation != null) {
            this.animation.draw(batch);
        }
    }

    protected void initTexture(Texture texture, float width) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);

        Vector2 widthHeight = MathUtils.getSpriteWidthHeight(this.texture, width);
        this.width = widthHeight.x;
        this.height = widthHeight.y;
    }

    public float getX() {
        return this.physics.getPosition().x;
    }

    public float getY() {
        return this.physics.getPosition().y;
    }

    public Vector2 getPosition() {
        return this.physics.getPosition();
    }
}
