package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class SchlangenGlied extends GameElement {

	private static final long serialVersionUID = 6662504687803015816L;
	
	public static final Point DIRECTION_LEFT = new Point(-1, 0);
	public static final Point DIRECTION_UP = new Point(0, -1);
	public static final Point DIRECTION_RIGHT = new Point(1, 0);
	public static final Point DIRECTION_DOWN = new Point(0, 1);
	public static final Point DIRECTION_START = DIRECTION_RIGHT;
	
	protected Point direction;
	
	private Color color;
	private SchlangenGlied nextGlied;

	protected SchlangenGlied(Rectangle masse, IntHolder unit, Color color) {
		super(masse, unit);
		this.color = color;
	}
	
	protected void addGlied() {
		if (nextGlied == null) {
			
			//create/add new glied
			Rectangle newMasse = (Rectangle) getBounds().clone();
			newMasse.x -= direction.x * width;
			newMasse.y -= direction.y * height;
			SchlangenGlied newGlied = new SchlangenGlied(newMasse, unit, Zufallsgenerator.zufallsFarbe(color, 20));
			newGlied.setDirection((Point) direction.clone());
			nextGlied = newGlied;
			
		} else {
			nextGlied.addGlied();
		}
	}
	
	public void move() {
		this.x += direction.getX() * width;
		this.y += direction.getY() * height;
		if (nextGlied != null) {
			nextGlied.move();
			nextGlied.setDirection((Point) direction.clone());
		}
	}
    
	public void draw(Graphics g) {
		if (nextGlied != null) {
			nextGlied.draw(g);
		}
		
		Rectangle masse = getAbsoluteRect();
		
		g.setColor(color);
		g.fillOval((int)masse.getX(), (int)masse.getY(), (int)masse.getWidth(), (int)masse.getHeight());
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
