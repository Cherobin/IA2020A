package com.engine;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Sprite {
	public float x;
	public float y;
	public int width;
	public int height; 
	public boolean isAlive;
	public BufferedImage image;
	
	public abstract void gameUpdate(long diffTime);
	public abstract void gameRender(Graphics2D dbg);
}
