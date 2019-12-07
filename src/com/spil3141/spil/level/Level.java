package com.spil3141.spil.level;

import com.spil3141.spil.graphics.Renderer;

public class Level {
	private int width, height;
	private int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}
	
	public Level (String path) {
		loadLevel(path);
	}
	private void generateLevel() {
		
	}
	private void loadLevel(String path) {
		
	}
	public void update() {
		
	}
	private void time() {
		
	}
	public void render(int xScroll, int yScroll, Renderer renderer) {
		
	}
}
