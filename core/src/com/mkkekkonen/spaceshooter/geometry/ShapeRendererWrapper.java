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

    public void setColor(float r, float g, float b, float a) {
        this.shapeRenderer.setColor(r, g, b, a);
    }

    public void drawTriangle(Vector2 a, Vector2 b, Vector2 c) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(a, b);
        shapeRenderer.line(b, c);
        shapeRenderer.line(c, a);
        shapeRenderer.end();
    }
}
