package com.mkkekkonen.spaceshooter.utils;

import com.mkkekkonen.spaceshooter.math.MathUtils;

public class Constants {
    public static final int
            LARGE_FONT_SIZE = Constants.scaleHeight(15),
            SMALL_FONT_SIZE = Constants.scaleHeight(20),
            SCORE_FONT_SIZE = Constants.scaleHeight(30);

    public static final float
        PX_PER_M = MathUtils.scaleWidth(20),
        SHOOTING_BAR_Y = MathUtils.mToPx(20),
        FONT_PAD = MathUtils.scaleHeight(30),
        FONT_PAD_SMALL = MathUtils.scaleHeight(50);

    public static final String
        HIGH_SCORES_FILE_NAME = "highscores.txt",
        PRIVACY_POLICY_URL = "https://mkkekkonen.github.io/spaceshooter/privacypolicy/";

    private static int scaleHeight(int divisor) {
        return (int) MathUtils.scaleHeight(divisor);
    }
}
