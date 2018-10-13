package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * set up an 20*20 eraser that can use to erase the stuf that we drawed.
 * @author wangyang xia
 *
 */

public class Eraser extends Square{


	public Eraser(Point s, Color color, int l) {
		super(s, color, l, "", 0);
		
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(g2d.getBackground());
		g2d.clearRect(this.getStart().getX(), this.getStart().getY(), 20, 20);	
	}
}
