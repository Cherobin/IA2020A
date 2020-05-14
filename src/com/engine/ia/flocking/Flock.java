package com.engine.ia.flocking;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.engine.npcs.Bird;
import com.engine.utils.Vector;

public class Flock {

    private final Bird[] flock;

    private final ArrayList<Rule> rules;

    private double maxSpeed;

    private Color color;

    public Flock(int flockSize, double maxSpeed, int maxX, int minX, int maxY, int minY, Color color) {
        this.maxSpeed = maxSpeed;

        flock = new Bird[flockSize];
        for (int i = 0; i < flockSize; i++) {
            flock[i] = new Bird(Bird.randomPos(maxX, minX, maxY, minY), Bird.randomVel(maxSpeed), color);
        }

        rules = new ArrayList<>();

        color = new Color((int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55, (int) (Math.random() * 200) + 55);
    }

    public Color color() {
        return color;
    }

    public Bird[] get() {
        return flock;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void update(long diffTime) {
        for (Bird b : flock) {
            calculateVelocity(b);
            limitSpeed(b);
        }
        for (Bird b : flock) {
            b.move(diffTime);
        }
    }
    
    public void render (Graphics2D dbg) {
    	   for (Bird b : flock) {
               b.render(dbg);
           }
    }

    private void calculateVelocity(Bird b) {
        Vector change = new Vector(0, 0);
        for (Rule rule : rules) {
            change = Vector.add(change, rule.getChange(b, flock));
        }
        b.vel = Vector.add(b.vel, change);
    }

    private void limitSpeed(Bird b) {
        if (Vector.mag(b.vel) > maxSpeed) {
            b.vel = Vector.multScalar(Vector.divScalar(b.vel, Vector.mag(b.vel)), maxSpeed);
        }
    }
}
