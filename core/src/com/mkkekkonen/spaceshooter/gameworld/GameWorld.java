package com.mkkekkonen.spaceshooter.gameworld;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.enums.Direction;
import com.mkkekkonen.spaceshooter.gameobjects.AbstractGameObject;
import com.mkkekkonen.spaceshooter.gameobjects.Asteroid;
import com.mkkekkonen.spaceshooter.gameobjects.Bullet;
import com.mkkekkonen.spaceshooter.gameobjects.Ship;
import com.mkkekkonen.spaceshooter.gameobjects.ShootingBar;
import com.mkkekkonen.spaceshooter.interfaces.IBulletFactory;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class GameWorld {
    @Inject Ship ship;
    @Inject ShootingBar shootingBar;
    @Inject Provider<Asteroid> asteroidProvider;
    @Inject RandomGenerator randomGenerator;

    List<Asteroid> asteroids = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();

    @Inject
    GameWorld() {}

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (this.randomGenerator.getRandomInt(1, 100) == 1) {
            this.asteroids.add(this.asteroidProvider.get());
        }

        this.ship.update(deltaTime);

        this.updateList(this.asteroids, Direction.DOWN, -50, deltaTime);
        this.updateList(this.bullets, Direction.UP, Gdx.graphics.getHeight() + 25, deltaTime);
    }

    public void render(SpriteBatch batch) {
        this.ship.draw(batch);

        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            this.shootingBar.draw(batch);
        }

        this.drawList(this.asteroids, batch);
        this.drawList(this.bullets, batch);
    }

    public void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    private void updateList(List gameObjectList, Direction direction, float threshold, float deltaTime) {
        for (int i = gameObjectList.size() - 1; i >= 0; i--) {
            AbstractGameObject gameObject = (AbstractGameObject) gameObjectList.get(i);

            float gameObjectY = gameObject.getY();

            if (
                    (direction.equals(Direction.UP) && gameObjectY > threshold)
                    || (direction.equals(Direction.DOWN) && gameObjectY < threshold)
            ) {
                gameObjectList.remove(i);
                continue;
            }

            gameObject.update(deltaTime);
        }
    }

    private void drawList(List gameObjectList, SpriteBatch batch) {
        for (Object gameObject : gameObjectList) {
            AbstractGameObject _gameObject = (AbstractGameObject) gameObject;
            _gameObject.draw(batch);
        }
    }

    public Ship getShip() {
        return ship;
    }
}
