package com.cherobin.engine;

public class Waypoint {
	
	float x, y;
	int width, height;
	
	public Waypoint (float x, float y) {
		this.x = x;
		this.y = y;
		width = height = 10;
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
	
}