package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class AirBrush extends Line{
	
	Random random = new Random();
	
	public AirBrush(Point center, Color color, Point end, int thickness) {
		super(center, color, end, thickness);
	}

	public int getrandnumber(int bond) {
		return random.nextInt(bond);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.drawLine(super.getStart().getX() , super.getStart().getY(), super.getEnd().getX(), super.getEnd().getY());
	}
}

