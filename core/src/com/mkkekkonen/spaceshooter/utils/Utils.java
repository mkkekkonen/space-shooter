package com.mkkekkonen.spaceshooter.utils;

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
}
