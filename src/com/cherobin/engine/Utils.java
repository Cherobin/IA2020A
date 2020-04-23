package com.cherobin.engine;

public class Utils {

	public static boolean squareCollision(Sprite a, Sprite b) {
		if( a.x < b.x + b.width &&
			a.x + a.width > b.x &&
			a.y < b.y + b.height &&
			a.y + a.height > b.y) {
			return true; 
		} else {
			return false;
		}
	}
}
