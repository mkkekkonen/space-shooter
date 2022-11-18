package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamestates.AbstractGameState;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IGamePlayingStateFactory;
import com.mkkekkonen.spaceshooter.interfaces.IMenuStateFactory;
import com.mkkekkonen.spaceshooter.interfaces.IUpdateable;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameStateManager implements IDrawable, IUpdateable {
    @Inject InputManager inputManager;

    private AudioManager audioManager;

    private Map<GameState, AbstractGameState> gameStates;

    private GameState currentGameState;

    @Inject
    public GameStateManager(
            AudioManager audioManager,
            IMenuStateFactory menuStateFactory,
            IGamePlayingStateFactory gamePlayingStateFactory
    ) {
        this.gameStates = new HashMap<>();
        this.gameStates.put(GameState.MENU, menuStateFactory.createMenuState(this));
        this.gameStates.put(GameState.GAME_PLAYING, gamePlayingStateFactory.createGamePlayingState(this));
        this.currentGameState = GameState.MENU;

        this.audioManager = audioManager;
        this.audioManager.changeMusic(GameState.MENU);
    }

    public void update(float deltaTime) {
        this.inputManager.getInput();
        this.gameStates.get(this.currentGameState).update(deltaTime);
    }

    public void draw(SpriteBatch batch) {
        this.gameStates.get(this.currentGameState).draw(batch);
    }

    public void changeGameState(GameState newState) {
        if (this.currentGameState.equals(newState)) {
            return;
        }

        this.currentGameState = newState;
        this.audioManager.changeMusic(newState);
    }
}
