package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SchlangenKopf extends SchlangenGlied implements KeyListener{
	
	private boolean alive = true;

	protected SchlangenKopf(Rectangle masse, Color color) {
		super(masse, color);
		direction = DIRECTION_START;
	}
	
	public void die() {
		alive = false;
	}
	
	public void eatDiamant(Diamant dia) {
		for (int i = 0; i < dia.getPoints(); i++) {
			addGlied();
		}
		dia.setCatched();
	}
	
	@Override
	public void draw(Graphics g) {
		if (alive) {
			super.move();
		}
		super.draw(g);
		
		//draw eyes
		g.setColor(Color.WHITE);
		g.fillOval((int)(masse.getX() + Game.UNIT * 0.2), (int)(masse.getY() + Game.UNIT * 0.4), (int)(masse.getWidth() / 6), (int)(masse.getHeight() / 6));
		g.fillOval((int)(masse.getX() + Game.UNIT * 0.7), (int)(masse.getY() + Game.UNIT * 0.4), (int)(masse.getWidth() / 6), (int)(masse.getHeight() / 6));
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			direction = SchlangenKopf.DIRECTION_LEFT;
			break;
		case KeyEvent.VK_UP:
			direction = SchlangenKopf.DIRECTION_UP;
			break;
		case KeyEvent.VK_RIGHT:
			direction = SchlangenKopf.DIRECTION_RIGHT;
			break;
		case KeyEvent.VK_DOWN:
			direction = SchlangenKopf.DIRECTION_DOWN;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
