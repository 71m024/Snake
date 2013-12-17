package ch.dorf10.snake;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Spielfeld extends JPanel{
	
	private static final long serialVersionUID = 1134775569216322572L;
	private Game game;

	public Spielfeld(Game game) {
		super();
		
		this.game = game;
		
		this.setFocusable(true);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					Spielfeld.this.repaint();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
    		
    	}).start();
	}
		
	/**
	 * Wird automatisch aufgerufen, wenn das Fenster neu gezeichnet werden
	 * muss. Delegiert das Zeichnen des Spiels an das Game-Objekt.
	 * @param g Graphics-Objekt zum Zeichnen
	 */
	@Override
	public void paintComponent(Graphics g){
	  game.draw(g);
	}
	
}
