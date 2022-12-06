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
import com.mkkekkonen.spaceshooter.interfaces.ICollisionManagerFactory;
import com.mkkekkonen.spaceshooter.interfaces.IShootingManagerFactory;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;
import com.mkkekkonen.spaceshooter.sprites.ExitButton;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class GamePlayingState extends AbstractGameState {
    @Inject InputManager inputManager;
    @Inject ScoreManager scoreManager;
    @Inject ExitButton exitButton;

    private final GameStateManager stateManager;
    private final ShootingManager shootingManager;
    private final CollisionManager collisionManager;
    private final Provider<GameWorld> gameWorldProvider;
    private GameWorld gameWorld;

    private final BitmapFont scoreFont;

    private float scoreTicker = 0;

    @AssistedInject
    GamePlayingState(
            ResourceManager resourceManager,
            Provider<GameWorld> gameWorldProvider,
            IShootingManagerFactory shootingManagerFactory,
            ICollisionManagerFactory collisionManagerFactory,
            @Assisted GameStateManager stateManager
    ) {
        this.stateManager = stateManager;
        this.scoreFont = resourceManager.getFont("score");

        this.gameWorldProvider = gameWorldProvider;
        this.gameWorld = this.gameWorldProvider.get();

        this.shootingManager = shootingManagerFactory.createShootingManager(this.gameWorld);
        this.collisionManager = collisionManagerFactory.createCollisionManager(this.gameWorld);
    }

    @Override
    public void update(float deltaTime) {
        boolean shipDestroyed = this.collisionManager.isShipDestroyed();

        this.inputManager.getInput();
        this.gameWorld.update();
        this.collisionManager.update();

        this.exitButton.update(deltaTime);

        if (!shipDestroyed) {
            this.shootingManager.update();

            scoreTicker += deltaTime;
            if (scoreTicker > 1f) {
                scoreTicker -= 1f;
                this.scoreManager.addToScore(5);
            }
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

    @Override
    public void reset() {
        this.gameWorld = this.gameWorldProvider.get();
        this.shootingManager.setGameWorld(this.gameWorld);
        this.collisionManager.setGameWorld(this.gameWorld);
        this.collisionManager.setShipDestroyed(false);
    }
}
