package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.graphics.Texture;
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

    protected AbstractGameObject(Physics physics, Vector2 origin, Texture texture, float width) {
        this.physics = physics;
        this.texture = texture;

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
}
