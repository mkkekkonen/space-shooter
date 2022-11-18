package com.mkkekkonen.spaceshooter.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TextureWrapper {
    private Texture texture;
    private TextureRegion textureRegion;
    private Vector2 widthHeight;

    public TextureWrapper() {}

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public Vector2 getWidthHeight() {
        return widthHeight;
    }

    public void setWidthHeight(Vector2 widthHeight) {
        this.widthHeight = widthHeight;
    }
}
