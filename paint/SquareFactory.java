package ca.utoronto.utm.paint;

import java.awt.Color;

public class SquareFactory extends Factory {
	private Square square;
	public SquareFactory(PaintModel model) {
		super(model);
	}
	
	public void createsquare(Point start,Color color,String style, int thickness) {
		this.square = new Square(start,color,0,style, thickness);
	}
	public void finishsquare(Point end) {
		double l = Math.sqrt(Math.pow(this.square.getStart().getX()-end.getX(), 2)+Math.pow(this.square.getStart().getY()-end.getY(), 2));
		int length = (int) l;
		this.square.setLength(length);
		super.getpaintmodel().addShapes(this.square);
		
	}
}
