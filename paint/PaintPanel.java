package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private static final Color Color = null;
	private int i=0; 
	private boolean state = false;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Color color;
	private String mode; // modifies how we interpret input (could be better?
	private Circle circle; // the circle we are building
	private Rectangle1 rectangle; //the rectangle we are building
	private Square square; //the square we are building
	private Line line; //the straight line we are building.
	private Eraser eraser;//the earser we are building
	private Pencil pencil; //the pencil we are building
	private Polyline polyline; //the polyine we are building.
	private Brush brush;   //the brush that can be use.
	private AirBrush airBrush; //the airbrush that can be use.
	private int thickness;  //the thickness that can be change.
	private Text text; // the text that can add in to.
	private Graphics2D g2d;
	private String style; // outline style or fill in style.
	private Point currect;

	private Shape select;


	private Shapefactory shapefactory;

	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.style = "outline";
		this.mode="";
		this.model = model;
		this.model.addObserver(this);
		this.color = Color.BLACK;
		this.view=view;
		this.shapefactory = new Shapefactory(this.model);
		
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
        super.paintComponent(g); //paint background
        this.g2d = (Graphics2D) g; // lets use the advanced api
        this.g2d.setColor(Color.gray); 
        this.g2d.setStroke(new BasicStroke(getStroke()));
		i=i+1;
		
		ArrayList<Shape> shapes = this.model.getShape();
		for(Shape shape : shapes) {
			shape.draw(g2d); 
		}
		this.g2d.dispose();
	}
	/**
	 * set the color that the user pressed.
	 * @param string
	 */
	public void setdrawColor(Color string) {
		this.color = string;
	}
	/**
	 * the style can be select. and we can change the style of the shape.
	 * @param sty
	 */
	public void setstyle(String sty) {
		if (sty == "outline") {this.style = "outline";}
		if (sty == "fill") {this.style = "fill";}
	}
	
	/**
	 * the color that the straw selected and show the color
	 * @return color that straw selected.
	 */
	public Color getStrawcolor() {
		return this.color;
	}
	
	/**
	 * 
	 * @return thickness that the user selected
	 */
	public int getStroke() {
		return this.thickness;
	}
	
	/**
	 * set the thickness that user selected.
	 * @param thickness
	 */
	public void setStroke(int thickness) {
		this.thickness = thickness;
	}
	
	public PaintModel getPaintModel() {
		return this.model;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){

		this.setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR ));
		this.mode = mode;
		if (mode == "Straw" || mode == "Fill") {
			this.setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		if(mode == "Polyline"|| mode == "Line"|| mode =="Circle"|| mode == "Rectangle"|| mode =="Square") {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR ));
		}
		if(mode == "Text") {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR  ));
		}

	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		//when the user choose the mode and the polyline be selected .
		if(this.mode == "Polyline") {
			if(this.state == true) {
				Point start = new Point(e.getX(),e.getY());
				this.polyline.setEnd(start);
				this.model.addShapes(this.polyline);
				this.pencil = new Pencil(start, this.color, start, thickness);
			}
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Point start = new Point(e.getX(),e.getY());
		this.shapefactory.finishshape(this.mode, start);
		//shows which of the button that be selected. and start to use it.

		switch (this.mode) {
		
		case "Line":
			this.line.setEnd(start);
			this.model.addShapes(line);
			
			break;
		case "Eraser":
			this.eraser = new Eraser(start, this.getBackground(), 20);
			this.model.addShapes(this.eraser);

			break;
		case "Squiggle":
			this.pencil.setEnd(start);
			this.model.addShapes(this.pencil);
			this.pencil = new Pencil(start, this.color, start, thickness);
			
			break;
		case "Brush":
			this.brush.setEnd(start);
			this.model.addShapes(this.brush);
			this.brush = new Brush(start, this.color, start, thickness);
			
			break;
		case "AirBrush":
			Point start1 = new Point(this.airBrush.getStart().getX(),this.airBrush.getStart().getY());
			for (int i= 0; i<30;i++) {
				int a = this.airBrush.getrandnumber(30);
				int b = this.airBrush.getrandnumber(30);
				Point newstart = (new Point(start1.getX()+a,start1.getY()+b));
				Point newends = (new Point(start1.getX()+a+1,start1.getY()+b+1));
				this.model.addShapes(new AirBrush(newstart,this.color,newends,1));
			}
			this.airBrush = new AirBrush(start, this.color, start, thickness);
			
			break;
		case "Select":
			if (this.model.getSelectShape() != null && this.model.getSelectShape().havePoint(e.getX(), e.getY())) {
				Shape selected = this.model.getSelectShape();
				if (selected.getClass().getSimpleName().equals("Rectangle1")) {
					Rectangle1 rect = (Rectangle1) selected;
					int x = (start.getX() + rect.getCenter().getX()) / 2;
					int y = (start.getY() + rect.getCenter().getY()) / 2;
					Point p = new Point(x,y);
					rect.setStart(p);
				}else {
					selected.setStart(start);
				}
				
				selected.setColor(color.BLUE);
				this.model.addShapes(selected);
			}
			
			break;
		}

	}
	
	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		Point start = new Point(e.getX(),e.getY());
		switch (this.mode) {
		case "Straw" :
			int x = e.getXOnScreen();  
            int y = e.getYOnScreen();  
			try {
				Robot robot = new Robot();
				java.awt.Color col = robot.getPixelColor(x, y);
				setdrawColor(col);
				this.view.getcolorChooserPanel().chagecolor(this.color);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			
			break;
		case "Fill" :
			this.model.select(e.getX(), e.getY());
			if (this.model.getSelectShape() != null) {
				this.model.getSelectShape().setfillcolor(this.color);
				this.model.update();
				this.repaint();
			}
			break;
		case "Polyline" :
			this.polyline = new Polyline(start, this.color,start, thickness);
			this.state = true;
			if(e.getButton() == MouseEvent.BUTTON3) {
				this.polyline = null;
				this.state =false;
			}
			break;
		case "Text" :
			String text = "";
			this.text.setText(text);
			this.model.addShapes(this.text);
			this.text = new Text(start, this.color, this.style, "", thickness);
			this.model.update();
			break;
		case "Select":
			this.model.select(e.getX(), e.getY());
			if (this.model.getSelectShape() != null) {
				this.select = this.model.getSelectShape();
				this.model.getSelectShape().setColor(Color.blue);
				this.repaint();
			}
			
			
			
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point start = new Point(e.getX(), e.getY());
		this.shapefactory.createshape(this.mode, start, this.color, this.style, this.thickness);
		if (this.mode != "Select" && this.model.getSelectShape() != null) {
			this.model.getSelectShape().setColor(this.color);
			this.repaint();
		}
		
		switch (this.mode) {
		
		
		case "Line" :
			this.line = new Line(start,this.color,start, thickness);
			
			break;
		case "Eraser" :
			this.eraser=new Eraser(start, this.getBackground(), 20);
			
			break;
		case "Squiggle" :
			this.pencil = new Pencil(start, this.color, start, thickness);
			
			break;
		case "Brush" :
			this.brush = new Brush(start, this.color, start, thickness);
			
			break;
		case "AirBrush" :
			this.airBrush = new AirBrush(start, this.color, start, thickness);
			
			break;
		case "Text" :
			this.text = new Text(start, this.color, this.style, "", thickness);
			
			break;
		case "Select":
			if (this.model.getSelectShape() != null) {
				this.model.getSelectShape().setColor(this.color);
			}
			
			this.model.select(e.getX(), e.getY());
			if (this.model.getSelectShape() != null) {
				this.model.getSelectShape().setColor(Color.BLUE);
				this.repaint();
			}

			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(this.mode) {
		case "Rectangle" :
			this.model.update();
			break;
		case "Circle" :
			this.model.update();
			break;
		case "Square" :
			this.model.update();
			break;
		case "Squiggle" :
			this.model.update();
			break;
		case "Text" :
			this.model.update();
			break;
		case "AirBrush" :
			this.model.update();
			break;
		case "Brush" :
			this.model.update();
			break;
		case "Eraser" :
			this.model.update();
			break;
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
