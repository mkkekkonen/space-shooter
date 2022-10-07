package com.mkkekkonen.spaceshooter.math;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RandomGenerator {
    Random random;

    @Inject
    RandomGenerator() {
        this.random = new Random();
    }

    public float getRandomFloat(float min, float max) {
        return min + (this.random.nextFloat() * (max - min));
    }

    public int getRandomInt(int min, int max) {
        return min + (this.random.nextInt(max - min));
    }
}
