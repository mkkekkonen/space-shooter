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
import com.sun.org.apache.bcel.internal.Const;

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

    private final float pad = Gdx.graphics.getHeight() / 30;
    private final float padSmall = Gdx.graphics.getHeight() / 50;
    private final float startGameTopY = this.getY(this.pad);
    private final float startGameBottomY = this.startGameTopY - Constants.MENU_FONT_SIZE;
    private final float highScoresTopY = this.getY(this.pad + this.padSmall + Constants.MENU_FONT_SIZE);
    private final float highScoresBottomY = this.highScoresTopY - Constants.MENU_FONT_SIZE;
    private final float toggleSoundTopY = this.pad + Constants.SMALL_FONT_SIZE;
    private final float toggleSoundBottomY = this.pad;

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
            if (clickLocation.y > this.startGameBottomY
                    && clickLocation.y < this.startGameTopY) {
                stateManager.changeGameState(GameState.GAME_PLAYING);
            } else if (clickLocation.y > this.toggleSoundBottomY
                    && clickLocation.y < this.toggleSoundTopY) {
                this.audioManager.toggleAudio();
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.menuFont.draw(
                batch,
                "New Game",
                this.pad,
                this.startGameTopY
        );

        this.menuFont.draw(
                batch,
                "High Scores",
                this.pad,
                this.highScoresTopY
        );

        this.smallFont.draw(
                batch,
                this.audioManager.isMuted() ? "(Sound Off)" : "(Sound On)",
                this.pad,
                this.toggleSoundTopY
        );
    }

    private float getY(float distance) {
        return Gdx.graphics.getHeight() - distance;
    }
}
