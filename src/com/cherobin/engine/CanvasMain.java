package com.cherobin.engine;

import java.awt.Color;
import java.awt.Graphics2D; 
import java.util.ArrayList;

public class CanvasMain extends MyCanvas {
 
	public ArrayList<Waypoint> waypoints;
	public ArrayList<Alien> aliens;
	
	public CanvasMain() {
		initArrays();
		initWaypoints();
		initAliens();
	}

	public void initArrays() {
		waypoints = new ArrayList<Waypoint>(10);
		aliens = new ArrayList<Alien>(10);
	}
	
	public void initWaypoints() {
		waypoints.add(new Waypoint(10, 100));
		waypoints.add(new Waypoint(400, 100));
		waypoints.add(new Waypoint(400, 200));
		waypoints.add(new Waypoint(10, 200));
		waypoints.add(new Waypoint(10, 300));
		waypoints.add(new Waypoint(400, 300)); 
	}
	
	public void initAliens() {
		
		Alien alienWayPoint = new Alien(10, 200, 60, 60);
		alienWayPoint.setWaypoints(waypoints);
		aliens.add(alienWayPoint);
		
		Alien alien = new Alien(10, 300, 60, 60);
		aliens.add(alien);
		
	}
	
	@Override
	public void gameUpdate(long diffTime) { 
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).gameUpdate(diffTime);
		}
	}
 

	@Override
	public void gameRender(Graphics2D dbg) { 

		dbg.setColor(Color.DARK_GRAY);
		dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).gameRender(dbg);
		}

	}

}
