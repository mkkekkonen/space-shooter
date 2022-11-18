package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.gamestates.MenuState;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IMenuStateFactory {
    MenuState createMenuState(GameStateManager stateManager);
}
