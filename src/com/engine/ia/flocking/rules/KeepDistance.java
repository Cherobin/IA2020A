package com.engine.ia.flocking.rules;

import com.engine.ia.flocking.Flock;
import com.engine.ia.flocking.Rule;
import com.engine.npcs.Bird;
import com.engine.utils.Vector;

public class KeepDistance extends Rule {

    private final double DISTANCE;
    private final Flock FLOCK;
    
    public KeepDistance(double distance, Flock flock) {
        DISTANCE = distance;
        FLOCK = flock;
    }

    @Override
    public Vector change(Bird bird, Bird[] flock) {
        Vector c = new Vector(0, 0);

        for (Bird b : FLOCK.get()) {
            if (b != bird) {
                if (Vector.mag(Vector.sub(b.pos, bird.pos)) < DISTANCE) {
                    c = Vector.sub(c, Vector.sub(b.pos, bird.pos));
                }
            }
        }

        return Vector.divScalar(c, 8);
    }
}
