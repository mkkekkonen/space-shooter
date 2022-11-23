package com.mkkekkonen.spaceshooter.highscores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.mkkekkonen.spaceshooter.gamemanagers.ScoreManager;
import com.mkkekkonen.spaceshooter.interfaces.IHighScoreInputListenerFactory;
import com.mkkekkonen.spaceshooter.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HighScoreManager {
    @Inject ScoreManager scoreManager;
    @Inject IHighScoreInputListenerFactory listenerFactory;

    @Inject
    HighScoreManager() {}

    public void openEnterNameDialog() {
        Gdx.input.getTextInput(
                this.listenerFactory.createHighScoreInputListener(this.scoreManager.getScore()),
                "Enter Your Name",
                "",
                "Name"
        );
    }
}
