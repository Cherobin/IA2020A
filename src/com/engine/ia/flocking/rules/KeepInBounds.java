package com.engine.ia.flocking.rules;

import com.engine.ia.flocking.Rule;
import com.engine.npcs.Bird;
import com.engine.utils.Vector;

public class KeepInBounds extends Rule {

    private final int MAX_X, MIN_X, MAX_Y, MIN_Y;
    private final double RETURN_SPEED;

    public KeepInBounds(int maxX, int minX, int maxY, int minY, double returnSpeed) {
        MAX_X = maxX;
        MIN_X = minX;
        MAX_Y = maxY;
        MIN_Y = minY;
        RETURN_SPEED = returnSpeed;
    }

    @Override
    public Vector change(Bird bird, Bird[] flock) {
        Vector v = new Vector(0, 0);

        if (bird.pos.x > MAX_X) {
            v.x = -RETURN_SPEED;
        } else if (bird.pos.x < MIN_X) {
            v.x = RETURN_SPEED;
        }

        if (bird.pos.y > MAX_Y) {
            v.y = -RETURN_SPEED;
        } else if (bird.pos.y < MIN_Y) {
            v.y = RETURN_SPEED;
        }

        return v;
    }
}
