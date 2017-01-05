package Model;
/** JSweepGameControl Class

 * Author: Varun Singh Rathore
 *
 * This class represents the setup of the components of the grid, ie. the cells and its contents. The class ensures distribution of the mines
 * within the game grid.
 */
import java.util.ArrayList;
import View.gridButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.GameManager;
import Controller.pauseController;
public class JSweepGameControl {
	
	public static int rowAndcol;
	public static int turn;
	public static Cell[] cells;
	private static JFrame frame;
	public static int minesCount;
	private static int flagged;
	

	/**
	*@param color 
	 * @brief Constructor for the Game Control.
	*@details Constructor accepts no parameters
	*@date 20/10/2016.
	*/
	public JSweepGameControl(int rAc, int color){
		rowAndcol=rAc;
		cells=new Cell[rowAndcol*rowAndcol];
		for (int i=0; i<(rowAndcol*rowAndcol); i++){
			cells[i]=new Cell(i,color);
		}
		setup();
		
	}
	/**
	*@brief Sets up the number of mines corresponding to grid size.
	*@Details Ensures that no row has more than 4,5, and 7 mines for
	*@Details Beginner, Intermediate, and Advanced difficulties, respectively.
	*@date 20/10/2016.
	*/
	protected static void setup(){
		int mines;
		flagged=0;
		turn=0;
		if(rowAndcol==9)
			minesCount=mines=10;
		
		else if(rowAndcol==16)
			minesCount=mines=40;
		else if(rowAndcol==24)
			minesCount=mines=99;
		else
			minesCount=mines=10;
		
		int round=0;
		GameManager.updatePineCounter(minesCount-flagged);
		ArrayList<Integer> rows = new ArrayList<>();
		
		while(mines>0 && rows.size()!=rowAndcol){
	
			int row=(int)(Math.random()*rowAndcol);
			while(rows.contains(row)){
				row=(int)(Math.random()*rowAndcol);
			}
			rows.add(row);
			if (mines>0 && rows.size()<=rowAndcol){
				int mineRow;
				do{
					if (round==0)
						mineRow=(int)(Math.random()*((int)(Math.sqrt(rowAndcol)+1)));
					else
						mineRow=1;
				}while(mineRow>mines);
				
				ArrayList<Integer> columns = new ArrayList<>();
				int column=(int)(Math.random()*rowAndcol);
				
				for (int k=0; k<mineRow; k++){
					while((cells[rowAndcol*row+column].getUnderneath()==-1)){
						column=(int)(Math.random()*rowAndcol);
					}
					columns.add(column);
					cells[rowAndcol*row+column].underneath(-1);
					mines--;
				}
			}
			if(mines>0 && rows.size()==rowAndcol){
				rows=new ArrayList<>();
				round++;
			}
		}
		
		for(int l=0; l<cells.length; l++){
			if (cells[l].getUnderneath()!=-1){
				int count=0;
				if (l%rowAndcol!=0 && cells[l-1].getUnderneath()==-1)
					count++;
				if (((l+1)%rowAndcol)!=0 && cells[l+1].getUnderneath()==-1)
					count++;
				if (l>(rowAndcol-1) && cells[l-rowAndcol].getUnderneath()==-1)
					count++;
				if (l<(rowAndcol*rowAndcol-rowAndcol) && cells[l+rowAndcol].getUnderneath()==-1)
					count++;
				if((l%rowAndcol!=0) && l>(rowAndcol-1) && cells[l-(rowAndcol+1)].getUnderneath()==-1)
					count++;
				if((l%rowAndcol!=0) && l<(rowAndcol*rowAndcol-rowAndcol) && cells[l+(rowAndcol-1)].getUnderneath()==-1)
					count++;
				if(((l+1)%rowAndcol!=0) && l>(rowAndcol-1) && cells[l-(rowAndcol-1)].getUnderneath()==-1)
					count++;
				if(((l+1)%rowAndcol!=0) && l<(rowAndcol*rowAndcol-rowAndcol) && cells[l+(rowAndcol+1)].getUnderneath()==-1)
					count++;
				cells[l].underneath(count);
			}
		}
		
	}
	
