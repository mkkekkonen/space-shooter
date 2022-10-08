package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.game.Game;
import com.mkkekkonen.spaceshooter.gameobjects.Asteroid;
import com.mkkekkonen.spaceshooter.gameobjects.Ship;
import com.mkkekkonen.spaceshooter.gameobjects.ShootingBar;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                Ship.class,
                Asteroid.class,
                ShootingBar.class
        }
)
public interface IGame {
    Game game();
}
