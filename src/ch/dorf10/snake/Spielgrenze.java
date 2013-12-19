package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spielgrenze extends GameElement {
	
	private static final long serialVersionUID = -4765133453327219906L;

	protected Spielgrenze(Rectangle masse, IntHolder unit) {
		super(masse, unit);
	}

	public void draw(Graphics g) {
		Rectangle bounds = getAbsoluteRect();
		g.setColor(new Color(226, 255, 255));
		g.fillRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
		g.setColor(Color.black);
		g.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
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
