package com.engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class Waypoint extends Sprite {
	
	int id; 
	float waytingTime;
	
	public Waypoint (int id, float x, float y, float waytingTie) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.waytingTime = waytingTie;
		width = height = 5;
	}
	
	public boolean atWaypoint(Sprite other ) {
		if(squareCollision(other)) {
			return true;
		}
		return false;
	}
	
	public boolean squareCollision(Sprite b) {
		if( x < b.x + b.width &&
			x + width > b.x &&
			y < b.y + b.height &&
			y + height > b.y) {
			return true; 
		} else {
			return false;
		}
	}

	@Override
	public void gameUpdate(long diffTime) {
	 
		
	}

	@Override
	public void gameRender(Graphics2D dbg) {
		dbg.setColor(Color.yellow);
		dbg.fillOval((int) x, (int) y, width, height);
	}
	
}