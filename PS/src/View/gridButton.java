package View;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.KeyStroke;
/** gridButton Class
 * Author: Vishesh Gulatee
 *
 * Class gridButton inherits from GUI class JButton/
 * This class represents the buttons used to form the gird of cell datatype object plot in the  View implementation. 
 * The class ensures that the visual scheme of the component is updated whilst also providing methods inherited from JButton
 *
 */
public class gridButton extends JButton {
	//ImageIcons used to describe the contents of the plot objects of Cell data type
	private ImageIcon one=new ImageIcon(getClass().getResource("/res/one.png")); 
	private ImageIcon two=new ImageIcon(getClass().getResource("/res/two.png")); 
	private ImageIcon three=new ImageIcon(getClass().getResource("/res/three.png")); 
	private ImageIcon four=new ImageIcon(getClass().getResource("/res/four.png")); 
	private ImageIcon five=new ImageIcon(getClass().getResource("/res/five.png")); 
	private ImageIcon six=new ImageIcon(getClass().getResource("/res/six.png")); 
	private ImageIcon pmine= new ImageIcon(getClass().getResource("/res/PineMine.png")); 
	private ImageIcon flag=new ImageIcon(getClass().getResource("/res/flag.png")); //ImageIcon of a flag
	//Three colors that make up the color scheme of the button
	private Color colLight;
	private Color colDark;
	private Color colFade;

	//Representation of theme integer value from GameManager
	private int palette;
	/**
	*@brief Constructor for gridButton
	*@details Constructor sets up the basic features exhibited by the button model for the grid space in the gaming application
	*@date 12/10/2016.
	*/
	
	public gridButton(int color){
			this.palette=color;
	        setFocusPainted(false);
	        setOpaque(false);
	        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none"); 
	        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none"); 
	}
	/**
	*@brief paintComponent method
	*@details Method changes the color scheme of the GUI component based on value of palette
	*@parameter method takes in a graphics object, used to paint color scheme
	*@date 12/10/2016.
	*/
	protected void paintComponent(Graphics g) {
		if(palette==0){
			setContentAreaFilled(true);
			
		}else{
			if (palette==1){
				 colLight= new Color(135, 206, 235);
				 colDark= new Color(176, 196, 222);
				 colFade= new Color(240, 248, 255);
			}
			else if(palette==2){
				colLight= new Color(255, 239, 213);
				 colDark= new Color(205, 133, 63);
				 colFade= new Color(245, 222, 179);
			}
			else if(palette==3){
				colLight= new Color(65, 105, 225);
				 colDark= new Color(25, 25, 112);
				 colFade= new Color(70, 130, 180);
			}
			else if(palette==4){
				colLight= new Color(155, 252, 0);
				 colDark= new Color(34, 139, 34);
				 colFade= new Color(144, 238, 144);
			}	
			else if(palette==5){
				colLight= new Color(192, 192, 192);
				 colDark= new Color(112,128,144);
				 colFade= new Color(220, 220, 220);
			}
			else if(palette==6){
				colLight= new Color(255,182,193);
				 colDark= new Color(255,105,180);
				 colFade= new Color(255, 192, 203);
			}
		setContentAreaFilled(false);
		 Graphics2D g2d = (Graphics2D) g;
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                 RenderingHints.VALUE_ANTIALIAS_ON);
         GradientPaint gp;
         if(isEnabled()==true){
          gp= new GradientPaint(0, 0, colLight, 0, getHeight(),colDark);
      
         }else{
        	gp = new GradientPaint(0, 0, colFade, 0, getHeight(), colFade);
         }
         g2d.setPaint(gp);
         g2d.fillRect(0, 0, getWidth(), getHeight());
        
		}
		super.paintComponent(g);
    }
	/**
	*@brief setIcon method
	*@details Method sets the icon corresponding to the content of the particular button of the GUI component based on value of rep
	*@parameter method takes in an integer value that represents flag variable used to describe the content of the cell
	*@date 12/10/2016.
	*/
	public void setIcon(int rep) {
		if(rep==1){
			super.setIcon(one);
			super.setDisabledIcon(one);
			}
			else if(rep==2){
				super.setIcon(two);
				super.setDisabledIcon(two);
			}
			else if(rep==3){
				super.setIcon(three);
				super.setDisabledIcon(three);
			}
			else if(rep==4){
				super.setIcon(four);
				super.setDisabledIcon(four);
			}
			else if(rep==5){
				super.setIcon(five);
				super.setDisabledIcon(five);
			}
			else if(rep==6){
				super.setIcon(six);
				super.setDisabledIcon(six);
			}
			else if (rep==-1){
				super.setIcon(pmine);
				super.setDisabledIcon(pmine);
			}
			else if(rep==-2){
			super.setIcon(flag);
			}
		
	}
	/**
	*@brief setTheme method
	*@details setter method that changes the value of the flag variable representing color scheme
	*@parameter method takes in an integer value that represents flag variable used to describe visual theme 
	*@date 12/10/2016.
	*/
	public void setPalette(int color) {
		this.palette=color;
	}
}