package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.interfaces.IDrawable;
import com.mkkekkonen.spaceshooter.interfaces.IUpdateable;

public abstract class AbstractGameState implements IDrawable, IUpdateable {
    public abstract void update(float deltaTime);
    public abstract void draw(SpriteBatch batch);
}
