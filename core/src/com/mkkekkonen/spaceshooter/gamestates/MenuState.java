package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamemanagers.AudioManager;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.math.MathUtils;
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

    private final float startGameTopY = MathUtils.invertY(Constants.FONT_PAD);
    private final float startGameBottomY = this.startGameTopY - Constants.LARGE_FONT_SIZE;
    private final float highScoresTopY = MathUtils.invertY(
            Constants.FONT_PAD + Constants.FONT_PAD_SMALL + Constants.LARGE_FONT_SIZE
    );
    private final float highScoresBottomY = this.highScoresTopY - Constants.LARGE_FONT_SIZE;
    private final float toggleSoundTopY = Constants.FONT_PAD + Constants.SMALL_FONT_SIZE;
    private final float toggleSoundBottomY = Constants.FONT_PAD;
    private final float privacyPolicyBottomY = this.toggleSoundTopY + Constants.FONT_PAD;
    private final float privacyPolicyTopY = this.privacyPolicyBottomY + Constants.SMALL_FONT_SIZE;

    @AssistedInject
    MenuState(ResourceManager resourceManager, @Assisted GameStateManager stateManager) {
        this.menuFont = resourceManager.getFont("large");
        this.smallFont = resourceManager.getFont("small");

        this.stateManager = stateManager;
    }

    @Override
    public void update(float deltaTime) {
        this.inputManager.getInput();

        Vector2 clickLocation = this.inputManager.getClickLocation();

        if (clickLocation != null) {
            if (clickIsBetween(this.startGameTopY, this.startGameBottomY)) {
                stateManager.changeGameState(GameState.GAME_PLAYING);
            } else if (clickIsBetween(this.highScoresTopY, this.highScoresBottomY)) {
                stateManager.changeGameState(GameState.HIGH_SCORES);
            } else if (clickIsBetween(this.privacyPolicyTopY, this.privacyPolicyBottomY)) {
                Gdx.net.openURI(Constants.PRIVACY_POLICY_URL);
            } else if (clickIsBetween(this.toggleSoundTopY, this.toggleSoundBottomY)) {
                this.audioManager.toggleAudio();
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.menuFont.draw(
                batch,
                "New Game",
                Constants.FONT_PAD,
                this.startGameTopY
        );

        this.menuFont.draw(
                batch,
                "High Scores",
                Constants.FONT_PAD,
                this.highScoresTopY
        );

        this.smallFont.draw(
                batch,
                "Privacy Policy",
                Constants.FONT_PAD,
                this.privacyPolicyTopY
        );

        this.smallFont.draw(
                batch,
                this.audioManager.isMuted() ? "(Sound Off)" : "(Sound On)",
                Constants.FONT_PAD,
                this.toggleSoundTopY
        );
    }

    @Override
    public void reset() {
        // no-op
    }

    private boolean clickIsBetween(float top, float bottom) {
        Vector2 clickLocation = this.inputManager.getClickLocation();

        return clickLocation.y > bottom && clickLocation.y < top;
    }
}
