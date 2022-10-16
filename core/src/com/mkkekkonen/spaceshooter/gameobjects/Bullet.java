package com.mkkekkonen.spaceshooter.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.gameobjects.components.Physics;
import com.mkkekkonen.spaceshooter.math.MathUtils;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

@Module
public class Bullet extends AbstractGameObject {
    static final float y = MathUtils.mToPx(4.75f);

    @AssistedInject
    Bullet(ResourceManager resourceManager, @Assisted float x) {
        this.physics = new Physics(
                new Vector2(x, y),
                new Vector2(0, 15),
                0.05f
        );
        this.initTexture(resourceManager.getSprite("bullet"), 0.1f);
    }

    public Vector2 getMidpoint() {
        float x = this.getX() + (this.width / 2);
        float y = this.getY() + (this.height / 2);

        return new Vector2(x, y);
    }
}
