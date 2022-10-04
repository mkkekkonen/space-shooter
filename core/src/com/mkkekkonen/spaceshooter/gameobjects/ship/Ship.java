package com.mkkekkonen.spaceshooter.gameobjects.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Ship implements IDrawable {
    float x, y, width, height, scale = 1;

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

        float textureWidth = this.texture.getWidth();
        float textureHeight = this.texture.getHeight();

        this.width = MathUtils.mToPx(2);
        float coefficient = this.width / textureWidth;
        this.height = textureHeight * coefficient;

        this.origin = new Vector2(textureWidth / 2, textureHeight / 2);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                new TextureRegion(this.texture),
                this.x - (this.width / 2),
                this.y - (this.height / 2),
                this.width / 2,
                this.height / 2,
                this.width,
                this.height,
                this.scale,
                this.scale,
                0
        );
    }
}
