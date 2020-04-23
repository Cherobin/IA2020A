package com.cherobin.engine;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Sprite {
	float x;
	float y;
	int width;
	int height; 
	boolean isAlive;
	BufferedImage image;
	
	public abstract void gameUpdate(long diffTime);
	public abstract void gameRender(Graphics2D dbg);
}
