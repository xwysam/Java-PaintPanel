package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
/**
 * set up a new Polyline that people can use.
 * @author wangyang xia
 *
 */
public class Polyline extends Line{
	public Polyline(Point start, Color color, Point end, int thickness) {
		super(start, color, end, thickness);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.drawLine(this.getStart().getX(), this.getStart().getY(), this.getEnd().getX(), this.getEnd().getY());
	}
}
