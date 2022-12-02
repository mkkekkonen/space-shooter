package com.mkkekkonen.spaceshooter.animation;

import com.badlogic.gdx.Gdx;
import com.mkkekkonen.spaceshooter.gameobjects.AbstractGameObject;
import com.mkkekkonen.spaceshooter.math.RandomGenerator;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class ExplosionAnimation extends Animation {

    @AssistedInject
    ExplosionAnimation(
            ResourceManager resourceManager,
            RandomGenerator randomGenerator,
            @Assisted AbstractGameObject parent
    ) {
        super(
                resourceManager,
                randomGenerator,
                parent,
                new String[] {
                        "explosion1",
                        "explosion2",
                        "explosion3",
                        "explosion4",
                        "explosion5",
                        "explosion6"
                },
                0.3f,
                Gdx.graphics.getWidth() / 480f,
                true
        );
        this.started = true;
    }
}
