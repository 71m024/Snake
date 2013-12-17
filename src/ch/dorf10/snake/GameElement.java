package ch.dorf10.snake;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameElement {
	protected Rectangle masse;
	
	protected GameElement(Rectangle masse) {
		this.masse = masse;
	}
	
	public void draw(Graphics g) {}
}
