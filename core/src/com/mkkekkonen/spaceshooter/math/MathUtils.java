package com.mkkekkonen.spaceshooter.math;

import com.mkkekkonen.spaceshooter.utils.Constants;

public class MathUtils {
    public static float mToPx(float meters) {
        return meters * Constants.PX_PER_M;
    }
}
