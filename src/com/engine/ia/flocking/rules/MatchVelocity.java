package com.engine.ia.flocking.rules;

import com.engine.ia.flocking.Rule;
import com.engine.npcs.Bird;
import com.engine.utils.Vector;

public class MatchVelocity extends Rule {

    private final double DISTANCE;

    public MatchVelocity(double distance) {
        DISTANCE = distance;
    }

    @Override
    public Vector change(Bird bird, Bird[] flock) {
        Vector v = bird.vel;

        int i = 1;

        for (Bird b : flock) {
            if (b != bird && Vector.mag(Vector.sub(b.pos, bird.pos)) < DISTANCE) {
                v = Vector.add(v, b.vel);
                i++;
            }
        }

        v = Vector.divScalar(v, i);

        return Vector.divScalar(Vector.sub(v, bird.vel), 8);
    }
}
