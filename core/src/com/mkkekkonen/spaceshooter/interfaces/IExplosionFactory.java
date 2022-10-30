package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.animation.ExplosionAnimation;
import com.mkkekkonen.spaceshooter.gameobjects.AbstractGameObject;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IExplosionFactory {
    ExplosionAnimation create(AbstractGameObject parent);
}
