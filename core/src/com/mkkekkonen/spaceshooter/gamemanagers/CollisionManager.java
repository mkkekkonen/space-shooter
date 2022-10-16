package com.mkkekkonen.spaceshooter.gamemanagers;

import com.mkkekkonen.spaceshooter.gameobjects.Asteroid;
import com.mkkekkonen.spaceshooter.gameobjects.Bullet;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.math.MathUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CollisionManager {
    @Inject GameWorld gameWorld;

    @Inject
    CollisionManager() {}

    public void update() {
        this.handleBulletCollisions();
    }

    private void handleBulletCollisions() {
        for (Bullet bullet : this.gameWorld.getBullets()) {
            for (Asteroid asteroid : this.gameWorld.getAsteroids()) {
                float distance = MathUtils.getDistanceBetweenVectors(
                        bullet.getMidpoint(),
                        asteroid.getPosition()
                );

                if (distance < asteroid.getRadius()) {
                    asteroid.setCollided();
                }
            }
        }
    }
}
