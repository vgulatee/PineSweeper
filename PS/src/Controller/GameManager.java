package Controller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Model.JSweepGameControl;
import View.boardPanel;
import View.gameInfo;
import View.pauseButton;
import View.smileButton;
import View.windowFrame;
/** GameManager Class
 * Author: Vishesh Gulatee, Prince Sandhu
 *
 * This class represents the control and setup class for all components of MVC framework and provides the program with static methods to 
 * facilitate communication between component classes of MVC.
 * Class includes the main class that executes the application
 *
 */

public class GameManager {
	private static windowFrame window; //window represents game window and is an object of windowFrame, which is a custom designed class that inherits from JFrame
	
	//Describing objects of type JMenuBar and JMenu. These components comprise of the function menu bar on top of the game
	private JMenuBar menuBar;
	private JMenu menuA;
	private JMenu menuB;
	private JMenu menuC;
	
	//JPopMenu objects describe pop up extension for each Menu object in menuBar
	//These extension provide visual classification of multiple menu items described below
	private JPopupMenu setA;
	private JPopupMenu setB;
	private JPopupMenu setC;
	
	//MenuItems are components of the function menu bar, where each item is responsible for enforcing specific actions
	private static JPanel panel;
	public static JMenuItem pref; //Game difficulty
	public static JMenuItem theme; //Visual theme
	public static JMenuItem newgame; // New game
	public static JMenuItem instruction; //Game instructions
	
	
	//Radio button objects described, they are to be used to provide users with a function to enforce changes regarding visual features in the game
	private static ButtonGroup radioGroup; 
	//Game Difficulty options
	public static JRadioButton easy= new JRadioButton("Easy");
	public static JRadioButton medium= new JRadioButton("Medium");
	public static JRadioButton hard= new JRadioButton("Hard");
	//Visual Theme options
	public static JRadioButton metal =new JRadioButton("Default");
	public static JRadioButton arctic= new JRadioButton("Glacial Blue");
	public static JRadioButton desert= new JRadioButton("Desert Gold");
	public static JRadioButton ocean =new JRadioButton("Tidal Blue");
	public static JRadioButton forest =new JRadioButton("Woodland Green");
	public static JRadioButton cave =new JRadioButton("Slate Gray");
	public static JRadioButton rose =new JRadioButton("Rose Garden Pink");
	
	//Objects of classes that extend Java's GUI specific classes, describe View components 
	private static boardPanel grid; //PineMine grid
	public static smileButton reset; //New Game button and Game State indicator
	
	//mouseControl object represents object that registers and maintains user input (Controller component)
	private mouseControl mc;
	
	//JSweepGameControl object repr Model Component (generates grid and manages changes made to logical component)
	private static JSweepGameControl JC;
	
	//Repr window Dimensions
	private static int length;
	private static int breadth;
	
	//Repr system type
	private static String systemName;
	
	//Repr visual theme (as flag variable)
	public static int Theme;
	
	//Addional featues of GUI, as part of the game. 
	//Pine Mine counter that users use to trace progress in terms of mine 
	//Timer display that users use to record time of game play
	private static gameInfo pineText;
	public static JLabel timeLabel;
	public static int currentTime;
	public static Timer timer;
	public static pauseButton pause;
	
	
	/**
	*@brief Constructor for GameManager Class 
	*@details Non-parameterized constructor that instantiates Swing objects representing components of the GUI and sets up components of MVC framework of the game application. Class also maintains internal property based on MVC interaction
	*@date 12/10/2016.
	*/
	
