package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the Panel for choose the color.
 * @author michael
 *
 */
public class ColorChooserPanel extends JPanel implements ActionListener {
	private View view;
	private ArrayList<JButton> buttonlist = new ArrayList<>();
	private JButton[] stylelist = new JButton[2];
	private JLabel color = new JLabel();
	
	public ColorChooserPanel(View a) {
		this.view = a;
		JPanel style = new JPanel();
	
		
		JButton outline = new JButton("outline");
		outline.addActionListener(this);
		outline.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JButton fill = new JButton("fill");
		fill.addActionListener(this);
		fill.setBorder(BorderFactory.createRaisedBevelBorder());
		style.setLayout(new GridLayout(2,1));
		
	
		
		this.stylelist[0] = outline;
		this.stylelist[1] = fill;
		
		
		String [] ColorButton = {"blue","red","black","green","pink","cyan","orange"};

		JLabel jl = new JLabel("  Current Color:  ",JLabel.CENTER);
		jl.setFont(new   java.awt.Font("Dialog",   1,   13));
		
		
		style.add(outline);
		style.add(fill);
		style.add(this.color);
		
		
		
		this.setLayout(new GridLayout(1,ColorButton.length+3));

		this.add(style);
		this.add(jl);
		this.add(this.color);
		for(String o:ColorButton) {
			JButton jb = new JButton(o);
			jb.setBorder(BorderFactory.createRaisedBevelBorder()); 
			jb.setIcon(new ImageIcon(getClass().getResource(o+".png"))); 
			jb.addActionListener(this);
			this.buttonlist.add(jb);
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
		for (JButton jb:this.buttonlist) {jb.setBorder(BorderFactory.createRaisedBevelBorder());}
	}
	
	private void changecurrentcolor(String color) {
		this.color.setIcon(new ImageIcon(getClass().getResource(color+".png"))); 
		
		
		
	}
	public void chagecolor(Color color) {
		System.out.println(color);
		if (color.equals(new Color(1,0,255))) {lowbutton(0);changecurrentcolor("blue");}
		if (color.equals(new Color(255,0,0))) {lowbutton(1);changecurrentcolor("red");}
		if (color.equals(new Color(0,0,0))) {lowbutton(2);changecurrentcolor("black");}
		if (color.equals(new Color(0,255,0))) {lowbutton(3);changecurrentcolor("green");}
		if (color.equals(new Color(0,255,255))) {lowbutton(5);changecurrentcolor("cyan");}
		if (color.equals(new Color(255,187,0))) {lowbutton(6);changecurrentcolor("orange");}
		if (color.equals(new Color(255,151,157))) {lowbutton(4);changecurrentcolor("pink");}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "fill"){
			this.view.getPaintPanel().setstyle("fill");
			this.stylelist[1].setBorder(BorderFactory.createLoweredBevelBorder());
			this.stylelist[0].setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
		if(e.getActionCommand() == "outline"){
			this.view.getPaintPanel().setstyle("outline");
			this.stylelist[0].setBorder(BorderFactory.createLoweredBevelBorder());
			this.stylelist[1].setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
		if(e.getActionCommand() == "blue") {this.view.getPaintPanel().setdrawColor(Color.BLUE);

			chagecolor(Color.BLUE);
			this.color.setIcon(new ImageIcon(getClass().getResource("blue.png")));
			lowbutton(0);
		
		}
		
		if(e.getActionCommand() == "red") {this.view.getPaintPanel().setdrawColor(Color.RED);

			chagecolor(Color.RED);

			lowbutton(1);
		}
		
		if(e.getActionCommand() == "black") {this.view.getPaintPanel().setdrawColor(Color.BLACK);

			chagecolor(Color.BLACK);

			lowbutton(2);
		}
		
		if(e.getActionCommand() == "green") {this.view.getPaintPanel().setdrawColor(Color.GREEN);

			chagecolor(Color.GREEN);

			lowbutton(3);
		}
		if(e.getActionCommand() == "pink") {this.view.getPaintPanel().setdrawColor(Color.PINK);

		chagecolor(Color.PINK);

		lowbutton(4);
	}
		if(e.getActionCommand() == "cyan") {this.view.getPaintPanel().setdrawColor(Color.CYAN);

		chagecolor(Color.CYAN);

		lowbutton(5);
	}
		if(e.getActionCommand() == "orange") {this.view.getPaintPanel().setdrawColor(Color.ORANGE);

		chagecolor(Color.ORANGE);

		lowbutton(6);
	}
		
		
	}

}
