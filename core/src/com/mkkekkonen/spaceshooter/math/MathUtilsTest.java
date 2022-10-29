package com.mkkekkonen.spaceshooter.math;

import static org.junit.Assert.*;

import com.badlogic.gdx.math.Vector2;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

public class MathUtilsTest {

    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Test
    public void getDistanceBetweenVectors() {
        Vector2 start = new Vector2(1, 1);
        Vector2 end = new Vector2(3, 4);

        float distance = MathUtils.getDistanceBetweenVectors(start, end);

        assertEquals("3,61", decimalFormat.format(distance));
    }

    @Test
    public void isPointOnLineSegment() {
        Vector2 start = new Vector2(1, 1);
        Vector2 end = new Vector2(3, 4);
        Vector2 mid = new Vector2(2, 2.5f);

        boolean isOnLineSegment = MathUtils.isPointOnLineSegment(
                start,
                end,
                mid
        );

        assertTrue(isOnLineSegment);
    }

    @Test
    public void isPointOnLineSegmentFails() {
        Vector2 start = new Vector2(0, 1);
        Vector2 end = new Vector2(4, 3);
        Vector2 mid = new Vector2(2, 1);

        boolean isOnLineSegment = MathUtils.isPointOnLineSegment(
                start,
                end,
                mid
        );

        assertFalse(isOnLineSegment);
    }

    @Test
    public void lineIntersectsCircle() {
        Vector2 lineStartPoint = new Vector2(-3, -2);
        Vector2 lineEndPoint = new Vector2(2, 3);
        Vector2 circleCenter = new Vector2(0, 0);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                true
        );

        assertEquals(2, intersectionPoints.length);
    }

    @Test
    public void lineIntersectsCircle2() {
        Vector2 lineStartPoint = new Vector2(1, 1);
        Vector2 lineEndPoint = new Vector2(6, 6);
        Vector2 circleCenter = new Vector2(4, 3);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                true
        );

        assertEquals(2, intersectionPoints.length);
    }

    @Test
    public void lineIntersectsCircle3() {
        Vector2 lineStartPoint = new Vector2(-4, -5);
        Vector2 lineEndPoint = new Vector2(-1, -1);
        Vector2 circleCenter = new Vector2(4, 3);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                true
        );

        assertEquals(0, intersectionPoints.length);
    }

    @Test
    public void lineIntersectsCircle4() {
        Vector2 lineStartPoint = new Vector2(-4, -5);
        Vector2 lineEndPoint = new Vector2(-1, -1);
        Vector2 circleCenter = new Vector2(4, 3);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                false
        );

        assertEquals(2, intersectionPoints.length);
    }

    @Test
    public void lineIntersectsCircleTangent() {
        Vector2 lineStartPoint = new Vector2(1, 5);
        Vector2 lineEndPoint = new Vector2(7, 5);
        Vector2 circleCenter = new Vector2(4, 3);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                true
        );

        assertEquals(1, intersectionPoints.length);
    }

    @Test
    public void lineIntersectsCircleTangent2() {
        Vector2 lineStartPoint = new Vector2(-4, 5);
        Vector2 lineEndPoint = new Vector2(-1, 5);
        Vector2 circleCenter = new Vector2(4, 3);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                true
        );

        assertEquals(0, intersectionPoints.length);
    }

    @Test
    public void lineIntersectsCircleTangent3() {
        Vector2 lineStartPoint = new Vector2(-4, 5);
        Vector2 lineEndPoint = new Vector2(-1, 5);
        Vector2 circleCenter = new Vector2(4, 3);
        float circleRadius = 2;

        Vector2[] intersectionPoints = MathUtils.lineIntersectsCircle(
                lineStartPoint,
                lineEndPoint,
                circleCenter,
                circleRadius,
                false
        );

        assertEquals(1, intersectionPoints.length);
    }
}