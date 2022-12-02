package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.gamemanagers.ScoreManager;
import com.mkkekkonen.spaceshooter.highscores.HighScore;
import com.mkkekkonen.spaceshooter.highscores.HighScoreFileHandler;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.sprites.ExitButton;
import com.mkkekkonen.spaceshooter.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class HighScoresState extends AbstractGameState {
    @Inject ScoreManager scoreManager;
    @Inject ExitButton exitButton;

    private final BitmapFont titleFont;
    private final BitmapFont scoresFont;

    private final GameStateManager stateManager;

    private final List<HighScore> highScores;

    private final float titleTopY = MathUtils.invertY(Constants.FONT_PAD);
    private final float titleBottomY = this.titleTopY - Constants.LARGE_FONT_SIZE;

    @AssistedInject
    HighScoresState(ResourceManager resourceManager, @Assisted GameStateManager stateManager) {
        this.titleFont = resourceManager.getFont("large");
        this.scoresFont = resourceManager.getFont("small");

        this.stateManager = stateManager;

        this.highScores = HighScoreFileHandler.getHighScoreEntries();
    }

    @Override
    public void update(float deltaTime) {
        this.exitButton.update(deltaTime);

        if (this.exitButton.isClicked()) {
            this.stateManager.changeGameState(GameState.MENU);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.titleFont.draw(
                batch,
                "High Scores",
                Constants.FONT_PAD,
                this.titleTopY
        );

        for (int i = 0; i < highScores.size(); i++) {
            float topY = this.titleBottomY - Constants.FONT_PAD -
                    (i * (Constants.SMALL_FONT_SIZE + Constants.FONT_PAD));

            HighScore item = highScores.get(i);

            this.scoresFont.draw(
                    batch,
                    item.getName(),
                    Constants.FONT_PAD,
                    topY
            );

            this.scoresFont.draw(
                    batch,
                    ((Integer)item.getScore()).toString(),
                    Gdx.graphics.getWidth() / 2f,
                    topY
            );
        }

        this.exitButton.draw(batch);
    }
}
