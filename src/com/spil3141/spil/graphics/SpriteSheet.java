package com.spil3141.spil.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

		private String path;
		public final int SIZE; 
		
		public int[] pixels;
		
		public static SpriteSheet tiles = new SpriteSheet("/textures/MainSprite.png",256); 
		
		public SpriteSheet(String path,int size) {
			SIZE = size;
			this.path = path;
			pixels = new int[SIZE * SIZE]; 
			this.load();
		}
		
		private void load()
		{
			try {
				BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
				image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
