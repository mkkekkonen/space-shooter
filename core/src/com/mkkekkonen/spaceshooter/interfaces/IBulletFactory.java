package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.gameobjects.Bullet;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IBulletFactory {
    Bullet create(float x);
}
