package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model; 
	
	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	private ThicknessChooserPanel thicknessChooserPanel;
	private ZoomPanel zoomPanel;
	
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		
		Container c=this.getContentPane();
		// c.add(new JButton("North"),BorderLayout.NORTH);
		// c.add(new JButton("South"),BorderLayout.SOUTH);
		// c.add(new JButton("East"),BorderLayout.EAST);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.colorChooserPanel = new ColorChooserPanel(this);
		this.thicknessChooserPanel = new ThicknessChooserPanel(this);
		this.zoomPanel = new ZoomPanel(model, this);
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
		c.add(this.colorChooserPanel,BorderLayout.SOUTH);
		c.add(this.thicknessChooserPanel,BorderLayout.EAST);
		c.add(this.zoomPanel,BorderLayout.NORTH);
		
		this.model = model;
		
		this.paintPanel = new PaintPanel(model, this);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		this.pack();
		// this.setSize(200,200);
		this.setVisible(true);
	}

	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	public ColorChooserPanel getcolorChooserPanel() {
		return this.colorChooserPanel;
	}
	
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	public ThicknessChooserPanel getThicknessChooserPanel() {
		return this.thicknessChooserPanel;
	}
	
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	
		if (e.getActionCommand() == "Undo") {
			this.model.Undo();
		}
	
		if (e.getActionCommand() == "Copy") {
			if (this.model.getSelectShape() != null) {
				this.model.copy(this.model.getSelectShape());
			}
		}
		
		if (e.getActionCommand() == "Paste") {
			if (this.model.getSelectShape() != null) {
				this.model.paste();
				this.paintPanel.getPaintModel().update();
			}
		}
	}
}
