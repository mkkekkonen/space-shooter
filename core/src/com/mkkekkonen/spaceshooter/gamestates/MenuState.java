package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamemanagers.AudioManager;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.utils.Constants;

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class MenuState extends AbstractGameState {
    @Inject InputManager inputManager;
    @Inject AudioManager audioManager;

    private BitmapFont menuFont;
    private BitmapFont smallFont;

    private GameStateManager stateManager;

    @AssistedInject
    MenuState(ResourceManager resourceManager, @Assisted GameStateManager stateManager) {
        this.menuFont = resourceManager.getFont("menu");
        this.smallFont = resourceManager.getFont("small");

        this.stateManager = stateManager;
    }

    @Override
    public void update(float deltaTime) {
        this.inputManager.getInput();

        Vector2 clickLocation = this.inputManager.getClickLocation();
        if (clickLocation != null) {
            float startGameTopY = this.getY(50);
            if (clickLocation.y > startGameTopY - Constants.MENU_FONT_SIZE
                    && clickLocation.y < startGameTopY) {
                stateManager.changeGameState(GameState.GAME_PLAYING);
            } else if (clickLocation.y > 50 && clickLocation.y < 75) {
                this.audioManager.toggleAudio();
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.menuFont.draw(
                batch,
                "New Game",
                50,
                this.getY(50)
        );

        this.menuFont.draw(
                batch,
                "High Scores",
                50,
                this.getY(100)
        );

        this.smallFont.draw(
                batch,
                this.audioManager.isMuted() ? "(Sound Off)" : "(Sound On)",
                50,
                75
        );
    }

    private float getY(float distance) {
        return Gdx.graphics.getHeight() - distance;
    }
}
