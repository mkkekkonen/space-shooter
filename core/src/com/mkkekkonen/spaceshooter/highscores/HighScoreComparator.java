package com.mkkekkonen.spaceshooter.highscores;

import com.mkkekkonen.spaceshooter.highscores.HighScore;

import java.util.Comparator;

public class HighScoreComparator implements Comparator<HighScore> {
    @Override
    public int compare(HighScore highScore, HighScore t1) {
        return Integer.compare(highScore.getScore(), t1.getScore());
    }
}
