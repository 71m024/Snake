package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Diamant extends GameElement {
	private int points;
	
	public Diamant(Rectangle masse, int points) {
		super(masse);
		this.points = points;
	}
	
	@Override
	public void draw(Graphics g) {
		
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
