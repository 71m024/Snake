package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Diamant extends GameElement {
	private int points;
	private boolean catched = false;
	
	public int getPoints() {
		return points;
	}

	public Diamant(Rectangle masse, int points) {
		super(masse);
		this.points = points;
	}
	
	public void setCatched() {
		catched = true;
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
			Rectangle masse = super.masse;
			
			g.setColor(Color.cyan);
			g.fillOval(
					(int)masse.getX(),
					(int)masse.getY(),
					(int)masse.getWidth(),
					(int)masse.getHeight());
			g.setColor(Color.black);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			g.drawString(Integer.toString(points), (int)masse.getX() + (int)(masse.getWidth() / 2.6), (int)masse.getY() + (int)(masse.getHeight() / 1.4));
		}
	}
}
