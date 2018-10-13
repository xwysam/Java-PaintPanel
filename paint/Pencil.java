package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * set up the pencil so that user can draw on the board.
 * @author wangyang xia
 *
 */
public class Pencil extends Line {

	public Pencil(Point center, Color color, Point end, int thickness) {
		super(center, color, end, thickness);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(getThickness()));
		g2d.drawLine(this.getStart().getX(), this.getStart().getY(), this.getEnd().getX(), this.getEnd().getY());
	}

}
