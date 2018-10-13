package ca.utoronto.utm.paint;
import java.util.Observable;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;



public class ZoomPanel extends JPanel implements ActionListener, Observer {
	
	private static final Observable Observable = null;
	private static final Object Object = null;
	private View view; // So we can talk to our parent or other components of the view
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private int nwidth, nheight;
	private int x,y;
	
	public ZoomPanel(PaintModel model, View view) {	
		this.view=view;
		this.model = model;
		this.model.addObserver(this);
		JButton Zoomin = new JButton("Zoom in");
		Zoomin.addActionListener(this);
		this.add(Zoomin);
		JButton Zoomout = new JButton("Zoom out");
		Zoomout.addActionListener(this);
		this.add(Zoomout);
		this.setLayout(new GridLayout(1,2));
		
		}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Zoom in") {
			x = 2*x;
			y = 2*y;
			nwidth = 2 * view.getWidth();
			nheight = 2 * view.getHeight();
		};
		if (e.getActionCommand() == "Zoom out") {
			x = x/2;
			y = y/2;
			nwidth = view.getWidth()/2;
			nheight = view.getHeight()/2;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.view.getPaintPanel().repaint(x,y, nwidth, nheight);
	}
}
