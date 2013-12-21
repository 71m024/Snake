package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.omg.CORBA.IntHolder;

public class Diamant extends GameElement {

	private static final long serialVersionUID = -1978284598779466410L;
	
	private int points;
	private boolean catched = false;
	
	public int getPoints() {
		return points;
	}

	public Diamant(Rectangle masse, IntHolder unit, int points) {
		super(masse, unit);
		this.points = points;
	}
	
	public void setCatched() {
		catched = true;
	}
	
	public boolean isCatched() {
		return catched;
	}
	
	@Override
	public boolean colides(SchlangenKopf head) {
		if (!catched && super.colides(head)) {
			head.eatDiamant(this);
			return true;
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		if (!catched) {
			Rectangle masse = getAbsoluteRect();
			
			g.setColor(Color.cyan);
			g.fillOval(
				(int)masse.getX(),
				(int)masse.getY(),
				(int)masse.getWidth(),
				(int)masse.getHeight());
		}
	}
}
