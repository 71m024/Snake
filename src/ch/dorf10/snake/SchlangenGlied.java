package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class SchlangenGlied extends GameElement {

	private static final long serialVersionUID = 6662504687803015816L;
	
	private Color color;
	protected SchlangenGlied nextGlied;
	protected SchlangenGlied previousGlied;

	protected SchlangenGlied(Rectangle masse, IntHolder unit, Color color) {
		super(masse, unit);
		this.color = color;
	}
	
	public void setNextGlied(SchlangenGlied glied) {
		if (nextGlied != null) {
			nextGlied.removePreviousGlied();
		}
		nextGlied = glied;
		if (glied.getPreviousGlied() != this) {
			glied.setPreviousGlied(this);
		}
	}
	
	public void setPreviousGlied(SchlangenGlied glied) {
		if (previousGlied != null) {
			previousGlied.removeNextGlied();
		}
		previousGlied = glied;
		if (glied.getNextGlied() != this) {
			glied.setNextGlied(this);
		}
	}
	
	public void removeNextGlied() {
		SchlangenGlied oldNextGlied = nextGlied;
		nextGlied = null;
		if (oldNextGlied != null) {
			nextGlied.removePreviousGlied();
		}
	}
	
	public void removePreviousGlied() {
		SchlangenGlied oldPreviousGlied = previousGlied;
		previousGlied = null;
		if (oldPreviousGlied != null) {
			previousGlied.removeNextGlied();
		}
	}
	
	public SchlangenGlied getNextGlied() {
		return nextGlied;
	}
	
	public SchlangenGlied getPreviousGlied() {
		return previousGlied;
	}
	
	protected void addGlied() {
		if (nextGlied == null) {
			
			//create/add new glied
			Rectangle newMasse = (Rectangle) getBounds().clone();
			newMasse.x -= getDirection().x * width;
			newMasse.y -= getDirection().y * height;
			SchlangenGlied newGlied = new SchlangenGlied(newMasse, unit, Zufallsgenerator.zufallsFarbe(color, 20));
			setNextGlied(newGlied);
			
		} else {
			nextGlied.addGlied();
		}
	}
	
	public void move() {
		this.x += getDirection().getX() * width;
		this.y += getDirection().getY() * height;
		if (nextGlied != null) {
			nextGlied.move();
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
		return new Point(previousGlied.x - x, previousGlied.y - y);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void addAllGlieder(List<SchlangenGlied> glieder) {
		glieder.add(this);
		if (nextGlied != null) {
			nextGlied.addAllGlieder(glieder);
		}
	}
	
	public static Point invertDirection(Point direction) {
		Point newDirection = (Point) direction.clone();
		newDirection.x *= -1;
		newDirection.y *= -1;
		return newDirection;
	}

	@Override
	public boolean colides(SchlangenKopf head) {
		if (super.colides(head) ? true : (nextGlied == null ? false : nextGlied.colides(head))) {
			//head.die();
			return true;
		}
		return false;
	}
}
