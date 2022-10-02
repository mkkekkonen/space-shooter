package com.mkkekkonen.spaceshooter.gameobjects.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mkkekkonen.spaceshooter.gameobjects.AbstractGameObject;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Ship {
    float x, y;
    Texture texture;

    @Inject
    Ship(ResourceManager resourceManager) {
        this.x = Gdx.graphics.getWidth() / 2;
        this.y = Gdx.graphics.getHeight() / 2;
        this.texture = resourceManager.getSprite("ship");
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                new TextureRegion(this.texture),
                this.x,
                this.y
        );
    }
}
