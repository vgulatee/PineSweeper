package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import View.gridButton;
import Model.JSweepGameControl;

/** mouseControl Class
 * Author: Vishesh Gulatee and Prince Sandhu
 * mouseControl is a class of the Controller component, responsible for registering and managing user input in the form of mouse clicks and actions.
 * The class implements interfaces MouseListener and ActionListener. MouseListner is a Java Library that provides methods to register mouse inputs,
 * while ActionListener is a Java Library that provides methods to register actions made to Swing objects such as buttons
 *
 */
public class mouseControl implements MouseListener, ActionListener {
	
	/**
	*@brief Method mouseClicked
	*@details Method that accepts one parameter which represents the mouse event corresponding to action using hardware mouse implemented by user
	*The click registered performs various actions based on the state of the application and type of click
	*@date 13/10/2016.
	*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//If mouse event e is a right click, user is marking a cell
		//right click represented as BUTTON 3
		if(e.getButton()==MouseEvent.BUTTON3){
			try{
				gridButton button=(gridButton) e.getSource(); //Creates temporary object from source
				JSweepGameControl.markCell(button);
			}	
		
			catch(ClassCastException ex){	
			}
		}
	}
				 

	/**
	*@brief Method mouseEntered
	*@details method not used
	*@date 13/10/2016.
	*/
	@Override
	public void mouseEntered(MouseEvent e) {		
	}
	
	/**
	*@brief Method mouseExited
	*@details method not used
	*@date 13/10/2016.
	*/
	@Override
	public void mouseExited(MouseEvent e) {		
	}
	
	/**
	*@brief Method mousePressed
	*@details Every time mouse is pressed the smile button changes the default picture bound to it. This is a good indication to the user that their input click has been registered
	*@date 13/10/2016.
	*/
	@Override
	public void mousePressed(MouseEvent e) {
		GameManager.reset.changeHappy();
	}
	
	/**
	*@brief Method mouseReleased
	*@details The smile button's icon is changed back to default icon once mouse is released
	*@date 13/10/2016.
	*/
	@Override
	public void mouseReleased(MouseEvent e) {
		GameManager.reset.changeBack();
	}
	
	/**
	*@brief Method actionPerformed
	*@details Method registers which button has been clicked by the user and executes a certain action, influencing state variable of application described
	* in the Model classes 
	*@date 14/10/2016.
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
     if(event.getSource()==GameManager.reset ||event.getSource()==GameManager.newgame){ //Reset(smile button)
    	 //Instantiates a new game, regenerating the grid of cells and its contents
    	 GameManager.reset.setContinue();
    	 JSweepGameControl.reset();
     }
     else if(event.getSource()==GameManager.theme){
    	 //Starts up theme menu
    	 GameManager.themeManager();
     }
     else if(event.getSource()==GameManager.pref){
    	 //Starts up difficulty menu
    	 GameManager.prefManager();
     }     else if(event.getSource()==GameManager.instruction){
    	 //Starts up instruction menu
    	 GameManager.readInstructions();
     }
     else if(event.getSource()==GameManager.easy){
    	 //Changes game difficulty to easy
    	 GameManager.changeSize(9);
     }
     else if(event.getSource()==GameManager.medium){
    	 //Changes game difficulty to medium
    	 GameManager.changeSize(16);
     }
     else if(event.getSource()==GameManager.hard){
    	 //Changes game difficulty hard
    	 GameManager.changeSize(24);
     }
     else if(event.getSource()==GameManager.metal){
    	//Changes color scheme to default
    	 GameManager.changeTheme(0);
     }
     else if(event.getSource()==GameManager.arctic){
    	//Changes color scheme to Glacial Blue
    	 GameManager.changeTheme(1);
     }
     else if(event.getSource()==GameManager.desert){
    	//Changes color scheme to Desert Gold
    	 GameManager.changeTheme(2);
     }
     else if(event.getSource()==GameManager.ocean){
    	//Changes color scheme to Tidal Blue
    	 GameManager.changeTheme(3);
     }
     else if(event.getSource()==GameManager.forest){
    	//Changes color scheme to Woodland Green
    	 GameManager.changeTheme(4);
     }
     else if(event.getSource()==GameManager.cave){
    	//Changes color scheme to Slate Gray
    	 GameManager.changeTheme(5);
     }
     else if(event.getSource()==GameManager.rose){
    	//Changes color scheme to Rose Garden Pink
    	 GameManager.changeTheme(6);
     }
     
     else{ //Plot buttons (comp of Cell obj)
    	 //"Unearths" or reveals content hidden by cell
    	 gridButton button= (gridButton)event.getSource();
    	 JSweepGameControl.openCell(button);
     	 }
	}
	
}