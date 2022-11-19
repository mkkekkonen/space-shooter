package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamemanagers.CollisionManager;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.gamemanagers.ScoreManager;
import com.mkkekkonen.spaceshooter.gamemanagers.ShootingManager;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.sprites.ExitButton;

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class GamePlayingState extends AbstractGameState {
    @Inject InputManager inputManager;
    @Inject ShootingManager shootingManager;
    @Inject CollisionManager collisionManager;
    @Inject ScoreManager scoreManager;
    @Inject GameWorld gameWorld;
    @Inject ExitButton exitButton;

    private GameStateManager stateManager;
    private BitmapFont scoreFont;

    private float scoreTicker = 0;

    @AssistedInject
    GamePlayingState(
            ResourceManager resourceManager,
            @Assisted GameStateManager stateManager
    ) {
        this.stateManager = stateManager;
        this.scoreFont = resourceManager.getFont("score");
    }

    @Override
    public void update(float deltaTime) {
        this.inputManager.getInput();
        this.shootingManager.update();
        this.collisionManager.update();
        this.gameWorld.update();
        this.exitButton.update(deltaTime);

        scoreTicker += deltaTime;
        if (scoreTicker > 1f) {
            scoreTicker -= 1f;
            this.scoreManager.addToScore(5);
        }

        if (this.exitButton.isClicked()) {
            this.stateManager.changeGameState(GameState.MENU);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.gameWorld.render(batch);
        this.exitButton.draw(batch);

        float padScore = MathUtils.scaleHeight(40);

        this.scoreFont.draw(
                batch,
                this.scoreManager.getScore().toString(),
                padScore,
                MathUtils.invertY(padScore)
        );
    }
}
