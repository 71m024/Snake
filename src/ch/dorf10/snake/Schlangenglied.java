package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

public class SchlangenGlied extends GameElement {

	public static final Point DIRECTION_LEFT = new Point(-1, 0);
	public static final Point DIRECTION_UP = new Point(0, -1);
	public static final Point DIRECTION_RIGHT = new Point(1, 0);
	public static final Point DIRECTION_DOWN = new Point(0, 1);
	public static final Point DIRECTION_START = DIRECTION_RIGHT;
	
	protected Point direction;
	
	private Color color;
	private SchlangenGlied nextGlied;

	protected SchlangenGlied(Rectangle masse, Color color) {
		super(masse);
		this.color = color;
	}
	
	protected void addGlied() {
		if (nextGlied == null) {
			
			//create/add new glied
			Rectangle newMasse = (Rectangle) masse.clone();
			newMasse.x -= direction.x * Game.UNIT;
			newMasse.y -= direction.y * Game.UNIT;
			SchlangenGlied newGlied = new SchlangenGlied(newMasse, color.brighter());
			newGlied.setDirection((Point) direction.clone());
			nextGlied = newGlied;
			
		} else {
			nextGlied.addGlied();
		}
	}
	
	public void move() {
		masse.x += direction.getX() * Game.UNIT;
		masse.y += direction.getY() * Game.UNIT;
		if (nextGlied != null) {
			nextGlied.move();
			nextGlied.setDirection((Point) direction.clone());
		}
	}
    
	public void draw(Graphics g) {
		Rectangle masse = super.masse;
		g.setColor(color);
		g.fillOval((int)masse.getX(), (int)masse.getY(), (int)masse.getWidth(), (int)masse.getHeight());
		if (nextGlied != null) {
			nextGlied.draw(g);
		}
	}

	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point direction) {
		this.direction = direction;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean colides(SchlangenKopf head) {
		if (super.colides(head) ? true : (nextGlied == null ? false : nextGlied.colides(head))) {
			head.die();
			return true;
		}
		return false;
	}
}
