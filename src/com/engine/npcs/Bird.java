package com.engine.npcs;

import java.awt.Color;
import java.awt.Graphics2D;

import com.engine.utils.Vector;

public class Bird {

	public Vector pos;
	public Vector vel;
	public Color color;

	public Bird(Vector pos, Vector vel, Color color) {
		this.pos = pos;
		this.vel = vel;
		this.color = color;
	}

	public void render(Graphics2D dbg) {
		dbg.setColor(color);
		dbg.fillOval((int) pos.x, (int) pos.y, 20, 20);
	}

	public void move(long diffTime) {
		pos = Vector.add(pos, Vector.multScalar(vel, diffTime / 1000f));
	}

	public static Vector randomPos(int maxX, int minX, int maxY, int minY) {
		double x = (Math.random() * (maxX - minX)) + minX;
		double y = (Math.random() * (maxY - minY)) + minY;
		return new Vector(x, y);
	}

	public static Vector randomVel(double maxSpeed) {
		double minSpeed = maxSpeed * -1;
		double x = (Math.random() * (maxSpeed - minSpeed)) + minSpeed;
		double y = (Math.random() * (maxSpeed - minSpeed)) + minSpeed;

		Vector v = new Vector(x, y);

		if (Vector.mag(v) > maxSpeed) {
			v = Vector.multScalar(Vector.divScalar(v, Vector.mag(v)), maxSpeed);
		}

		return v;
	}

}
