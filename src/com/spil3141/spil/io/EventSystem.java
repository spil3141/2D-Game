package com.spil3141.spil.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventSystem implements KeyListener{

	private boolean[] keys = new boolean[120];
	public boolean up,down,left,right;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
	}
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}