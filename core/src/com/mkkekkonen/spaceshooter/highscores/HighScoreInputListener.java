package com.mkkekkonen.spaceshooter.highscores;

import com.badlogic.gdx.Input;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class HighScoreInputListener implements Input.TextInputListener {
    @Inject GameStateManager gameStateManager;

    private final int score;

    @AssistedInject
    HighScoreInputListener(@Assisted Integer score) {
        this.score = score;
    }

    @Override
    public void input(String text) {
        List<HighScore> entries = HighScoreFileHandler.getHighScoreEntries();

        HighScore highScore = new HighScore();
        highScore.setName(text);
        highScore.setScore(this.score);
        entries.add(highScore);

        HighScoreFileHandler.writeHighScores(entries);

        this.gameStateManager.changeGameState(GameState.MENU);
    }

    @Override
    public void canceled() {

    }
}
