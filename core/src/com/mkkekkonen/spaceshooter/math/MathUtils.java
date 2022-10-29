package com.mkkekkonen.spaceshooter.math;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.utils.Constants;

public class MathUtils {
    public static float EPSILON = 0.01f;

    public static float mToPx(float meters) {
        return meters * Constants.PX_PER_M;
    }

    public static Vector2 vecMToPx(Vector2 vector) {
        return new Vector2(
                MathUtils.mToPx(vector.x),
                MathUtils.mToPx(vector.y)
        );
    }

    public static Vector2 vecMulScalar(Vector2 vector, float scalar) {
        return new Vector2(
                vector.x * scalar,
                vector.y * scalar
        );
    }

    public static Vector2 vecSub(Vector2 first, Vector2 second) {
        return new Vector2(
                first.x - second.x,
                first.y - second.y
        );
    }

    public static Vector2 getSpriteWidthHeight(Texture texture, float spriteWidth) {
        float textureWidth = texture.getWidth();
        float textureHeight = texture.getHeight();

        float width = MathUtils.mToPx(spriteWidth);
        float coefficient = width / textureWidth;
        float height = textureHeight * coefficient;

        return new Vector2(width, height);
    }

    public static float getDistanceBetweenVectors(Vector2 vector1, Vector2 vector2) {
        float xDiff = vector2.x - vector1.x;
        double xSquared = Math.pow(xDiff, 2);

        float yDiff = vector2.y - vector1.y;
        double ySquared = Math.pow(yDiff, 2);

        return (float) Math.sqrt(xSquared + ySquared);
    }

    public static boolean isPointOnLineSegment(
            Vector2 lineStartPoint,
            Vector2 lineEndPoint,
            Vector2 point
    ) {
        float segmentLength = MathUtils.getDistanceBetweenVectors(lineStartPoint, lineEndPoint);
        float distanceFromStart = MathUtils.getDistanceBetweenVectors(lineStartPoint, point);
        float distanceFromEnd = MathUtils.getDistanceBetweenVectors(point, lineEndPoint);

        return Math.abs((distanceFromStart + distanceFromEnd) - segmentLength) < MathUtils.EPSILON;
    }

    /** https://mathworld.wolfram.com/Circle-LineIntersection.html */
    public static Vector2[] lineIntersectsCircle(
            Vector2 lineStartPoint,
            Vector2 lineEndPoint,
            Vector2 circleCenter,
            float circleRadius,
            boolean isLineSegment
    ) {
        Vector2 adjustedStartPoint = MathUtils.vecSub(lineStartPoint, circleCenter);
        Vector2 adjustedEndPoint = MathUtils.vecSub(lineEndPoint, circleCenter);

        float dx = adjustedEndPoint.x - adjustedStartPoint.x;
        float dy = adjustedEndPoint.y - adjustedStartPoint.y;
        float dr = (float) Math.sqrt(
                Math.pow(dx, 2) + Math.pow(dy, 2)
        );
        float D = (adjustedStartPoint.x * adjustedEndPoint.y) - (adjustedEndPoint.x * adjustedStartPoint.y);
        float discriminant = MathUtils.getCircleIntersectionDiscriminant(circleRadius, dr, D);
        float denominator = (float) Math.pow(dr, 2);

        Vector2[] emptyResult = new Vector2[] {};

        // discriminant less than zero -> no intersection
        if (discriminant < 0) {
            return emptyResult;
        }

        Float[] xCoordinates = MathUtils.getCircleIntersectionXCoordinates(dx, dy, D, discriminant, denominator);
        Float[] yCoordinates = MathUtils.getCircleIntersectionYCoordinates(dx, dy, D, discriminant, denominator);

        // discriminant equals zero -> tangent
        if (discriminant == 0) {
            Vector2 tangentPoint = new Vector2(xCoordinates[0], yCoordinates[0]);

            Vector2[] result = new Vector2[] { tangentPoint };

            if (!isLineSegment) {
                return result;
            }

            if (MathUtils.isPointOnLineSegment(adjustedStartPoint, adjustedEndPoint, tangentPoint)) {
                return result;
            }

            return emptyResult;
        }

        // two intersection points -> intersection through circle
        Vector2 firstIntersectionPoint = new Vector2(xCoordinates[0], yCoordinates[0]);
        Vector2 secondIntersectionPoint = new Vector2(xCoordinates[1], yCoordinates[1]);

        Vector2[] result = new Vector2[] { firstIntersectionPoint, secondIntersectionPoint };

        if (!isLineSegment) {
            return result;
        }

        if (isLineSegment
                && MathUtils.isPointOnLineSegment(adjustedStartPoint, adjustedEndPoint, firstIntersectionPoint)
                && MathUtils.isPointOnLineSegment(adjustedStartPoint, adjustedEndPoint, secondIntersectionPoint)) {
            return result;
        }

        return emptyResult;
    }

    private static Float[] getCircleIntersectionXCoordinates(
            float dx,
            float dy,
            float D,
            float discriminant,
            float denominator
    ) {
        float numeratorLeftPart = D * dy;
        float sgn = (dy < 0) ? -1 : 1;
        float numeratorRightPart = sgn * dx * (float) Math.sqrt(discriminant);

        float x1 = (numeratorLeftPart + numeratorRightPart) / denominator;
        float x2 = (numeratorLeftPart - numeratorRightPart) / denominator;

        return new Float[] { x1, x2 };
    }

    private static Float[] getCircleIntersectionYCoordinates(
            float dx,
            float dy,
            float D,
            float discriminant,
            float denominator
    ) {
        float numeratorLeftPart = -D * dx;
        float numeratorRightPart = (float) (Math.abs(dy) * Math.sqrt(discriminant));

        float y1 = (numeratorLeftPart + numeratorRightPart) / denominator;
        float y2 = (numeratorLeftPart - numeratorRightPart) / denominator;

        return new Float[] { y1, y2 };
    }

    private static float getCircleIntersectionDiscriminant(
            float r,
            float dr,
            float D
    ) {
        return (float) ((Math.pow(r, 2) * Math.pow(dr, 2)) - Math.pow(D, 2));
    }
}
