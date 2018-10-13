package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * set up a shape circle that can be draw.
 * @author wangyang xia
 *
 */
public class Circle extends Shape{
	private int radius;
	public Circle(Point centre, int radius, Color color, String sty, int thickness){
		super(centre, color,sty,thickness);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public boolean havePoint(int x, int y) {
		double r = Math.sqrt(Math.pow(super.getStart().getX()-x, 2)+Math.pow(super.getStart().getY()-y, 2));
		int distence = (int) r;
		if (distence <= this.radius) {
			return true;
		}else {return false;}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(getThickness()));
		if(this.getStyle() == "fill") {
			g2d.fillOval(this.getStart().getX()-this.radius, this.getStart().getY()-this.radius, radius*2, radius*2);
		}else {
			g2d.drawOval(this.getStart().getX()-this.radius, this.getStart().getY()-this.radius, radius*2, radius*2);
		}
	}
}
