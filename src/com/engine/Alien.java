package com.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Alien extends Sprite {
 
	public ArrayList<Waypoint> waypoints;
 
	private int vel;
	private Waypoint target;
	
	private int indexWaypoint;
	
	private float timerWaypoint;
	private int state;
	
	//TODO:: FIXED
	
	final int STATE_IDLE = 0;
	final int STATE_PATROL = 1;
	final int STATE_CHASE = 2;
	final int STATE_ATTACK = 3;
	final int STATE_RUN_AWAY = 4;
	
	private float timerIdle;
	private float timerPatrol;
	private float timer;
	
	//0% -> run away
	//100% -> attack
	
	public Alien(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = true;
		vel = 100;
		timerWaypoint = 0;
		state = STATE_PATROL;
		timerIdle = 0;
		timerPatrol = 0;
		timer = 0;
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
			simpleIA(diffTime);
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
			//TODO: change state
			timerIdle = target.waytingTime;
			state = STATE_IDLE;
			nextTarget();
		}  
	}
	
	public void simpleIA(long diffTime) {
		switch (state) {
		case STATE_IDLE:
			STATE_IDLE(diffTime);
			break;
		case STATE_PATROL:
			STATE_PATROL(diffTime);
			break;
		default:
			break;
		}
	}

	private void STATE_PATROL(long diffTime) {
		moveToWaypont(diffTime);
	}
	private void STATE_IDLE(long diffTime) {
		timer+=diffTime/1000.0f; 
		if(timer > timerIdle) {
			state = STATE_PATROL;
			timer = 0;
		}
	} 
	
	@Override
	public void gameRender(Graphics2D dbg) {
		dbg.setColor(Color.YELLOW);
		dbg.fillOval( (int) x, (int) y, width, height);
		
		dbg.drawString("" + (waypoints == null), x, y-10);
		dbg.drawString("index " + indexWaypoint, x+ 30, y-10);
		
		dbg.drawString("STATE - " + state, x, y-40);
		dbg.drawString("timer - " + timer, x, y-70); 
	}

}
