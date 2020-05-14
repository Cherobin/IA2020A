package com.engine.utils;

public class Vector {

    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector add(Vector v1, Vector v2) {
        double x = v1.x + v2.x;
        double y = v1.y + v2.y;
        return new Vector(x, y);
    }

    public static Vector addAll(Vector[] all) {
        Vector v = new Vector(0, 0);
        for (Vector a : all) {
            v = Vector.add(v, a);
        }
        return v;
    }

    public static Vector sub(Vector v1, Vector v2) {
        double x = v1.x - v2.x;
        double y = v1.y - v2.y;
        return new Vector(x, y);
    }

    public static Vector multScalar(Vector v1, double s1) {
        double x = v1.x * s1;
        double y = v1.y * s1;
        return new Vector(x, y);
    }

    public static Vector divScalar(Vector v1, double s1) {
        double x = v1.x / s1;
        double y = v1.y / s1;
        return new Vector(x, y);
    }

    public static double mag(Vector v1) {
        return Math.sqrt(Math.pow(v1.x, 2) + Math.pow(v1.y, 2));
    }
}
