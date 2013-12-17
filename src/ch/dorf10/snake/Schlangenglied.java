package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

public class Schlangenglied extends GameElement implements GliedMoveListener{
	
	private Schlangenglied nextGlied;
	private Set<GliedMoveListener> gliedMoveListeners = new HashSet<GliedMoveListener>();

	protected Schlangenglied(Rectangle masse) {
		super(masse);
	}
	
	
	public void addGlied() {
		
	}
	
	public void addGlied(Schlangenglied glied) {
		nextGlied = glied;
	}
	
	public void moveTo(Point pos) {
		masse.setLocation(pos);
		for (GliedMoveListener gliedMoveListener : gliedMoveListeners) {
			gliedMoveListener.gliedMoved(new GliedMoveEvent(pos));
		}
	}
    
	public void draw(Graphics g) {
		Rectangle masse = super.masse;
		g.setColor(Color.red);
		g.fillOval((int)masse.getX(), (int)masse.getY(), (int)masse.getWidth(), (int)masse.getHeight());
	}

	@Override
	public void gliedMoved(GliedMoveEvent event) {
		moveTo(event.getLastPosition());
	}
}
