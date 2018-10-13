package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;

public class Text extends Shape{
	private String text;
	
	public Text(Point start, Color color, String sty, String text, int thickness){
		super(start, color, sty, thickness);
		this.text = text;
	}
	

	public void setText(String text) {
		text = JOptionPane.showInputDialog("Please input text");
		this.text = text;
	}
	
	public void draw(Graphics2D g2d) {
		g2d = (Graphics2D) g2d;
		g2d.setColor(this.getColor());
		if (this.text != "") {
			g2d.drawString(text, this.getStart().getX(), this.getStart().getY());
		}
	}
	
}

