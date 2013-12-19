package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
    private List<GameElement> gameElements = new ArrayList<GameElement> ();
    private SchlangenKopf schlange;
    private Spielgrenze spielgrenze;
    private double elapse = 0;
    private IntHolder unit = new IntHolder(-1);
    
    public static final int MOVE_TIME_MILLS = 300;
    public static final Dimension FIELD_DIMENSION = new Dimension(20, 20);
    public static final int DIAMONDS_NUMER_MAX = 50;
    public static final int DIAMONDS_NUMER_MIN = 50;
    public static final int DIAMONDS_POINTS_MIN = 1;
    public static final int DIAMONDS_POINTS_MAX = 3;
    
    public Game(Dimension size) {
    	generateUnit(size);
    	Dimension spielgrenzeSize = new Dimension(FIELD_DIMENSION.width, FIELD_DIMENSION.height);
    	spielgrenze = new Spielgrenze(
    			new Rectangle(new Point(1, 1), spielgrenzeSize),
    			unit);
    	
    	schlange = new SchlangenKopf(new Rectangle(
	    			(int)spielgrenze.getX() + 2,
	    			(int)spielgrenze.getY() + 2,
	    			1,
	    			1),
	    		unit,
    			Color.GREEN);
    	
    	gameElements.add(spielgrenze);
    	
    	for (int i = 1; i < getRandomNumber(DIAMONDS_NUMER_MIN, DIAMONDS_NUMER_MAX); i++) {
    		Rectangle diamantRect = new Rectangle(
    				(int)spielgrenze.getX() + getRandomNumber(0, FIELD_DIMENSION.width - 1),
    				(int)spielgrenze.getY() + getRandomNumber(0, FIELD_DIMENSION.height - 1),
    				1,
    				1);
    		gameElements.add(new Diamant(diamantRect, unit, getRandomNumber(DIAMONDS_POINTS_MIN, DIAMONDS_POINTS_MAX)));
    	}
    	
    	gameElements.add(schlange);
    }
    
    private int getRandomNumber(int min, int max) {
    	return Zufallsgenerator.zufallszahl(min, max);
    }
    
    public KeyListener getKeyListener() {
    	return schlange;
    }
    
    public void update(double elapse) {
    	this.elapse += elapse;
    	while (this.elapse > MOVE_TIME_MILLS) {
    		this.elapse -= MOVE_TIME_MILLS;
    		
    		schlange.move();
    	}
    }

    public void draw(Graphics g) {
    	for (GameElement gameElement : gameElements) {
    		gameElement.colides(schlange);
    		gameElement.draw(g);
    	}
    }

	public void setSize(Dimension size) {
		generateUnit(size);
	}
	
	public void generateUnit(Dimension size) {
		unit.set(size.width < size.height ? size.width / (FIELD_DIMENSION.width + 2) : size.height / (FIELD_DIMENSION.height + 2));
	}
}
