package ch.dorf10.snake;

import java.awt.Rectangle;

import javax.swing.JFrame;

/**
 * Die Klasse GUI stellt das Fenster (JFrame) und
 * das Spielfeld (JPanel) des Snake-Spiels zur Verf?gung.
 * 
 * @author A. Scheidegger, M.Frieden
 * @version 4.0
 */
@SuppressWarnings("serial")
public class GUI extends JFrame implements GameListener{
	
	private Spielfeld spielfeld;
	private Game game;

    public GUI() {
        setTitle("Snake Timo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setFocusable(true);
        setBounds(new Rectangle(595, 624));
        setExtendedState(this.MAXIMIZED_BOTH);         
        
        game = new Game(getBounds().getSize());
        spielfeld = new Spielfeld(game);
        
        setContentPane(spielfeld);
        addKeyListener(spielfeld);
        
        game.addGameListener(this);
        
        spielfeld.startGameLoop();
    }

	@Override
	public void win(GameEvent event) {
		endGame("Gewonnen", event.getPoints());
	}

	@Override
	public void loose(GameEvent event) {
		endGame("Verloren", event.getPoints());
	}
	
	private void endGame(String message, int points) {
		spielfeld.stopGameLoop();
		setTitle(message + "! [" + points + " PUNKT" + (points > 1 ? "E" : "") + "]");
	}
}
