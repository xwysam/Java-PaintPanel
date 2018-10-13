package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * set up a shaoe of square .
 * @author wangyang xia
 *
 */
public class Square extends Shape{
	private int length;
	
	public Square(Point s, Color color, int l, String sty, int thickness) {
		super(s,color,sty,thickness);
		this.length = l;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	public boolean havePoint(int x, int y) {
		if(xInRange(x) && yInRange(y)) {
			return true;
		}
		
		return false;
	}
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(getThickness()));
		if (this.getStyle() == "fill") {g2d.fillRect(this.getStart().getX() - this.length, this.getStart().getY()- this.length, length*2, length*2);}
		if (this.getStyle() == "outline") {g2d.drawRect(this.getStart().getX()- this.length, this.getStart().getY()- this.length, length*2, length*2);}	
		
	}
	
	private boolean xInRange(int x) {
		return x <= this.getStart().getX() + (this.length / 2) && x >= this.getStart().getX() - (this.length / 2);
	}
	
	private boolean yInRange(int y) {
		return y <= this.getStart().getY() + (this.length / 2) && y >= this.getStart().getY() - (this.length / 2);
	}
}
