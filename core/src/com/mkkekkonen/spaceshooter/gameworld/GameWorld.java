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
import com.mkkekkonen.spaceshooter.gameobjects.State;
import com.mkkekkonen.spaceshooter.interfaces.IBulletFactory;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;

@Module
public class GameWorld {
    @Inject Ship ship;
    @Inject ShootingBar shootingBar;
    @Inject Provider<Asteroid> asteroidProvider;
    @Inject RandomGenerator randomGenerator;

    private final List<Asteroid> asteroids = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();

    private static final int BOTTOM_Y_BORDER = -50,
        TOP_Y_BORDER = Gdx.graphics.getHeight() + 25,
        RANDOM_MIN = 1,
        RANDOM_MAX = 100;

    @Inject
    GameWorld() {}

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (this.randomGenerator.getRandomInt(
                GameWorld.RANDOM_MIN,
                GameWorld.RANDOM_MAX
        ) == GameWorld.RANDOM_MIN) {
            this.asteroids.add(this.asteroidProvider.get());
        }

        this.ship.update(deltaTime);

        this.updateList(this.asteroids, Direction.DOWN, GameWorld.BOTTOM_Y_BORDER, deltaTime);
        this.updateList(this.bullets, Direction.UP, GameWorld.TOP_Y_BORDER, deltaTime);
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

            boolean upperThresholdReached = direction.equals(Direction.UP) && gameObjectY > threshold;
            boolean lowerThresholdReached = direction.equals(Direction.DOWN) && gameObjectY < threshold;
            boolean dispose = gameObject.getState() == State.DISPOSABLE;

            if (upperThresholdReached || lowerThresholdReached || dispose) {
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

    public List<Asteroid> getAsteroids() {
        return this.asteroids;
    }

    public List<Bullet> getBullets() {
        return this.bullets;
    }
}