	public GameManager(){ //Controller class that provides a starting state for the game
		
		//Instantiating JFrame object, which servers as the primary window for the application's graphical user interface
		Theme=0;
		window = new windowFrame("PineSweeper");
		window.setTheme(Theme);
		
		pineText= new gameInfo(Theme);
	
		//JC object represents the virtual board of the game. JSweepeGameClass is a class belonging to the Model module. Constructor calls sets up model of board for easy difficulty (default)
		JC= new JSweepGameControl(9,Theme);
		
		//Menu created
		menuBar= new JMenuBar();
		menuA= new JMenu("File");
		menuB= new JMenu("Settings");
		menuC= new JMenu("Help");
		
		
		
		
		//Instantiating objects of components of function menu bar (primary user preference management tool)
		setA= new JPopupMenu();
		setB= new JPopupMenu();
		setC= new JPopupMenu();
		setC.add(menuC);
		setB.add(menuB);
		setA.add(menuA);
		
		//Managing menu times that enforce management of changes and register user input to display a pane to this end
		newgame=new JMenuItem("New Game");
		newgame.setVisible(true);
		menuA.add(newgame);	
		
		pref=new JMenuItem("Difficulty Preferences");
		pref.setVisible(true);
		menuB.add(pref);
		
		theme= new JMenuItem("Visual Themes");
		theme.setVisible(true);
		menuB.add(theme);		
		
		instruction= new JMenuItem("Game Instructions");
		instruction.setVisible(true);
		menuC.add(instruction);		
		/*
		 * TIMER BLOCK
		 * Maintains information described by timer clock and manages timer functionality (Balances timer value and visual repr)
		 */
		
		timeLabel = new JLabel("");
		timeLabel.setText("<html>Time<br><center>" + currentTime + "</center></html>");
		
		ActionListener taskPerformer = new ActionListener() { //Incremental count (by seconds)
			public void actionPerformed(ActionEvent evt) {
				currentTime++;
				timeLabel.setText("<html>Time<br><center>" + currentTime + "</center></html>");
			}
		};
		
		timer = new Timer(1000, taskPerformer);		
		/*
		 * END TIMER BLOCK
		 */
		
		//Instantiate pause button (Provides user with control on time in game)
		pause = new pauseButton(Theme); 
		pause.addActionListener(new pauseController());
		
		//boardPanel object grid, represents the graphical visual representation of the virtual board JC
		grid= new boardPanel(9);
		grid.setLocation(0, 70);
	
		//mc object of mouseControl() manages user input to manipulate state variables of the model
		mc= new mouseControl();
	
		//Adding Controller component object to View components (Describes View-Controller relationsip)
		pref.addActionListener(mc);
		instruction.addActionListener(mc);
		newgame.addActionListener(mc);
		theme.addActionListener(mc);
		easy.addActionListener(mc);
		medium.addActionListener(mc);
		hard.addActionListener(mc);
		metal.addActionListener(mc);
		arctic.addActionListener(mc);
		forest.addActionListener(mc);
		desert.addActionListener(mc);
		ocean.addActionListener(mc);
		cave.addActionListener(mc);
		rose.addActionListener(mc);
		
		//Button used in the gaming application to reset game and judge game state (smile picture)
		reset= new smileButton(mc,Theme);
		
	
		//Adding/binding visual components to window (setting location and size relative to layout)
	

		window.pack();
		
		systemName=(System.getProperty("os.name"));
		breadth=248; length=362;
		checkSystem();
		window.setSize(breadth, length);
		timeLabel.setBounds(breadth-breadth/31-35 ,20,35,35);
		reset.setLocation(breadth/2-20, 20);
		pineText.setBounds(breadth/31, 20 ,90, 35);
		menuBar.add(menuA);
		menuBar.add(menuB);
		menuBar.add(menuC);
		window.add(reset);
		window.add(timeLabel);
		window.add(grid);
		window.add(pineText);
		window.setJMenuBar(menuBar);
	
		//Setting visibility to true
		
		menuBar.setVisible(true);
		
		
		window.add(timeLabel);
		pause.setMargin(new Insets(0, 0, 0, 0));
		pause.setBounds(breadth-breadth/31-65, 30, 20, 20);
		window.add(pause, BorderLayout.NORTH);
		
		}
	
	/**
	*@brief Static method checkSystem, determines the operating system being used to execute this program.
	*@details Method takes no parameters, it modifies the length and breadth of the window frame to accomadate specific systems
	*@date 19/10/2016.
	*/
	
	private static void checkSystem() {
	
		if(systemName.toLowerCase().contains("mac")){
			breadth=breadth-5; length=length-12;
			
		}
	
	}

	/**
	*@brief static method changeSize enforces user's choice of game difficulty
	*@details Method that accepts one parameter, an integer that represents the number of rows and columns of the grid that needs to be setup.
	*Based on user choice method will generate a board of differing sizes. Harder the difficulty, larger will be the size of the window and grid.
	*@date 20/10/2016.
	*/
	
