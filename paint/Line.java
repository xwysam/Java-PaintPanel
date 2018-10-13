package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Line extends Shape{
	private Point end;
	
	public Line(Point start, Color color,Point end, int thickness) {
		super(start, color,"outline",thickness);
		this.end = end;
	}

	public Point getEnd() {
		return end;
	}
	
	public void setfillcolor(Color color) {
		
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.setStroke(new BasicStroke(getThickness()));
		g2d.drawLine(this.getStart().getX(), this.getStart().getY(), this.getEnd().getX(), this.getEnd().getY());
		
	}
}
