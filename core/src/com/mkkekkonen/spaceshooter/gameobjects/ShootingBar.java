package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ShootingBar implements IDrawable {
    float y = MathUtils.mToPx(8);

    float width, height, scale;

    Texture texture;

    @Inject
    ShootingBar(ResourceManager resourceManager) {
        this.texture = resourceManager.getSprite("shootingBarComponent");

        float[] widthHeight = MathUtils.getSpriteWidthHeight(this.texture, 0.1f);
        this.width = widthHeight[0];
        this.height = widthHeight[1];
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (float x = 0; x < Gdx.graphics.getWidth(); x += this.width) {
            batch.draw(
                    new TextureRegion(this.texture),
                    x,
                    this.y,
                    this.width,
                    this.height
            );
        }
    }
}
