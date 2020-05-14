package com.engine.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.engine.Sprite;

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
	
	public BufferedImage loadImage(String filename) {

		try {
			BufferedImage imgtmp = ImageIO.read(getClass().getResource(filename));
			BufferedImage imgfinal = new BufferedImage(imgtmp.getWidth(), imgtmp.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			imgfinal.getGraphics().drawImage(imgtmp, 0, 0, null);
			return imgfinal;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> BufferedReader loadFile(Class<T> nameClass, String filename) throws IOException {
		InputStream in = nameClass.getResourceAsStream(filename);
		BufferedReader file = new BufferedReader(new InputStreamReader(in));
		return file;
	}
	
}
