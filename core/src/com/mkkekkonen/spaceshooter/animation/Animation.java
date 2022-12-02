package com.mkkekkonen.spaceshooter.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mkkekkonen.spaceshooter.gameobjects.AbstractGameObject;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

public class Animation implements IDrawable {
    private float frameLength, scale, currentTime = 0, rotation = 0;
    protected boolean started;

    private List<Texture> sprites = new ArrayList<>();
    private List<TextureRegion> textureRegions = new ArrayList<>();

    private AbstractGameObject parent;

    Animation(
            ResourceManager resourceManager,
            RandomGenerator randomGenerator,
            AbstractGameObject parent,
            String[] keys,
            Float frameLength,
            Float scale,
            Boolean randomRotation
    ) {
        for (String key : keys) {
            Texture sprite = resourceManager.getSprite(key);
            this.sprites.add(sprite);
            this.textureRegions.add(new TextureRegion(sprite));
        }

        this.parent = parent;

        this.frameLength = frameLength != null ? frameLength : 2;
        this.scale = scale != null ? scale : 1;

        if (randomRotation) {
            this.rotation = randomGenerator.getRandomFloat(0, 360);
        }
    }

    public void draw(SpriteBatch batch) {
        if (!this.started) {
            return;
        }

        int currentSpriteIndex = this.getCurrentSpriteIndex();

        if (currentSpriteIndex >= this.sprites.size()) {
            return;
        }

        Texture sprite = this.sprites.get(currentSpriteIndex);

        batch.draw(
                this.textureRegions.get(currentSpriteIndex),
                this.getX() - this.getScaledWidth(sprite) / 2,
                this.getY() - this.getScaledHeight(sprite) / 2,
                this.getScaledWidth(sprite) / 2,
                this.getScaledHeight(sprite) / 2,
                sprite.getWidth(),
                sprite.getHeight(),
                1,
                1,
                this.rotation
        );
    }

    public void update() {
        if (!this.started) {
            return;
        }

        int currentSpriteIndex = this.getCurrentSpriteIndex();

        if (currentSpriteIndex >= this.sprites.size()) {
            this.started = false;
            return;
        }

        this.currentTime += Gdx.graphics.getDeltaTime();
    }

    public boolean isStarted() {
        return this.started;
    }

    public void start() {
        this.started = true;
    }

    private int getCurrentSpriteIndex() {
        return (int) Math.floor(this.currentTime / this.frameLength);
    }

    private float getScaledWidth(Texture sprite) {
        return sprite.getWidth() * this.scale;
    }

    private float getScaledHeight(Texture sprite) {
        return sprite.getHeight() * this.scale;
    }

    private float getX() {
        return this.parent.getX();
    }

    private float getY() {
        return this.parent.getY();
    }
}
