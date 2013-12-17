package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spielgrenze extends GameElement {
	
	protected Spielgrenze(Rectangle masse) {
		super(masse);
	}

	public void draw(Graphics g) {
		Rectangle bounds = super.masse;
		g.setColor(new Color(226, 255, 255));
		g.fillRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
		g.setColor(Color.black);
		g.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
	}
	
	public int getY() {
		return super.masse.y;
	}
	
	public int getX() {
		return super.masse.x;
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
