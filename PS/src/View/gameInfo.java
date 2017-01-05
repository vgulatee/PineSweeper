package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
/** gameInfo Class
 * Author: Vishesh Gulatee
 *
 * Class gameInfo inherits from GUI class JTextArea, a GUI component that writes and views text.
 * This class represents text panels used in the View implementation. It ensures that the visual scheme of the component is updated whilst also providing methods inherited from JtextArea

 *
 */
public class gameInfo extends JTextArea {
	//Color scheme of the GUI component
	private Color colFade;
	/**
	*@brief Constructor for gameInfo
	*@details Constructor sets up the basic features of the Text Area representing PineMines counter
	*@parameter method takes integer palette
	*@date 12/10/2016.
	*/
	
	public gameInfo(int palette) {
		super.setEditable(false);
		setTheme(palette);
		super.setFont(new Font("Arial", Font.BOLD, 12));
		
	}
	/**
	*@brief setTheme method
	*@details Method changes the color scheme of the GUI component based on value of palette
	*@parameter method takes in an integer value that represents flag variable used to describe visual theme 
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
		super.setBackground(colFade);
		
	}
}