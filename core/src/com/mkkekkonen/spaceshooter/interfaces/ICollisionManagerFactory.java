package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.gamemanagers.CollisionManager;
import com.mkkekkonen.spaceshooter.gameworld.GameWorld;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface ICollisionManagerFactory {
    CollisionManager createCollisionManager(GameWorld gameWorld);
}
