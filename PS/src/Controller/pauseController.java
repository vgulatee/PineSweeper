package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.JSweepGameControl;
/** pauseControl Class
 * Author: Prince Sandhu
 * pauseControl is a class of the Controller component, responsible for registering and managing user input in the form of mouse clicks and actions, specific however to pause button
 * The class implements interfaces  ActionListener. ActionListener is a Java Library that provides methods to register actions made to Swing objects such as buttons
 *
 */
public class pauseController implements ActionListener{

	public static boolean isPaused = false;
	/**
	*@brief Method actionPerformed
	*@details Method registers when pause button has been clicked by the user and executes a certain action, influencing state of the timer 
	*@date 14/10/2016.
	*/
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == GameManager.pause) {
    		if(isPaused == false) {
    			//Pauses game and sets the timer to stop and wait
    			//Sets icon of button to resume
    			if(JSweepGameControl.turn >= 1) {
    				GameManager.timer.stop();
    				GameManager.pause.setResume();
    				isPaused = true;
    			}
    		}
    		
    		else{
    			//Resumes game if paused (determined by conditional) and sets the timer to resume from stationary point
    			//Sets icon of button to pause
    			isPaused = false;
    			if(JSweepGameControl.turn >= 1) {
        			GameManager.timer.start();
        			GameManager.pause.setPause();
    			}
    		}
    	}
    }
}