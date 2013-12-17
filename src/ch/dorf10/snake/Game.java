package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
    private List<GameElement> gameElements = new ArrayList<GameElement> ();
    private SchlangenKopf schlange;
    public static final int UNIT = 37;
    
    public Game() {
    	Spielgrenze spielgrenze = new Spielgrenze(new Rectangle(10, 10, 20 * UNIT, 20 * UNIT));
    	schlange = new SchlangenKopf(new Rectangle(
    			spielgrenze.getX() + 2*UNIT,
    			spielgrenze.getY() + 2*UNIT,
    			1*UNIT,
    			1*UNIT), new Color(140, 0, 0));
    	
    	gameElements.add(spielgrenze);
    	for (int i = 1; i < getRandomNumber(4, 12); i++) {
    		Rectangle diamantRect = new Rectangle(
    				spielgrenze.getX() + getRandomNumber(0, 19) * UNIT,
    				spielgrenze.getY() + getRandomNumber(0, 19) * UNIT,
    				1 * UNIT,
    				1 * UNIT);
    		gameElements.add(new Diamant(diamantRect, i));
    	}
    	gameElements.add(schlange);
    }
    
    private int getRandomNumber(int min, int max) {
    	return Zufallsgenerator.zufallszahl(min, max);
    }
    
    public KeyListener getKeyListener() {
    	return schlange;
    }

    public void draw(Graphics g) {
    	for (GameElement gameElement : gameElements) {
    		gameElement.colides(schlange);
    		gameElement.draw(g);
    	}
    }

}