package com.engine.ia.flocking;

import com.engine.npcs.Bird;
import com.engine.utils.Vector;

public abstract class Rule {

    double weight;

    public Rule() {
        weight = 1;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public Vector getChange(Bird bird, Bird[] flock) {
        return Vector.multScalar(change(bird, flock), weight);
    }

    public abstract Vector change(Bird bird, Bird[] flock);
}
