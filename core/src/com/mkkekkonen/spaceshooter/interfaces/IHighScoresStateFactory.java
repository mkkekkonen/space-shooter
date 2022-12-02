package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.gamestates.HighScoresState;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IHighScoresStateFactory {
    HighScoresState createHighScoresState(GameStateManager stateManager);
}
