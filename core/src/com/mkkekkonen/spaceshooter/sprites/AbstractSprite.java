package com.mkkekkonen.spaceshooter.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IUpdateable;
import com.mkkekkonen.spaceshooter.utils.TextureWrapper;
import com.mkkekkonen.spaceshooter.utils.Utils;

public abstract class AbstractSprite implements IDrawable, IUpdateable {
    protected Vector2 position = null;
    protected Texture texture = null;
    protected TextureRegion textureRegion = null;

    protected float width = 0;
    protected float height = 0;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                this.textureRegion,
                this.position.x - (this.width / 2),
                this.position.y - (this.height / 2),
                this.width,
                this.height
        );
    }

    protected void initTexture(Texture texture, float width) {
        TextureWrapper textureWrapper = Utils.initTexture(texture, width);

        this.texture = textureWrapper.getTexture();
        this.textureRegion = textureWrapper.getTextureRegion();

        Vector2 widthHeight = textureWrapper.getWidthHeight();
        this.width = widthHeight.x;
        this.height = widthHeight.y;
    }
}
