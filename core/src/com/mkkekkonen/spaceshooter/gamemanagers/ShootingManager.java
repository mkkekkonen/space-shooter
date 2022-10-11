package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.Bullet;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.interfaces.IBulletFactory;
import com.mkkekkonen.spaceshooter.utils.Constants;

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
        if (this.inputManager.isShooting()) {
            this.time += Gdx.graphics.getDeltaTime();
        } else if (!this.inputManager.isShooting() && this.time > 0) {
            this.time = 0;
        }

        this.tryAddBullet();
    }

    public void handleMobileShooting() {
        Vector2 touchLocation = this.inputManager.getTouchLocation();

        boolean touching = (touchLocation != null
                && touchLocation.y >= (Constants.SHOOTING_BAR_Y - 50)
                && touchLocation.y <= (Constants.SHOOTING_BAR_Y + 50));

        if (touching) {
            this.time += Gdx.graphics.getDeltaTime();
        } else if (!touching && this.time > 0) {
            this.time = 0;
        }

        this.tryAddBullet();
    }

    private void tryAddBullet() {
        float shipX = this.gameWorld.getShip().getX();

        if (this.time > 0.1f) {
            this.time -= 0.1f;
            this.gameWorld.addBullet(this.bulletFactory.create(shipX));
        }
    }
}
