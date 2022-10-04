package com.mkkekkonen.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.gameobjects.ship.Ship;
import com.mkkekkonen.spaceshooter.input.InputManager;

import javax.inject.Inject;


public class Game
{
    @Inject InputManager inputManager;

    Ship ship;

    @Inject
    Game(Ship ship) {
        this.ship = ship;
    }

    public void update() {
        this.inputManager.getInput();

        this.ship.update(Gdx.graphics.getDeltaTime());
    }

    public void render(SpriteBatch batch) {
        this.ship.draw(batch);
    }
}
