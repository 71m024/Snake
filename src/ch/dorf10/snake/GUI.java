package ch.dorf10.snake;

import java.awt.Rectangle;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Die Klasse GUI stellt das Fenster (JFrame) und
 * das Spielfeld (JPanel) des Snake-Spiels zur Verf?gung.
 * 
 * @author A. Scheidegger, M.Frieden
 * @version 4.0
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private Spielfeld spielfeld;
	private Game game;



    /**
     * Konstruktor. Initialisiert den Frame. Registriert das Game-Objekt
     * als KeyListener.
     */
    public GUI() {
        setTitle("Snake Timo");
        setBounds(new Rectangle(595, 624));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        game = new Game(getBounds().getSize());
        spielfeld = new Spielfeld(game);
        setContentPane(spielfeld);
        setVisible(true);
        setFocusable(true);
        addKeyListener(spielfeld);
        spielfeld.startGameLoop();
    }
}
