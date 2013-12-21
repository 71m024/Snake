package ch.dorf10.snake;

public class GameEvent {
	
	private int points = 0;
	
	public GameEvent(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
}
