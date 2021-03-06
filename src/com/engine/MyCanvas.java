package com.engine;
import java.awt.Graphics2D; 

public abstract class MyCanvas {
	/*
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
	
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
	
	public abstract void mouseWheelMoved(MouseEvent e);
	*/
	
	public abstract void gameUpdate(long diffTime);
	public abstract void gameRender(Graphics2D dbg);
 
}
