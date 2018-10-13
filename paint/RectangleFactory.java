package ca.utoronto.utm.paint;

import java.awt.Color;

public class RectangleFactory extends Factory {
	private Rectangle1 rectangle;
	
	public RectangleFactory(PaintModel model) {
		super(model);
	}
	
	public void creatrectangle( Point start,Color color,String style , Point start1, int thickness) {
		 this.rectangle = new Rectangle1(start,color,0, 0,style , start1, thickness);
	}
	
	public void finishrectangle(Point end) {
		Point s = new Point(Math.min(this.rectangle.getCenter().getX(), end.getX()),Math.min(this.rectangle.getCenter().getY(), end.getY()));
		int width = Math.abs(this.rectangle.getCenter().getX() - end.getX());
		int height = Math.abs(this.rectangle.getCenter().getY() - end.getY());
		this.rectangle.setStart(s);
		this.rectangle.setHeight(height);
		this.rectangle.setWidth(width);
		super.getpaintmodel().addShapes(this.rectangle);
	
	}
}
