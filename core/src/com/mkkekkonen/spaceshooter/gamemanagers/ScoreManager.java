package com.mkkekkonen.spaceshooter.gamemanagers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScoreManager {
    private int score = 0;

    @Inject
    ScoreManager() {}

    public void resetScore() {
        this.score = 0;
    }

    public void addToScore(int value) {
        this.score += value;
    }

    public Integer getScore() {
        return this.score;
    }
}
