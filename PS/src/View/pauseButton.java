package View;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.KeyStroke;
/** pauseButton Class
 * Author: Prince Sandhu
 *
 * Class pauseButton inherits from GUI class JButton. 
 * This class represents the single pause button linked to the programmers timer implementation.
 * The class ensures that the visual scheme of the component is updated whilst also providing methods inherited from JButton
 *
 */
public class pauseButton extends JButton {
	//ImageIcons representing action available to the user for specific points in the game
	private ImageIcon pause=new ImageIcon(getClass().getResource("/res/pause.png"));
	private  ImageIcon resume=new ImageIcon(getClass().getResource("/res/resume.png"));
	//Color scheme
	private Color colFade;
	private int palette;
	/**
	*@brief Constructor for pauseButton
	*@details Constructor sets up the basic features exhibited by the button model for linked to applications timer
	*@parameter takes int value as parameter which represents the flag value representing one of the 7 color themes.
	*@date 12/10/2016.
	*/
	public pauseButton(int theme){
		this.setIcon(pause);
		this.setVisible(true);
		this.updateUI();
		this.palette=theme;
		 getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none"); 
	        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none"); 
	}
	/**
	*@brief setResume method
	*@details Mutator Method changes the icon of the button to represent the availability of the resume operation
	*@date 12/10/2016.
	*/
	public void setResume(){
		this.setIcon(resume);
	}
	/**
	*@brief setPause method
	*@details Mutator Method changes the icon of the button to represent the availability of the pause operation
	*@date 12/10/2016.
	*/
	public void setPause(){
		this.setIcon(pause);
	}
	/**
	*@brief paintComponent method (inherited)
	*@details Method changes the color scheme of the GUI component based on value of palette
	*@parameter method takes in a graphics object, used to paint color scheme
	*@date 12/10/2016.
	*/
	protected void paintComponent(Graphics g) {
	
			this.setContentAreaFilled(false);
			if(palette==0){
				colFade=null;
			}
			else{
				
			if (palette==1){
				colFade= new Color(240, 248, 255);
			}else if(palette==2){
				colFade=new Color(245, 222, 179);
			}else if(palette==3){
				colFade= new Color(70, 130, 180);
			}else if(palette==4){
				colFade= new Color(144, 238, 144);
			}
			else if (palette==5){
				 colFade= new Color(220, 220, 220);
			}
			else if (palette==6){
				colFade= new Color(255, 192, 203);
			}
			
		 Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp;
        gp= new GradientPaint(0, 0, colFade, 0, getHeight(),colFade);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
			}
			super.paintComponent(g);   
   }
}
