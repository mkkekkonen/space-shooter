package com.mkkekkonen.spaceshooter.highscores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mkkekkonen.spaceshooter.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreFileHandler {
    public static List<HighScore> getHighScoreEntries() {
        FileHandle file = Gdx.files.local(Constants.HIGH_SCORES_FILE_NAME);

        if (!file.exists()) {
            file.writeString("", false);
        }

        String content = file.readString();

        if (content.isEmpty()) {
            return new ArrayList<>();
        }

        String[] rows = content.split("\n");

        List<HighScore> entries = new ArrayList<>();
        for (String row : rows) {
            if (row.isEmpty()) {
                continue;
            }

            String[] data = row.split("=");
            HighScore highScore = new HighScore();
            highScore.setName(data[0]);
            highScore.setScore(Integer.parseInt(data[1]));
            entries.add(highScore);
        }
        HighScoreFileHandler.sortEntries(entries);

        return entries;
    }

    public static void writeHighScores(List<HighScore> highScores) {
        HighScoreFileHandler.sortEntries(highScores);

        StringBuilder builder = new StringBuilder();

        for (HighScore highScore : highScores) {
            builder.append(highScore.getName());
            builder.append("=");
            builder.append(highScore.getScore());
            builder.append("\n");
        }

        FileHandle file = Gdx.files.local(Constants.HIGH_SCORES_FILE_NAME);
        file.writeString(builder.toString(), false);
    }

    private static void sortEntries(List<HighScore> entries) {
        Collections.sort(entries, new HighScoreComparator());
        Collections.reverse(entries);
    }
}
