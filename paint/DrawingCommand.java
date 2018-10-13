package ca.utoronto.utm.paint;

import java.awt.Graphics2D;

public interface DrawingCommand {
	public void draw(Graphics2D g2d);
}
