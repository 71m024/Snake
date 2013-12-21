package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.omg.CORBA.IntHolder;

public class Spielgrenze extends GameElement {
	
	private static final long serialVersionUID = -4765133453327219906L;

	protected Spielgrenze(Rectangle masse, IntHolder unit) {
		super(masse, unit);
	}

	public void draw(Graphics g) {
		Rectangle bounds = getAbsoluteRect();
		g.setColor(new Color(255, 245, 230));
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(Color.black);
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	@Override
	public boolean colides(SchlangenKopf head) {
		if (!super.colides(head)) {
			head.die();
			return true;
		}
		return false;
	}
}
