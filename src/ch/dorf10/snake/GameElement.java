package ch.dorf10.snake;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameElement {
	protected Rectangle masse;

	protected GameElement(Rectangle masse) {
		this.masse = masse;
	}
	
	public void draw(Graphics g) {}
	
	public Rectangle getMasse() {
		return masse;
	}

	public void setMasse(Rectangle masse) {
		this.masse = masse;
	}
	
	public boolean colides(SchlangenKopf head) {
		return masse.intersects(head.getMasse()) && this != head;
	}
}
