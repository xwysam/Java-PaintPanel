package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * crate the shape that we need and it's a father of all the shapes. 
 * @author wangyang xia
 *
 */
public class Shape implements DrawingCommand, Cloneable {
	private Color color;
	private Point start;
	private String style;
	private int thickness;
	public Shape(Point start, Color color, String sty, int thickness) {
		this.color = color;
		this.start = start;
		this.style = sty;
		this.thickness = thickness;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setfillcolor(Color color) {
		this.color = color;
		this.style = "fill";
	}
	
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
		
	}
	public void setStyle(String stye) {
		
		this.style = stye;
	}
	
	public String getStyle() {
		return this.style;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}
	
	public boolean havePoint(int x, int y) {
		return true;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
	}
}
