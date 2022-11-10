package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamestates.AbstractGameState;
import com.mkkekkonen.spaceshooter.gamestates.GamePlayingState;
import com.mkkekkonen.spaceshooter.gamestates.MenuState;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
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
            GamePlayingState gamePlayingState,
            MenuState menuState,
            AudioManager audioManager
    ) {
        this.gameStates = new HashMap<>();
        this.gameStates.put(GameState.MENU, menuState);
        this.gameStates.put(GameState.GAME_PLAYING, gamePlayingState);
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
}
