package ch.dorf10.snake;

import java.awt.Point;

public interface GliedMoveListener {

	class GliedMoveEvent {
		private Point lastPosition;
		
		public GliedMoveEvent(Point penis) {
			lastPosition = penis;
		}

		public Point getLastPosition() {
			return lastPosition;
		}
	}
	
	public void gliedMoved(GliedMoveEvent event);
}
