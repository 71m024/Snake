package ch.dorf10.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.CORBA.IntHolder;

public class Game {
	
    private List<GameElement> gameElements = new ArrayList<GameElement> ();
    private SchlangenKopf schlange;
    private Set<Diamant> diamanten = new HashSet<Diamant>();
    private double elapsed = 0;
    private IntHolder unit = new IntHolder(-1);
    private Map<Integer, Point> keyMapping = new HashMap<Integer, Point>();
    private Set<GameListener> listeners = new HashSet<GameListener>();
    private int fps;
    
    public static final int MOVE_TIME_MILLS = 200;
    public static final Dimension FIELD_DIMENSION = new Dimension(25, 25);
    public static final int DIAMONDS_NUMBER_MAX = 50;
    public static final int DIAMONDS_NUMBER_MIN = 40;
    public static final int DIAMONDS_POINTS_MIN = 3;
    public static final int DIAMONDS_POINTS_MAX = 5;
    public static final boolean FPS_SHOW = true;
    
    public Game(Dimension size) {
    	generateUnit(size);
    	initKeyMapping();
    	Dimension spielgrenzeSize = new Dimension(FIELD_DIMENSION.width, FIELD_DIMENSION.height);
    	Spielgrenze spielgrenze = new Spielgrenze(
    			new Rectangle(new Point(1, 1), spielgrenzeSize),
    			unit);
    	
    	schlange = new SchlangenKopf(
    			new Rectangle(
	    			(int)spielgrenze.getX() + 2,
	    			(int)spielgrenze.getY() + 2,
	    			1,
	    			1),
	    		unit,
    			Color.GREEN);
    	
    	gameElements.add(spielgrenze);
    	
    	for (int i = 1; i < Zufallsgenerator.zufallszahl(DIAMONDS_NUMBER_MIN, DIAMONDS_NUMBER_MAX) + 1; i++) {
    		Rectangle diamantRect = new Rectangle(
    				(int)spielgrenze.getX() + Zufallsgenerator.zufallszahl(0, FIELD_DIMENSION.width - 1),
    				(int)spielgrenze.getY() + Zufallsgenerator.zufallszahl(0, FIELD_DIMENSION.height - 1),
    				1,
    				1);
    		Diamant newDiamant = new Diamant(diamantRect, unit, Zufallsgenerator.zufallszahl(DIAMONDS_POINTS_MIN, DIAMONDS_POINTS_MAX));
    		gameElements.add(newDiamant);
    		diamanten.add(newDiamant);
    	}
    	
    	gameElements.add(schlange);
    }
    
    private void initKeyMapping() {
    	keyMapping.put(KeyEvent.VK_UP, SchlangenKopf.DIRECTION_UP);
    	keyMapping.put(KeyEvent.VK_DOWN, SchlangenKopf.DIRECTION_DOWN);
    	keyMapping.put(KeyEvent.VK_RIGHT, SchlangenKopf.DIRECTION_RIGHT);
    	keyMapping.put(KeyEvent.VK_LEFT, SchlangenKopf.DIRECTION_LEFT);
    }
    
    public void update(double elapsed) {
    	this.elapsed += elapsed;
    	while (this.elapsed > MOVE_TIME_MILLS) {
    		this.elapsed -= MOVE_TIME_MILLS;
    		
    		for (GameElement element : gameElements) {
    			element.colides(schlange);
    		}
    		
    		schlange.move();
    	}
    	
    	//check if there are catchable diamonds
    	boolean catchableDias = false;
    	for (Diamant dia : diamanten) {
    		if (!dia.isCatched()) {
    			catchableDias = true;
    		}
    	}
    	if (!catchableDias) {
    		for (GameListener listener : listeners) {
    			listener.win(new GameEvent(schlange.getLength()));
    		}
    	}
    	
    	//check if game is lost
    	if (!schlange.isAlive()) {
    		for (GameListener listener : listeners) {
    			listener.loose(new GameEvent(schlange.getLength()));
    		}
    	}
    	
    	//update fps
    	fps = (int) (1000 / elapsed);
    }

    public void draw(Graphics g) {
    	for (GameElement gameElement : gameElements) {
    		gameElement.draw(g);
    	}
    	if (FPS_SHOW) {
    		g.setColor(Color.RED);
    		int position = unit.value * 2;
    		g.drawString("FPS: " + Integer.toString(fps), position, position);
    	}
    }

	public void setSize(Dimension size) {
		generateUnit(size);
	}
	
	public void generateUnit(Dimension size) {
		unit.value = (size.width < size.height ? size.width / (FIELD_DIMENSION.width + 2) : size.height / (FIELD_DIMENSION.height + 2));
	}

	public void processInput(Deque<KeyEvent> events) {
		
		int eventNumber = events.size();

		for (int i = 0; i < eventNumber; i++) {
			KeyEvent event = events.pollFirst();
			Point direction = keyMapping.get(event.getKeyCode());
			if (direction != null) {
				schlange.setDirection(direction);
			}
		}
	}
	
	public void addGameListener(GameListener listener) {
		listeners.add(listener);
	}
}
