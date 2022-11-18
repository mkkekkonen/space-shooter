package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.gamestates.GamePlayingState;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IGamePlayingStateFactory {
    GamePlayingState createGamePlayingState(GameStateManager stateManager);
}
