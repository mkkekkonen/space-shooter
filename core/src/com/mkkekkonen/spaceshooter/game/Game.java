package com.mkkekkonen.spaceshooter.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.gamemanagers.CollisionManager;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.gamemanagers.ShootingManager;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;


import javax.inject.Inject;


public class Game
{
    @Inject GameStateManager gameStateManager;
    @Inject ResourceManager resourceManager;

    @Inject
    Game() {}

    public void update() {
        this.gameStateManager.update(Gdx.graphics.getDeltaTime());
    }

    public void render(SpriteBatch batch) {
        this.gameStateManager.draw(batch);
    }

    public void dispose() {
        this.resourceManager.dispose();
    }
}
