package com.spil3141.spil.graphics;

import java.util.Random;

public class Renderer {

		private int width,height;
		public int[] pixels;
		
		private Random random = new Random();
		public final int MAP_SIZE = 64;
		public final int MAP_SIZE_MASK = MAP_SIZE -1;
		private int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // Represents tiles on the screen 
		
		
		public Renderer(int width, int height) {
			this.width = width;
			this.height = height;
			this.pixels = new int[width * height];
			
			//assign random colors to all the tiles
			for (int i=0;i<this.tiles.length;i++) { this.tiles[i] =  random.nextInt(0xffffff)/*(i % 2 == 0 ? 0xffffff : 0x000000)*/;}
			this.tiles[0] = 0x000000;
		}
		
		public void clear() 
		{
			for(int i = 0;i<pixels.length;i++) { pixels[i] = 0x000000; }
		}
		
		public void render(int xOffSet, int yOffSet) 
		{
			for(int y=0;y<height;y++)
			{
				int yy = y + yOffSet;
//				if( yy < 0 || yy >= height) break;
				for(int x=0; x<width;x++) 
				{
					int xx = x + xOffSet;
//					if( xx < 0 || xx >= width) break;
					int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
					pixels[x + ( y*width)] = this.tiles[tileIndex];
				}  	
			}
		}
		
}
