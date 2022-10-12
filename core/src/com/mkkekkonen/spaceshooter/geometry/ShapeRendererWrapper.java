package com.mkkekkonen.spaceshooter.geometry;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShapeRendererWrapper {
    private ShapeRenderer shapeRenderer;

    @Inject
    ShapeRendererWrapper() {
        this.shapeRenderer = new ShapeRenderer();
        this.setColor(0, 1, 0, 1);
    }

    public void setColor(float red, float green, float blue, float alpha) {
        this.shapeRenderer.setColor(red, green, blue, alpha);
    }

    public void drawTriangle(Vector2 a, Vector2 b, Vector2 c) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(a, b);
        shapeRenderer.line(b, c);
        shapeRenderer.line(c, a);
        shapeRenderer.end();
    }

    public void drawCircle(Vector2 center, float radius) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(center.x, center.y, radius);
        shapeRenderer.end();
    }
}
