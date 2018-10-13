package ca.utoronto.utm.paint;

import java.awt.Color;

public class Shapefactory extends Factory {
	private CircleFactory circlefactory;
	private RectangleFactory rectangelfactory;
	private SquareFactory squarefactory;
	public Shapefactory(PaintModel model) {
		super(model);
		this.circlefactory = new CircleFactory(super.getpaintmodel());
		this.rectangelfactory = new RectangleFactory(super.getpaintmodel());
		this.squarefactory = new SquareFactory(super.getpaintmodel());
	}
	
	public void createshape(String mode,Point start, Color color,String style,int thickness) {
		switch (mode) {
		case "Circle":
			this.circlefactory.createcircle(start, color, style, thickness);
			break;
		case "Rectangle":
			this.rectangelfactory.creatrectangle(start,color,style , start, thickness);
			break;
		case "Square":
			this.squarefactory.createsquare(start, color, style, thickness);
			break;
		}
	}
	
	public void finishshape(String mode,Point end) {
		switch(mode) {
		case"Circle":
			this.circlefactory.finishcircle(end);
			break;
		case "Rectangle":
			this.rectangelfactory.finishrectangle(end);
			break;
		case "Square":
			this.squarefactory.finishsquare(end);
			break;

	}
	}
	
}
