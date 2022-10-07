package com.mkkekkonen.spaceshooter.gameobjects.components;

import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.math.MathUtils;

public class Physics {
    private float angularVelocity = 0;
    private float rotationDeg = 0;

    private Vector2 position;
    private Vector2 velocity;

    public Physics(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2();
    }

    public Physics(Vector2 position, Vector2 velocity, Float angularVelocity) {
        this.position = position != null ? position : new Vector2();
        this.velocity = velocity != null ? velocity : new Vector2();

        if (angularVelocity != null) {
            this.angularVelocity = angularVelocity;
        }
    }

    public void update(float deltaTime) {
        Vector2 newPosition = this.getPosition().add(
                MathUtils.vecMulScalar(
                    MathUtils.vecMToPx(this.velocity),
                    deltaTime
                )
        );

        float newRotation = this.rotationDeg + (angularVelocity * deltaTime);
        newRotation = newRotation % 360;

        this.setPosition(newPosition);
        this.setRotationDeg(newRotation);
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

    public float getRotationDeg() {
        return rotationDeg;
    }

    public void setRotationDeg(float rotationDeg) {
        this.rotationDeg = rotationDeg;
    }
}
