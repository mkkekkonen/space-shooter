package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.Gdx;
import com.mkkekkonen.spaceshooter.gameobjects.Bullet;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IBulletFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShootingManager {
    @Inject InputManager inputManager;
    @Inject IBulletFactory bulletFactory;
    @Inject GameWorld gameWorld;

    float time = 0;

    @Inject
    ShootingManager() {}

    public void handleDesktopShooting() {
        float shipX = this.gameWorld.getShip().getX();

        if (this.inputManager.isShooting()) {
            this.time += Gdx.graphics.getDeltaTime();
        } else if (!this.inputManager.isShooting() && time > 0) {
            this.time = 0;
        }

        if (this.time > 0.1f) {
            this.time -= 0.1f;
            this.gameWorld.addBullet(this.bulletFactory.create(shipX));
        }
    }
}
