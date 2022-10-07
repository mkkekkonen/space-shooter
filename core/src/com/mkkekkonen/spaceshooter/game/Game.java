package com.mkkekkonen.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.gameobjects.Asteroid;
import com.mkkekkonen.spaceshooter.gameobjects.Ship;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;


public class Game
{
    @Inject InputManager inputManager;
    @Inject Ship ship;
    @Inject Provider<Asteroid> asteroidProvider;
    @Inject RandomGenerator randomGenerator;

    List<Asteroid> asteroids = new ArrayList<>();

    @Inject
    Game() {}

    public void update() {
        if (this.randomGenerator.getRandomInt(1, 100) == 1) {
            this.asteroids.add(this.asteroidProvider.get());
        }

        this.inputManager.getInput();

        float deltaTime = Gdx.graphics.getDeltaTime();

        this.ship.update(deltaTime);

        for (int i = asteroids.size() - 1; i >= 0; i--) {
            Asteroid asteroid = asteroids.get(i);

            if (asteroid.getY() < -50) {
                asteroids.remove(i);
                continue;
            }

            asteroid.update(deltaTime);
        }
    }

    public void render(SpriteBatch batch) {
        this.ship.draw(batch);
        for (Asteroid asteroid : this.asteroids) {
            asteroid.draw(batch);
        }
    }
}
