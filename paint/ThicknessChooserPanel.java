package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This is the Panel for choosing the thickness.
 * @author YIFANJIANG
 *
 */

public class ThicknessChooserPanel extends JPanel implements ActionListener{
	private View view;
	private ArrayList<JButton> buttonlist = new ArrayList<>();
	
	public ThicknessChooserPanel(View v) {
		this.view = v;
		String[] buttonLabels = { "Thin", "Median", "Thick"};
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			button.setBorder(BorderFactory.createRaisedBevelBorder());
			this.buttonlist.add(button);
			button.addActionListener(this);
		}
		for(JButton b: this.buttonlist) {
			this.add(b);
		}
	}
	
	private void lowbutton(int x) {
		riseallbutton();
		this.buttonlist.get(x).setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	private void riseallbutton() {
		for (JButton button:this.buttonlist) {button.setBorder(BorderFactory.createRaisedBevelBorder());}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "Thin" :
			this.view.getPaintPanel().setStroke(1);
			lowbutton(0);
			
			break;
		case "Median" :
			this.view.getPaintPanel().setStroke(5);
			lowbutton(1);
			
			break;
		case "Thick" :
			this.view.getPaintPanel().setStroke(10);
			lowbutton(2);
			
			break;
		}
		
		
		
	}	
	}
