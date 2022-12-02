package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.gamemanagers.ShootingManager;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IShootingManagerFactory {
    ShootingManager createShootingManager(GameWorld gameWorld);
}
