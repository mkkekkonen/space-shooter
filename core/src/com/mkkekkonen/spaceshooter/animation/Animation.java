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

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class Animation implements IDrawable {
    @Inject RandomGenerator randomGenerator;

    private float frameLength, scale, currentTime = 0;
    private boolean started, randomRotation;

    private List<Texture> sprites = new ArrayList<>();
    private List<TextureRegion> textureRegions = new ArrayList<>();

    private AbstractGameObject parent;

    @AssistedInject
    Animation(
            ResourceManager resourceManager,
            @Assisted AbstractGameObject parent,
            @Assisted String[] keys,
            @Assisted("frameLength") Float frameLength,
            @Assisted("scale") Float scale,
            @Assisted Boolean randomRotation
    ) {
        for (String key : keys) {
            Texture sprite = resourceManager.getSprite(key);
            this.sprites.add(sprite);
            this.textureRegions.add(new TextureRegion(sprite));
        }

        this.parent = parent;

        this.frameLength = frameLength != null ? frameLength : 2;
        this.scale = scale != null ? scale : 1;

        this.randomRotation = randomRotation != null ? randomRotation : false;
    }

    public void draw(SpriteBatch batch) {
        if (!this.started) {
            return;
        }

        int currentSpriteIndex = (int) Math.floor(this.currentTime / this.frameLength);

        if (currentSpriteIndex >= this.sprites.size()) {
            this.started = false;
            return;
        }

        Texture sprite = this.sprites.get(currentSpriteIndex);

        batch.draw(
                this.textureRegions.get(currentSpriteIndex),
                this.getX(sprite),
                this.getY(sprite),
                this.getScaledWidth(sprite) / 2,
                this.getScaledHeight(sprite) / 2,
                sprite.getWidth(),
                sprite.getHeight(),
                this.scale,
                this.scale,
                this.getRotation()
        );

        this.currentTime += Gdx.graphics.getDeltaTime();
    }

    private float getScaledWidth(Texture sprite) {
        return sprite.getWidth() * this.scale;
    }

    private float getScaledHeight(Texture sprite) {
        return sprite.getHeight() * this.scale;
    }

    private float getX(Texture sprite) {
        return this.parent.getX() - (this.getScaledWidth(sprite) / 2);
    }

    private float getY(Texture sprite) {
        return this.parent.getY() - (this.getScaledHeight(sprite) / 2);
    }

    private float getRotation() {
        if (this.randomRotation) {
            return this.randomGenerator.getRandomFloat(0, 360);
        }

        return 0;
    }
}