	/**
	*@brief If blank cell is clicked it opens up the surrounding empty and numbered cells
	*@Details Ensures the nearby non mine cells are opened
	*@date 25/11/2016.
	*/
	public static void bfs(int currentCell){
		boolean enable=cells[currentCell].bfsEnable;
		if (currentCell%rowAndcol!=0 && cells[currentCell-1].getUnderneath()==0){
			if(enable==true){
				cells[currentCell-1].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell-1);
			}
		}
		if (((currentCell+1)%rowAndcol)!=0 && cells[currentCell+1].getUnderneath()==0){
			if(enable==true){
				cells[currentCell+1].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell+1);
			}
		}
		if (currentCell>(rowAndcol-1) && cells[currentCell-rowAndcol].getUnderneath()==0){
			if(enable==true){
				cells[currentCell-rowAndcol].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell-rowAndcol);
			}
		}
		if (currentCell<(rowAndcol*rowAndcol-rowAndcol) && cells[currentCell+rowAndcol].getUnderneath()==0){
			if(enable==true){
				cells[currentCell+rowAndcol].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell+rowAndcol);
			}
		}
		if((currentCell%rowAndcol!=0) && currentCell>(rowAndcol-1) && cells[currentCell-(rowAndcol+1)].getUnderneath()==0){
			if(enable==true){
				cells[currentCell-(rowAndcol+1)].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell-(rowAndcol+1));
			}
		}
		if((currentCell%rowAndcol!=0) && currentCell<(rowAndcol*rowAndcol-rowAndcol) && cells[currentCell+(rowAndcol-1)].getUnderneath()==0){
			if(enable==true){
				cells[currentCell+(rowAndcol-1)].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell+(rowAndcol-1));
			}
		}
		if(((currentCell+1)%rowAndcol!=0) && currentCell>(rowAndcol-1) && cells[currentCell-(rowAndcol-1)].getUnderneath()==0){
			if(enable==true){
				cells[currentCell-(rowAndcol-1)].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell-(rowAndcol-1));
			}
		}
		if(((currentCell+1)%rowAndcol!=0) && currentCell<(rowAndcol*rowAndcol-rowAndcol) && cells[currentCell+(rowAndcol+1)].getUnderneath()==0){
			if(enable==true){
				cells[currentCell+(rowAndcol+1)].bfsCount++;
				cells[currentCell].bfsEnable=false;
				bfs(currentCell+(rowAndcol+1));
			}
		}
	}
	
	/**
	*@brief Starts bfs
	*@Details Initiates bfs and then resets variables
	*@date 25/11/2016.
	*/
	public static void startBfs(int currentCell){
		cells[currentCell].bfsCount=2;
		bfs(currentCell);
		for(int k=0; k<cells.length; k++){
			if (cells[k].bfsCount>=1){
				cells[k].setDig(true);
				cells[k].setOpen();
				++turn;
				cells[k].turnIncrement=false;
				if (k%rowAndcol!=0 && cells[k-1].getUnderneath()>0 && cells[k-1].turnIncrement==true){
					cells[k-1].setDig(true);
					cells[k-1].setOpen();
					++turn;
					cells[k-1].turnIncrement=false;
				}
				if (((k+1)%rowAndcol)!=0 && cells[k+1].getUnderneath()>0 && cells[k+1].turnIncrement==true){
					cells[k+1].setDig(true);
					cells[k+1].setOpen();
					++turn;
					cells[k+1].turnIncrement=false;
				}
				if (k>(rowAndcol-1) && cells[k-rowAndcol].getUnderneath()>0 && cells[k-rowAndcol].turnIncrement==true){
					cells[k-rowAndcol].setDig(true);
					cells[k-rowAndcol].setOpen();
					++turn;
					cells[k-rowAndcol].turnIncrement=false;
				}
				if (k<(rowAndcol*rowAndcol-rowAndcol) && cells[k+rowAndcol].getUnderneath()>0 && cells[k+rowAndcol].turnIncrement==true){
					cells[k+rowAndcol].setDig(true);
					cells[k+rowAndcol].setOpen();
					++turn;
					cells[k+rowAndcol].turnIncrement=false;
				}
				if((k%rowAndcol!=0) && k>(rowAndcol-1) && cells[k-(rowAndcol+1)].getUnderneath()>0 && cells[k-(rowAndcol+1)].turnIncrement==true){
					cells[k-(rowAndcol+1)].setDig(true);
					cells[k-(rowAndcol+1)].setOpen();
					++turn;
					cells[k-(rowAndcol+1)].turnIncrement=false;
				}
				if((k%rowAndcol!=0) && k<(rowAndcol*rowAndcol-rowAndcol) && cells[k+(rowAndcol-1)].getUnderneath()>0 && cells[k+(rowAndcol-1)].turnIncrement==true ){
					cells[k+(rowAndcol-1)].setDig(true);
					cells[k+(rowAndcol-1)].setOpen();
					++turn;
					cells[k+(rowAndcol-1)].turnIncrement=false;
				}
				if(((k+1)%rowAndcol!=0) && k>(rowAndcol-1) && cells[k-(rowAndcol-1)].getUnderneath()>0 && cells[k-(rowAndcol-1)].turnIncrement==true){
					cells[k-(rowAndcol-1)].setDig(true);
					cells[k-(rowAndcol-1)].setOpen();
					++turn;
					cells[k-(rowAndcol-1)].turnIncrement=false;
					
				}
				if(((k+1)%rowAndcol!=0) && k<(rowAndcol*rowAndcol-rowAndcol) && cells[k+(rowAndcol+1)].getUnderneath()>0 && cells[k+(rowAndcol+1)].turnIncrement==true ){
					cells[k+(rowAndcol+1)].setDig(true);
					cells[k+(rowAndcol+1)].setOpen();
					++turn;
					cells[k+(rowAndcol+1)].turnIncrement=false;

				}
			}
		}
		for(int m=0; m<cells.length; m++){
			cells[m].bfsCount=0;
			cells[m].bfsEnable=true;
		}
		--turn;
		//reset all count to 0 and boolean
	}
	/**
	*@brief Generates the grid based on the dimensions.
	*@param i The number of rows/columns (square grid).
	*@return The grid representation of the board.
	*@date 16/10/2016
	*/
	public static gridButton plotGenerater(int i) {
		return cells[i].getPlot();
	}
	/**
	*@brief 
	*@param i The cell which is to be clicked upon.
	*@return The string value of the contents of the cell.
	*@date 16/10/2016
	*/
	public static String unEarth(int i) {
		
		return Integer.toString(cells[i].getUnderneath());
	}

	private static void checkMine(int i) {
		// TODO Auto-generated method stub
		if(cells[i].getUnderneath()==-1){
			GameManager.reset.funeral();
			GameManager.timer.stop();
			//GameManager.currentTime = 0;
			GameManager.timeLabel.setText("<html>Time<br><center>" + GameManager.currentTime + "</center></html>");
			JOptionPane.showMessageDialog(frame,"Game Over");
			revealAll();
			
		}
	}
	/**
	*@brief Reveal what is under all tiles
	*@details Instantaneously removes all of the tiles.
	*@date 17/10/2016
	*/
	public static void revealAll() {
		// TODO Auto-generated method stub
		for (int i=0; i<(rowAndcol*rowAndcol); i++){
			cells[i].setOpen();
		}
	}
	/**
	*@brief Cover the revealed cells of the grid.
	*@details Instantaneously Places back all of the removed tiles.
	*@date 17/10/2016
	*/
	public static void coverAll () {
		// TODO Auto-generated method stub
		for (int i=0; i<(rowAndcol*rowAndcol); i++){
			if(cells[i].getDig()==false)
			cells[i].setClosed();
		}
	}
	/**
	*@brief Reset the game
	*@details Sets up new grid, redistributing PineMines in the grid
	*@date 16/10/2016
	*/
	public static void reset() {

		for (int i=0; i<(rowAndcol*rowAndcol); i++){
			cells[i].resetCell();
		}
		setup();
		GameManager.currentTime = 0;
		GameManager.timeLabel.setText("<html>Time<br><center>" + GameManager.currentTime + "</center></html>");
		GameManager.timer.stop();
		GameManager.pause.setPause();
		pauseController.isPaused = false;
		turn = 0;

	}
	/**
	*@brief Reveals contents of a cell
	*@param button object call representing that particular cell
	*/
	public static void openCell(gridButton button) {
		
		if((GameManager.currentTime == 0) && (turn >= 0)){
			GameManager.timer.start();
		}
		
		if(pauseController.isPaused == false) {
			for(int i=0; i<(rowAndcol*rowAndcol);i++){
				if(button==cells[i].getButton() && cells[i].getMark()==false){
					cells[i].setDig(true);
					cells[i].setOpen();
					cells[i].turnIncrement=false;
					checkMine(i);
					if(cells[i].getUnderneath()==0){
						startBfs(i);
					}
					++turn;
					checkWin();
					
				}	
			}
		}
	}
	/**
	*@brief markCell marks the cells that user wants with flags or unmark cells with flags
	*@parameter button object that represent cell object user registers a right click on
	*@date 16/10/2016
	*/
	public static void markCell(gridButton button) {
		
		if((GameManager.currentTime == 0) && (flagged >= 0)){
			GameManager.timer.start();
		}

		if(pauseController.isPaused == false) {
			for(int i=0; i<(rowAndcol*rowAndcol);i++){
				if(button==cells[i].getButton()){
					cells[i].flagSpace();
					if(cells[i].getMark()==true){
					++flagged;
					}
					else{
						--flagged;
					}
				}
			}
		}
			GameManager.updatePineCounter(minesCount-flagged);
			checkWin();
	}
	/**
	*@brief checkWin(), checks to see if win objects have been cleared every turn
	*@details Once the requirements for a win conclusion are met, game ends
	*@date 16/10/2016
	*/
	public static void checkWin(){
		if(turn==rowAndcol*rowAndcol-minesCount && minesCount==flagged){
			GameManager.reset.celebration();
			GameManager.timer.stop();
			JOptionPane.showMessageDialog(frame,"Game Won");
			revealAll();
		}
	}
	/**
	*@brief setColor facilitates change of cell color 
	*@date 16/10/2016
	*/
	public void setColor(int color) {
		for(int i=0;i<rowAndcol*rowAndcol;i++){
			cells[i].changePlotColor(color);
		}
	}
}