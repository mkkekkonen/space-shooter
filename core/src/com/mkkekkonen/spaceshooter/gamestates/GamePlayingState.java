package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.gamemanagers.CollisionManager;
import com.mkkekkonen.spaceshooter.gamemanagers.ShootingManager;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class GamePlayingState extends AbstractGameState {
    @Inject InputManager inputManager;
    @Inject ShootingManager shootingManager;
    @Inject CollisionManager collisionManager;
    @Inject GameWorld gameWorld;

    @Inject
    GamePlayingState() {}

    public void update(float deltaTime) {
        this.inputManager.getInput();
        this.shootingManager.update();
        this.collisionManager.update();
        this.gameWorld.update();
    }

    public void draw(SpriteBatch batch) {
        this.gameWorld.render(batch);
    }
}
