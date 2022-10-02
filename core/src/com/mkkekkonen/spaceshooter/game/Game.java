package com.mkkekkonen.spaceshooter.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.gameobjects.ship.Ship;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;


public class Game {
    Ship ship;

    @Inject
    Game(Ship ship) {
        this.ship = ship;
    }

    public void render(SpriteBatch batch) {
        this.ship.draw(batch);
    }
}
