package View;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Controller.mouseControl;

/** smileButton Class
 * Author: Vishesh Gulatee and Varun Rathore
 *
 * This class represents the Smile/reset button and all of
 * the actions associated with it.
 * This class inherits methods from JButton GUI component
 */
public class smileButton extends JButton {
	//ImageIcon used to describe game state along with adding positive esthetics
	private ImageIcon smile=new ImageIcon(getClass().getResource("/res/smile.png"));
	private ImageIcon happy=new ImageIcon(getClass().getResource("/res/happy.png"));
	private ImageIcon angel=new ImageIcon(getClass().getResource("/res/angel.png"));
	private ImageIcon smug=new ImageIcon(getClass().getResource("/res/smug.png"));
	//Color components of color scheme
	private Color colLight;
	private Color colDark;
	private int palette;
	private boolean over;
	/**
	*@brief Constructor for the smile button.
	*@details Constructor accepts one parameter, the mouse click.
	*@param mc the mouse click.
	 * @param theme 
	*@date 16/10/2016.
	*/
	public smileButton(mouseControl mc, int theme){
		this.addActionListener(mc);
		this.setSize(36, 36);
		this.setIcon(smile);
		this.setVisible(true);
		this.updateUI();
		this.palette=theme;
		over=false;
	}
	/**
	*@brief paintComponent method (inherited)
	*@details Method changes the color scheme of the GUI component based on value of palette
	*@parameter method takes in a graphics object, used to paint color scheme
	*@date 12/10/2016.
	*/
	protected void paintComponent(Graphics g) {
		if(palette==0){
			
			setContentAreaFilled(true);
		}else{
			
			this.setContentAreaFilled(false);
			if (palette==1){
				 colLight= new Color(135, 206, 235);
				 colDark= new Color(176, 196, 222);
				 
			}
			else if(palette==2){
				colLight= new Color(255, 239, 213);
				 colDark= new Color(205, 133, 63);
				
			}
			else if(palette==3){
				colLight= new Color(65, 105, 225);
				 colDark= new Color(25, 25, 112);
				
			}
			else if(palette==4){
				colLight= new Color(155, 252, 0);
				 colDark= new Color(34, 139, 34);
				
			}
			else if(palette==5){
				colLight= new Color(192, 192, 192);
				 colDark= new Color(112,128,144);
				 
				
			}
			else if(palette==6){
				colLight= new Color(255,182,193);
				 colDark= new Color(255,105,180);
			}
		 Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp;
         gp= new GradientPaint(0, 0, colLight, 0, getHeight(),colDark);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
       
   }
		super.paintComponent(g);
	}
	/**
	*@brief changeHappy() 
	*@details Method changes the icon of the smileButton object when a cell in the grid is clicked
	*@date 12/10/2016.
	*/
	public void changeHappy() {
		if(this.over==false){
		this.setIcon(happy);
		}
	}
	/**
	*@brief changeBack() 
	*@details Method changes the icon  of the smileButton object to default when a click operation on a cell in the grid is released
	*@date 12/10/2016.
	*/
	public void changeBack() {
		if(this.over==false){
		this.setIcon(smile);
		}
	}
	/**
	*@brief funeral() 
	*@details Method changes the icon of the smileButton object to an angel signifying the end of the game and the players loss
	*@date 12/10/2016.
	*/
	public void funeral() {
		this.setIcon(angel);
		//Indicates game is over
		this.over=true;
	}
	/**
	*@brief setContinue()
	*@details Method resets the icon to default once a new game has started
	*@date 12/10/2016.
	*/
	public void setContinue() {
		this.over=false;
		changeBack();
	}
	/**
	*@brief celebration() 
	*@details Method changes the icon of the smileButton object to a 'stud' signifying the end of the game and the players win
	*@date 12/10/2016.
	*/
	public void celebration() {
		//Indicates game is over
		this.setIcon(smug);
		this.over=true;
	}
	/**
	*@brief setPalette () 
	*@details Method changes the visual theme selected
	*@param Method takes integer value that represents flag value of color scheme
	*@date 12/10/2016.
	*/
	public void setPalette(int color) {
		this.palette=color;
	}
}