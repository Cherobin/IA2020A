package com.engine.ia.flocking.rules;

import com.engine.ia.flocking.Rule;
import com.engine.npcs.Bird;
import com.engine.utils.Vector;

public class KeepTowardCentre extends Rule {

    private final double DISTANCE;

    public KeepTowardCentre(double distance) {
        DISTANCE = distance;
    }

    @Override
    public Vector change(Bird bird, Bird[] flock) {
        Vector p = bird.pos;

        int i = 1;

        for (Bird b : flock) {
            if (b != bird && Vector.mag(Vector.sub(b.pos, bird.pos)) < DISTANCE) {
                p = Vector.add(p, b.pos);
                i++;
            }
        }

        p = Vector.divScalar(p, i);

        return Vector.divScalar(Vector.sub(p, bird.pos), 100);
    }
}
