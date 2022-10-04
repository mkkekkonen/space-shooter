package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.game.Game;
import com.mkkekkonen.spaceshooter.gameobjects.ship.Ship;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                Ship.class
        }
)
public interface IGame {
    Game game();
}
