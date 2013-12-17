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
public class GUI extends JFrame {
	
	/**
	 * Referenz auf das Game-Objekt.
	 */
	private Game game;
	
	private Spielfeld spielfeld;


    /**
     * Konstruktor. Initialisiert den Frame. Registriert das Game-Objekt
     * als KeyListener.
     */
    public GUI(Game game) {
        this.game = game;
        setTitle("Snake V4.0");
        setBounds(new Rectangle(780, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        spielfeld = new Spielfeld(game);
       	spielfeld.setBounds(new Rectangle(800, 800));
        setContentPane(spielfeld);
        setVisible(true);
        spielfeld.addKeyListener(game.getKeyListener());
    }

    /**
     * Gibt die Breite des Spielfeldes zur?ck.
     * @return Breite des Spielfeldes.
     */
    public int getBreite() {
        return spielfeld.getWidth();
    }

    /**
     * Gibt die H?he des Spielfeldes zur?ck.
     * @return H?he des Spielfeldes
     */
    public int getHoehe() {
        return spielfeld.getHeight();
    }

}
