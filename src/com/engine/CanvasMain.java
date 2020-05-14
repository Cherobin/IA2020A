package com.engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.engine.ia.flocking.Flock;
import com.engine.ia.flocking.FlockManager;
import com.engine.ia.flocking.rules.KeepDistance;
import com.engine.ia.flocking.rules.KeepInBounds;
import com.engine.ia.flocking.rules.KeepTowardCentre;
import com.engine.ia.flocking.rules.MatchVelocity;
import com.engine.utils.Utils;

public class CanvasMain extends MyCanvas {
 
	public ArrayList<Waypoint> waypoints;
	public ArrayList<Alien> aliens;
	public BufferedReader loadFile;
	FlockManager flockManager;
	
	
	public CanvasMain() {
		init();
		initWaypoints();
		initAliens();
	}

	public void init() {
		waypoints = new ArrayList<Waypoint>(10);
		aliens = new ArrayList<Alien>(10);
		
		flockManager = new FlockManager();
		
	    Flock f1 = new Flock(20, 300, 500, -100, 500, -100, Color.RED);
        Flock f2 = new Flock(100, 300, 500, -100, 500, -100, Color.BLUE);
        
        f1.addRule(new KeepInBounds(500, -500, 500, -500, 10));
        f1.addRule(new KeepDistance(40, f1));
        f1.addRule(new KeepDistance(40, f2));
        f1.addRule(new KeepTowardCentre(80));
        f1.addRule(new MatchVelocity(160));
        flockManager.add(f1);

        f2.addRule(new KeepInBounds(400, -100, 400, -100, 10));
        f2.addRule(new KeepDistance(20, f2));
        KeepDistance rule = new KeepDistance(60, f1);
        rule.setWeight(-1);
        f2.addRule(rule);
        f2.addRule(new KeepTowardCentre(200));
        f2.addRule(new MatchVelocity(200));
        flockManager.add(f2);
	}
	
	//TODO: FIXED-ME 
	public void initWaypoints() {
	
		String line = "";
		try {
			loadFile = Utils.loadFile(this.getClass(), "waypoints.csv");
			//nextLine cabeçalho
			loadFile.readLine();
			 
			while((line = loadFile.readLine())!=null) {
				String[] col = line.split(";");
				int id = Integer.parseInt(col[0]) ;
				float x = Float.parseFloat(col[1]);
				float y = Float.parseFloat(col[2]);
				float waytingTime = Float.parseFloat(col[3]); 
				waypoints.add(new Waypoint(id, x, y, waytingTime));
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		}
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
		
		for (int i = 0; i < waypoints.size(); i++) {
			waypoints.get(i).gameUpdate(diffTime);
		}
		
		flockManager.update(diffTime);
	}
 

	@Override
	public void gameRender(Graphics2D dbg) { 

		dbg.setColor(Color.DARK_GRAY);
		dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).gameRender(dbg);
		}
		
		for (int i = 0; i < waypoints.size(); i++) {
			waypoints.get(i).gameRender(dbg);
		}
		
		flockManager.render(dbg);
	}

}
