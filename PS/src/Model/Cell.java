package Model;

import javax.swing.JButton;

import View.gridButton;

/** Cell Class
 * Author: Prince Sandhu
 *
 * This class represents the individual cell, from which the whole
 * game grid is to be constructed.
 */

public class Cell {
	
	private int index;
	private int underneath=0;
	private boolean dig;
	private gridButton plot;
	private boolean mark;
	public int bfsCount=0;
	protected boolean bfsEnable=true, turnIncrement=true;
	
	

	/**
	*@brief Constructor for the cell.
	*@details Constructor accepts one parameter, which is position of the cell.
	*@param position The position of the cell.
	*@date 14/10/2016.
	*/
	public Cell (int position, int color){
		this.index=position;
		this.dig=false;
		this.mark=false;
		this.plot= new gridButton(color);
	}

	/**
	*@brief Mutator method utilized to set the cell to the specified value.
	*@param num The value which the specified cell is to contain.
	*@date 14/10/2016
	*/
	public void underneath(int num){
		this.underneath=num;
		
	}

	/**
	*@brief Accessor method utilized to return what is underneath the button.
	*@return The value that is underneath the specified button.
	*@date 14/10/2016
	*/
	public int getUnderneath(){
		return this.underneath;
	}

	/**
	*@brief Accessor method utilized to return the index of the specified button. 
	*@return The index of the specified button.
	*@date 14/10/2016
	*/
	public int getIndex(){
		return this.index;
	}

	/**
	*@brief Accessor method utilized to return the instance of plot
	*@return The instance of plot.
	*@date 14/10/2016
	*/
	public gridButton getPlot(){
		return this.plot;
	}

	

	/**
	*@brief Accessor method utilized to return instance of the button.
	*@return The instance of the button.
	*@date 14/10/2016
	*/
	public JButton getButton() {
		return this.plot;
	}
	
	/**
	*@brief Mutator method utilized to provide field dig a boolean value based on if the cell corresponding to dig has been opened
	*@date 14/10/2016
	*/
	
	public void setDig(boolean b){
		this.dig=b;
	}

	/**
	*@brief Mutator method which sets the value of dig to true.
	*@date 14/10/2016
	*/
	public void setOpen() {
		this.plot.setIcon(underneath);
		this.plot.setEnabled(false);
	}

	/**
	*@brief Resets the contents of the cell.
	*@details Covers the cell with a button.
	*@date 14/10/2016
	*/
	public void resetCell() {
		this.dig=false;
		this.mark=false;
		this.plot.setVisible(true);
		this.underneath=0;
		this.plot.setIcon(null);
		this.plot.setEnabled(true);
		this.bfsCount=0;
		this.bfsEnable=true;
		this.turnIncrement=true;
	}

	/**
	*@brief Accessor method utilized to return the value of dig.
	*@return The boolean value of dig.
	*@date 14/10/2016
	*/
	public boolean getDig() {
		return this.dig;
	}
	/**
	*@brief Mutator method which ensures flagged button remain un-revealed
	*@date 14/11/2016
	*/

	public void setClosed() {
		if(this.mark==false){
		this.plot.setIcon(null);
		}else{
			this.plot.setIcon(-2);
		}
		this.plot.setEnabled(true);
	}
	/**
	*@brief Mutator method which facilitates marking a plot object with a flag
	*@date 14/11/2016
	*/
	public void flagSpace() {
		if(this.mark==false){
			this.mark=true;
			this.plot.setIcon(-2);
		}
		else if(this.mark==true){
			this.mark=false;
			this.plot.setIcon(null);
		}
	}
	/**
	*@brief Acessor method utilized to return the value of mark.
	*@return The boolean value of mark.
	*@date 16/11/2016
	*/
	public boolean getMark() {
		return this.mark;
	}
	/**
	*@brief Mutator method which facilitates the changing of button (plot's) color scheme.
	*Takes integer value as parameter (flag variable rep color)
	*@date 02/11/2016
	*/
	public void changePlotColor(int color) {
		this.plot.setPalette(color);
	}
}