	public static void changeSize(int i){
			
			if(i==9){ //breadth and length for easy difficulty (9x9 grid)
				breadth=248; length=362;
			} 
			else if(i==16){ //breadth and length for medium difficulty (16x16 grid)
				breadth=437; length= 544;
				pineText.setBounds(breadth/31, 20 ,90, 35);
			}
			else if(i==24){ //breadth and length for hard difficulty (24x24 grid)
				breadth=653; length= 752;
				pineText.setBounds(breadth/31, 20 ,90, 35);
			}
			checkSystem(); //method call to modify difference across different operating systems
			
			//re-instantiate time to 0
			currentTime = 0;
			timeLabel.setText("<html>Time<br><center>" + GameManager.currentTime + "</center></html>");
			//stops timer 
			timer.stop();
			//pauses timer sets the buttons state to ensure game can be paused
			pause.setPause();
			pauseController.isPaused = false;
			
			//Re-instantiating board and its components
			JC=new JSweepGameControl(i,Theme);
			window.remove(grid);
			grid= new boardPanel(i);
			grid.setLocation(0, 70);
			window.add(grid);
			//Changing bounds and location, relative to window frame size.
			window.setSize(breadth, length);
			pause.setBounds(breadth-breadth/31-65, 30, 20, 20);
			timeLabel.setBounds(breadth-breadth/31-35 ,20,35,35);
			reset.setLocation(breadth/2-18, 20);
		
			
	}
	

	/**
	*@brief static method changeTheme enforces user's choice of visual theme
	*@details Method that accepts one parameter, an integer that represents a flag integer, which itself is a representation of a particular visual theme.
	*Based on user choice method will generate a board of with a different color scheme, specific to the options provided.
	*@date 3/11/2016.
	*/
	public static void changeTheme(int i){
		Theme=i;
		//Changing theme value for all the specified GUI objects
		JC.setColor(Theme);
		window.setTheme(Theme);
		reset.setPalette(Theme);
		pineText.setTheme(Theme);
	}

	/**
	*@brief static method prefManager provides user with a menu to manage game difficulty preference
	*@details Method provides user with a menu to interact with and allows the user to input his/her choice
	*@date 20/10/2016.
	*/
	public static void prefManager(){
		
		radioGroup= new ButtonGroup();
		radioGroup.add(easy);
		radioGroup.add(medium);
		radioGroup.add(hard);
		//Button group added to option pane
		panel=new JPanel();
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);
		  JOptionPane.showMessageDialog(null, panel, "Chose a difficulty:", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	*@brief static method themeManager provides user with a menu to manage visual themes
	*@details Method provides user with a menu to interact with and allows the user to input his/her choice
	*@date 03/11/2016.
	*/
	public static void themeManager() {
		radioGroup= new ButtonGroup();
		radioGroup.add(metal);
		radioGroup.add(ocean);
		radioGroup.add(desert);
		radioGroup.add(arctic);
		radioGroup.add(forest);
		radioGroup.add(cave);
		radioGroup.add(rose);
		
		panel= new JPanel();
		panel.add(metal);
		panel.add(ocean);
		panel.add(desert);
		panel.add(arctic);
		panel.add(forest);
		panel.add(cave);
		panel.add(rose);
		  JOptionPane.showMessageDialog(null, panel, "Chose a Theme: ", JOptionPane.PLAIN_MESSAGE);
		
	}

	/**
	*@brief updatePineCounter, updates text displaying PineMine counter
	*@details Method that accepts one parameter, an integer that represents the number of PineMines potentially unmarked with a flag.
	*@date 15/11/2016.
	*/
	public static void updatePineCounter(int flagged) {
		pineText.setText("Pine\nMines: "+flagged);
		
		}

	/**
	*@brief static method readInstruction, provides users with a window to review objectives and instructions for playing the game
	*@details Method sets up a menu extension for users to read.
	*@date 25/11/2016.
	*/
	public static void readInstructions() {
	
		JTextArea msg = new JTextArea("HOW TO PLAY \n\n"
				+ "Clear the grid, by clicking on the cells in the grid that do not contain a hidden PineMine and Flag all the hidden PineMines. "
				+ "If you click on a PineMine, the game is over. "
				+ "\n\n"
				+ "Numbers represent the number of PineMines bordering that cell. "
				+ "Mark potential PineMines with a flag by right-clicking on the cell. "
				+ "\n\n"
				+ "Start a new game by clicking on the smile button. "
				+ "\n\n"
				+ "Pause the game by clicking on the pause button. ");
		msg.setFont(new Font("Arial", Font.BOLD, 15));
		msg.setRows(15);
		msg.setColumns(25);
		msg.setEditable(false);
		msg.setBackground(null);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(msg);
		JOptionPane.showMessageDialog(null, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	*@brief main method to be called by the interpreter to compile and execute Java project
	*@details main method that accepts one parameter, a string array. 
	*Method calls instantiates an object of class GameManager via a constructor call
	*@date 12/10/2016.
	*/
public static void main(String[] args){
	
		  try {
	            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Metal".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                }
	            }
	        } catch (Exception e) {
	            try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (Exception e2) {
	            }
	        }
	
	
	GameManager GM= new GameManager();
}


}