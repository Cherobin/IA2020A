package com.cherobin.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Alien extends Sprite {
 
	public ArrayList<Waypoint> waypoints;
 
	private int vel;
	private Waypoint target;
	
	private int indexWaypoint;
	
	public Alien(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = true;
		vel = 100;
	}

	public void setWaypoints(ArrayList<Waypoint> waypoints) {
		this.waypoints = waypoints;
		indexWaypoint = 0;
		nextTarget();
	}
	
	private void nextTarget() { 
		target = waypoints.get(nextIndexWaypoint());
	}
	
	private int nextIndexWaypoint() {
		 	if(indexWaypoint < waypoints.size()-1) {
		 		indexWaypoint++; 
		 		return indexWaypoint;
		 	} 
		 	indexWaypoint = 0;
		 	return indexWaypoint;
	}
	
	@Override
	public void gameUpdate(long diffTime) {
		if(waypoints == null) {
			x += vel * diffTime/1000.0f;
		} else { 
			moveToWaypont(diffTime);
		} 
	}
	
	double getAngToWaypoint() {  
		float difx = target.x - x;
		float dify = target.y - y; 
		return Math.atan2(dify, difx); 
	}
	
	void moveToWaypont(long diffTime) {
		double ang = getAngToWaypoint();
		double velX = vel * Math.cos(ang);
		double velY = vel * Math.sin(ang);
		x += velX * diffTime/1000.0f;
		y += velY * diffTime/1000.0f;
		
		if(target.atWaypoint(this)) {
			nextTarget();
		}  
	}
	

	@Override
	public void gameRender(Graphics2D dbg) {
		dbg.setColor(Color.YELLOW);
		dbg.fillOval( (int) x, (int) y, width, height);
		
		dbg.drawString("" + (waypoints == null), x, y-10);
		dbg.drawString("index " + indexWaypoint, x+ 30, y-10);
	}

}
