package ca.utoronto.utm.paint;

import java.awt.Color;

public class CircleFactory extends Factory{
	private Circle circle;
	public CircleFactory(PaintModel model) {
		super(model);
	}
	
	public void createcircle(Point start, Color color,String style, int thickness) {
		this.circle = new Circle(start, 0, color, style, thickness);
	}
	public void finishcircle(Point end) {
		double r = Math.sqrt(Math.pow(this.circle.getStart().getX()-end.getX(), 2)+Math.pow(this.circle.getStart().getY()-end.getY(), 2));
		int radius = (int) r;
		(this.circle).setRadius(radius);
		super.getpaintmodel().addShapes(this.circle);
		
	}
}
