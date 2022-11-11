package com.mkkekkonen.spaceshooter.utils;

import com.badlogic.gdx.Gdx;
import com.mkkekkonen.spaceshooter.math.MathUtils;

public class Constants {
    public static final int
            MENU_FONT_SIZE = 34;

    public static final float
        PX_PER_M = Gdx.graphics.getWidth() / 20,
        SHOOTING_BAR_Y = MathUtils.mToPx(20);
}
