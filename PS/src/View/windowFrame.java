package View;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
/** windowFrame Class
 * Author: Vishesh Gulatee
 *
 * Class windowFrame inherits from GUI class JFrame
 * This class represents the window frame, which is the primary component of the user interface (View implementation component).
 * The class ensures that the visual scheme of the component is updated whilst also providing methods inherited from windowFrame
 *
 */
public class windowFrame extends JFrame {
	//Represents color scheme
	private Color colFade;
	//Imageicon used to set icon of the frame and application
	private ImageIcon pmine= new ImageIcon(getClass().getResource("/res/PineMine.png")); 
	
	/**
	*@brief Constructor for windowFrame
	*@details Constructor sets up the basic features exhibited by the window of the game application
	*@parameter method takes parameter name as a String value, it represents the title of the frame
	*@date 12/10/2016.
	*/
	public windowFrame(String name)
	
	{
			super.setTitle(name);
			super.setLayout(null);
			super.setExtendedState(JFrame.MAXIMIZED_BOTH);
			super.setResizable(false);
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			super.setLocationRelativeTo(null);
			super.setVisible(true);
			super.setIconImage(pmine.getImage());
	}
	/**
	*@brief setTheme method
	*@details Method ensures visual appearance of the frame is changed based on the integer value of palette
	*@parameter method takes parameter int palette, represents flag value that represents a visual theme
	*@date 12/10/2016.
	*/
	public void setTheme(int palette) {
		if(palette==0){
			colFade=null;
		}else if(palette==1){
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

		Container c=getContentPane();
		c.setBackground(colFade);
		setContentPane(c);	
	}
}
