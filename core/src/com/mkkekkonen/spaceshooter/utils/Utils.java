package com.mkkekkonen.spaceshooter.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.math.MathUtils;

public class Utils {
    public static Object getNextElementInArray(Object[] array, int currentIndex) {
        if (array.length == 0) {
            return null;
        }

        if (currentIndex < array.length - 1) {
            return array[currentIndex + 1];
        }

        return array[0];
    }

    public static TextureWrapper initTexture(Texture texture, float width) {
        Vector2 widthHeight = MathUtils.getSpriteWidthHeight(texture, width);

        TextureWrapper textureWrapper = new TextureWrapper();
        textureWrapper.setTexture(texture);
        textureWrapper.setTextureRegion(new TextureRegion(texture));
        textureWrapper.setWidthHeight(widthHeight);

        return textureWrapper;
    }
}
