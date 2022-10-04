package com.mkkekkonen.spaceshooter.gameobjects.components;

import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.math.MathUtils;

public class Physics {
    private Vector2 position;
    private Vector2 velocity;

    public Physics(float x, float y) {
        this.setPosition(new Vector2(x, y));
        this.velocity = new Vector2();
    }

    public void update(float deltaTime) {
        Vector2 newPosition = this.getPosition().add(
                MathUtils.vecMulScalar(
                    MathUtils.vecMToPx(this.velocity),
                    deltaTime
                )
        );

        this.setPosition(newPosition);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
