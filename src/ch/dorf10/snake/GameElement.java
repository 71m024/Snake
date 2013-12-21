package ch.dorf10.snake;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.omg.CORBA.IntHolder;

public abstract class GameElement extends Rectangle{

	private static final long serialVersionUID = 8553525279457955212L;
	
	protected IntHolder unit;

	protected GameElement(Rectangle masse, IntHolder unit) {
		super(masse);
		this.unit = unit;
	}
	
	public abstract void draw(Graphics g);
	
	public boolean colides(SchlangenKopf head) {
		return getBounds().intersects(head.getBounds()) && this != head;
	}
	
	public Rectangle getAbsoluteRect() {
		Rectangle absRect = new Rectangle();
		int unitVal = unit.value;
		absRect.x = x * unitVal;
		absRect.y = y * unitVal;
		absRect.width = width * unitVal;
		absRect.height = height * unitVal;
		return absRect;
	}
}
