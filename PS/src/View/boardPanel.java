package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import View.gridButton;
import javax.swing.JPanel;

import Controller.mouseControl;
import Model.JSweepGameControl;

/** Board Panel Class
 * Author: Vishesh Gulatee
 *
 * This class represents the graphical user interface of the PineSweeper
 * game board (grid).
 */
public class boardPanel extends JPanel {
	Color gainsboro= new Color(220, 220, 220);
	Color gray= new Color(128, 128, 128);
	mouseControl mc = new mouseControl();
	private int rows;
	private int cols;
	private int length;
	private int breadth;

	/**
	*@param i 
	 * @brief Constructor boardPanel
	*
	*@date 14/10/2016.
	*/
	public boardPanel(int i){
		
		this.setVisible(true);
		if(i==9){
			rows=cols=9;
			breadth=243; length=236;
		}
		else if(i==16){
			rows=cols=16;
			breadth=432; length=418;
		}
		else if(i==24){
			rows=cols=24;
			breadth=648; length=626;
		}
		
		this.setSize(breadth,length);
		this.setLayout(new GridLayout(rows, cols));
		this.addMouseListener(mc);
		this.setUpButton(null);
		
	}
	/**
	*@brief Paints each of the components in the container.
	*@param g the graphical representation of the mine grid.
	*@date 14/10/2016.
	*/
	protected void paintComponent(Graphics g){
		try{
			super.paintComponent(g);
			//drawGrid(g);
			draw();
		}
		catch(NullPointerException e){
		}
	}
	/**
	*@brief Adds the buttons, which disappear when clicked upon, to the grid.
	*@author Prince Sandhu.
	*@param g the graphical representation of the minegrid.
	*@date 12/10/2016.
	*/
	
	
	private void setUpButton(Graphics g) {
		try{
			for (int i = 0; i < rows*cols; i++) {
				gridButton button = JSweepGameControl.plotGenerater(i);
				button.addActionListener(mc);
				button.addMouseListener(mc);
				button.setVisible(true);
				this.add(button);
			}
		}
		
		catch(NullPointerException e){	
		}
	}

	public void draw(){
		super.revalidate();
		super.repaint();
	}
}