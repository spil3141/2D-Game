package com.spil3141.spil;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.spil3141.spil.graphics.Renderer;
import com.spil3141.spil.io.EventSystem;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	private static final int VERSION = 1;
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	
	private boolean running = false;
	public Thread gameThread;
	private JFrame frame;
	private EventSystem key;
	
	
	private Renderer renderer;
	
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
	private int[]  pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
			
	//Debug
	int x = 0;
	int y = 0;
	
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale); 
		setPreferredSize(size);
		
		renderer = new Renderer(width,height);
		frame = new JFrame();
		
		key = new EventSystem();
		//Add EventListener
		addKeyListener(key);
		
	}
	
	public synchronized void start() {
		System.out.println("Welcome to Spil Engine V-" + VERSION);
		this.running = true;
		gameThread = new Thread(this,"Game Thread");
		gameThread.start();
	}
	
	public synchronized void stop(){
		this.running = false;
		try {
			gameThread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0/ 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		//put the canvas in focus 
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;	
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.frame.setTitle("Spil Engine | " + "us: " + updates + " fps: " + frames);
//				System.out.println("up: " + updates + " fps: " + frames);
				frames = 0;
				updates = 0;
			}
			
		}
		stop();
	}
	
	public void update() {
		//Input CallBack
		this.key.update();
		if(this.key.right) this.x--;
		if(this.key.left) this.x++;

		if(this.key.up) this.y++;
		if(this.key.down) this.y--;

	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return; 
		}

		renderer.clear();
		renderer.render(this.x,y);
		
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = renderer.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		{//graphics goes here
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(image, 0,0,getWidth(),getHeight(),null);
		}
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.pack(); // set size of frame to its subcomponents
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null); // Position window in the middle of screen
//		game.frame.setTitle("Spil Engine");
		game.frame.setVisible(true);
		
		game.start();
		
	}

}

