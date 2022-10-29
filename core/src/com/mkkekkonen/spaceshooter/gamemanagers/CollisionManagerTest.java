package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.math.Vector2;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CollisionManagerTest {
    double EPSILON = 0.001;

    @Test
    public void getShipLines() {
        Vector2[] shipPoints = new Vector2[] {
                new Vector2(2.5f, 4),
                new Vector2(4, 1),
                new Vector2(1, 1)
        };

        List<Vector2[]> lines = CollisionManager.getShipLines(shipPoints);

        List<Vector2[]> expected = new ArrayList<>();
        expected.add(new Vector2[] {
                shipPoints[0],
                shipPoints[1],
        });
        expected.add(new Vector2[] {
                shipPoints[1],
                shipPoints[2]
        });
        expected.add(new Vector2[] {
                shipPoints[2],
                shipPoints[0],
        });

        for (int i = 0; i < lines.size(); i++) {
            Vector2[] currentLine = lines.get(i);
            Vector2[] expectedLine = expected.get(i);

            assertEquals(expectedLine[0].x, currentLine[0].x, EPSILON);
            assertEquals(expectedLine[0].y, currentLine[0].y, EPSILON);

            assertEquals(expectedLine[1].x, currentLine[1].x, EPSILON);
            assertEquals(expectedLine[1].y, currentLine[1].y, EPSILON);
        }
    }
}
