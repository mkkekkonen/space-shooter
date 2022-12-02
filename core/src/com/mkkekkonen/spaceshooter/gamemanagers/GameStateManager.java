package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamestates.AbstractGameState;
import com.mkkekkonen.spaceshooter.gamestates.GamePlayingState;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IGamePlayingStateFactory;
import com.mkkekkonen.spaceshooter.interfaces.IHighScoresStateFactory;
import com.mkkekkonen.spaceshooter.interfaces.IMenuStateFactory;
import com.mkkekkonen.spaceshooter.interfaces.IUpdateable;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameStateManager implements IDrawable, IUpdateable {
    @Inject InputManager inputManager;
    @Inject ScoreManager scoreManager;

    private final AudioManager audioManager;

    private final Map<GameState, AbstractGameState> gameStates;

    private GameState currentGameState;

    @Inject
    public GameStateManager(
            AudioManager audioManager,
            IMenuStateFactory menuStateFactory,
            IGamePlayingStateFactory gamePlayingStateFactory,
            IHighScoresStateFactory highScoresStateFactory
    ) {
        this.gameStates = new HashMap<>();
        this.gameStates.put(GameState.MENU, menuStateFactory.createMenuState(this));
        this.gameStates.put(GameState.GAME_PLAYING, gamePlayingStateFactory.createGamePlayingState(this));
        this.gameStates.put(GameState.HIGH_SCORES, highScoresStateFactory.createHighScoresState(this));

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

        if (newState.equals(GameState.GAME_PLAYING)) {
            ((GamePlayingState)this.gameStates.get(newState)).reset();
            this.scoreManager.resetScore();
        }

        this.currentGameState = newState;
        this.audioManager.changeMusic(newState);
    }
}
