package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * set up a new rectangle that people can use
 * @author wangyang xia
 *
 */
public class Rectangle1 extends Shape{
	private int width;
	private int height;
	private Point center;
	
	public Rectangle1(Point s,Color c, int h, int w, String sty, Point center, int thickness) {
		super(s,c,sty,thickness);
		this.height = h;
		this.width = w;
		this.center = center;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean havePoint(int x, int y) {
		if(this.xInRange(x) && this.yInRange(y) ) {
			return true;
		}
		
		return false;
	}
	
	private boolean xInRange(int x) {
		return x <= this.getStart().getX()+(this.width) && x >= this.getStart().getX();
	}
	
	private boolean yInRange(int y) {
		return y <= this.getStart().getY()+(this.height) && y >= this.getStart().getY();
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(getThickness()));
		if (this.getStyle() == "fill") {g2d.fillRect(this.getStart().getX(), this.getStart().getY(), width, height);}
		if (this.getStyle() == "outline") {g2d.drawRect(this.getStart().getX(), this.getStart().getY(), width, height);}	
	}
}
