package com.mkkekkonen.spaceshooter.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.gamemanagers.ShootingManager;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;


import javax.inject.Inject;


public class Game
{
    @Inject InputManager inputManager;
    @Inject ResourceManager resourceManager;
    @Inject ShootingManager shootingManager;
    @Inject GameWorld gameWorld;

    @Inject
    Game() {}

    public void update() {
        this.inputManager.getInput();

        Application.ApplicationType appType = Gdx.app.getType();

        if (appType.equals(Application.ApplicationType.Desktop)) {
            this.shootingManager.handleDesktopShooting();
        } else if (appType.equals(Application.ApplicationType.Android)) {
            this.shootingManager.handleMobileShooting();
        }

        this.gameWorld.update();
    }

    public void render(SpriteBatch batch) {
        this.gameWorld.render(batch);
    }

    public void dispose() {
        this.resourceManager.dispose();
    }
}
