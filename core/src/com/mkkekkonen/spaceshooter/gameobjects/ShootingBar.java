package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.utils.Constants;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ShootingBar extends AbstractGameObject {
    float y = Constants.SHOOTING_BAR_Y;

    @Inject
    ShootingBar(ResourceManager resourceManager) {
        super(
                null,
                resourceManager.getSprite("shootingBarComponent"),
                0.1f
        );
    }

    @Override
    public void update(float deltaTime) {

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
