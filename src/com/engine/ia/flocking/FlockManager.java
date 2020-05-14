package com.engine.ia.flocking;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class FlockManager {

    private final ArrayList<Flock> flocks;

    public FlockManager() {
        flocks = new ArrayList<>();
    }

    public void update(long diffTime) {
        for (Flock flock : flocks) {
            flock.update(diffTime);
        }
    }
    
    public void render(Graphics2D dbg) {
        for (Flock flock : flocks) {
            flock.render(dbg);
        }
    }
   

    public void add(Flock flock) {
        flocks.add(flock);
    }

    public Flock[] flock() {
        return flocks.toArray(new Flock[flocks.size()]);
    }
}