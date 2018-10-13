package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Brush extends Line{
	public Brush(Point center, Color color, Point end, int thickness) {
		super(center, color, end, thickness);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(10));
		g2d.drawLine(this.getStart().getX(), this.getStart().getY(), this.getEnd().getX(), this.getEnd().getY());
		g2d.setStroke(new BasicStroke());
	}
}
