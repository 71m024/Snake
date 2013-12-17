package ch.dorf10.snake;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SchlangenKopf extends Schlangenglied implements KeyListener{
	
	public static final Point DIRECTION_LEFT = new Point(-1, 0);
	public static final Point DIRECTION_UP = new Point(0, -1);
	public static final Point DIRECTION_RIGHT = new Point(1, 0);
	public static final Point DIRECTION_DOWN = new Point(0, 1);
	public static final Point DIRECTION_START = DIRECTION_RIGHT;
	public static final int SLEEP_TIME_MILLS_DEFAULT = 500;
	
	private Point direction;

	protected SchlangenKopf(Rectangle masse) {
		super(masse);
		direction = DIRECTION_START;
	}
	
	@Override
	public void draw(Graphics g) {
		move();
		super.draw(g);
	}
	
	public void move() {
		masse.x += direction.getX() * Game.UNIT;
		masse.y += direction.getY() * Game.UNIT;
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
