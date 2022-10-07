package com.mkkekkonen.spaceshooter.math;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.utils.Constants;

public class MathUtils {
    public static float mToPx(float meters) {
        return meters * Constants.PX_PER_M;
    }

    public static Vector2 vecMToPx(Vector2 vector) {
        return new Vector2(
                MathUtils.mToPx(vector.x),
                MathUtils.mToPx(vector.y)
        );
    }

    public static Vector2 vecMulScalar(Vector2 vector, float scalar) {
        return new Vector2(
                vector.x * scalar,
                vector.y * scalar
        );
    }

    public static float[] getSpriteWidthHeight(Texture texture, float spriteWidth) {
        float textureWidth = texture.getWidth();
        float textureHeight = texture.getHeight();

        float width = MathUtils.mToPx(spriteWidth);
        float coefficient = width / textureWidth;
        float height = textureHeight * coefficient;

        return new float[] {width, height};
    }
}
