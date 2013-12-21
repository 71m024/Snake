package ch.dorf10.snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener{
	
	private static final long serialVersionUID = 1134775569216322572L;
	private Game game;
	private Deque<KeyEvent> inputEvents = new LinkedList<KeyEvent>();
	private boolean gameLoopRunning = false;

	public Spielfeld(Game game) {
		super();
		this.game = game;
		this.setFocusable(true);
	}
	
	public void startGameLoop() {
		gameLoopRunning = true;
		double lastTime = System.currentTimeMillis();
		while (gameLoopRunning) {
			double current = System.currentTimeMillis();
			double elapsed = current - lastTime;
		
			game.processInput(inputEvents);
			game.update(elapsed);
			repaint();
			
			lastTime = current;
		}
	}
	
	public void stopGameLoop() {
		gameLoopRunning = false;
	}
		
	/**
	 * Wird automatisch aufgerufen, wenn das Fenster neu gezeichnet werden
	 * muss. Delegiert das Zeichnen des Spiels an das Game-Objekt.
	 * @param g Graphics-Objekt zum Zeichnen
	 */
	@Override
	public void paintComponent(Graphics g){
		game.setSize(getSize());
		game.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		inputEvents.addLast(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
