package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.Asteroid;
import com.mkkekkonen.spaceshooter.gameobjects.Bullet;
import com.mkkekkonen.spaceshooter.gameobjects.Ship;
import com.mkkekkonen.spaceshooter.gameobjects.State;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CollisionManager {
    @Inject GameWorld gameWorld;
    @Inject AudioManager audioManager;
    @Inject ScoreManager scoreManager;

    @Inject
    CollisionManager() {}

    public void update() {
        this.handleBulletCollisions();
        this.handleShipAsteroidCollisions();
    }

    public static List<Vector2[]> getShipLines(Vector2[] points) {
        List<Vector2[]> lines = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            Vector2 firstPoint = points[i];
            Vector2 secondPoint = (Vector2) Utils.getNextElementInArray(points, i);
            Vector2[] line = new Vector2[] { firstPoint, secondPoint };
            lines.add(line);
        }

        return lines;
    }

    private void handleBulletCollisions() {
        for (Bullet bullet : this.gameWorld.getBullets()) {
            for (Asteroid asteroid : this.gameWorld.getAsteroids()) {
                float distance = MathUtils.getDistanceBetweenVectors(
                        bullet.getMidpoint(),
                        asteroid.getPosition()
                );

                if (distance < asteroid.getRadius()
                        && !asteroid.getState().equals(State.EXPLODING)) {
                    asteroid.setState(State.EXPLODING);
                    this.audioManager.playExplosionSound();
                    this.scoreManager.addToScore(30);
                }
            }
        }
    }

    private void handleShipAsteroidCollisions() {
        Ship ship = this.gameWorld.getShip();
        Vector2[] points = ship.getTriangleVectors();

        List<Vector2[]> lines = CollisionManager.getShipLines(points);

        for (Asteroid asteroid : this.gameWorld.getAsteroids()) {
            float radius = asteroid.getRadius();
            Vector2 asteroidPosition = asteroid.getPosition();

            for (int i = 0; i < lines.size(); i++) {
                Vector2[] line = lines.get(i);
                Vector2[] intersection = MathUtils.lineIntersectsCircle(
                        line[0],
                        line[1],
                        asteroidPosition,
                        radius,
                        true
                );
                if (intersection.length > 0 && !ship.getState().equals(State.EXPLODING)) {
                    ship.setState(State.EXPLODING);
                    asteroid.setState(State.DISPOSABLE);
                    this.audioManager.playExplosionSound();
                }
            }
        }
    }
}
