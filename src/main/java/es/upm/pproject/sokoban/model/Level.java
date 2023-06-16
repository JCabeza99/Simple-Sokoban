package es.upm.pproject.sokoban.model;

import java.awt.event.KeyEvent;

public class Level implements LevelInterface {
	
	private int[][] map;
	
	
	// ints meanings:
	//  0 = Empty Square
	//  1 = Goal without boxes
	// 	2 = Wall
	//  3 = Box
	//  4 = Box in Goal
	
	
	
	private int nGoals;
	private int boxesInGoal;
	private PlayerInterface player;
	private int failureStatus;
	private String name;
	
	enum position {
		X,
		Y
	}
	
	public Level(int[][] map, String name , int nGoals, int boxesInGoal, PlayerInterface player, int failureStatus){
		this.map = map;
		this.player = player;
		this.nGoals = nGoals;
		this.boxesInGoal = boxesInGoal;
		this.failureStatus=failureStatus;
		this.name = name;
  	}
	
	
	public int getnGoals() {
		return nGoals;
	}

	public String getName() {
		return name;
    }


	public void setnGoals(int nGoals) {
		this.nGoals = nGoals;
	}


	public int getBoxesInGoal() {
		return boxesInGoal;
	}

	public void setBoxesInGoal(int boxesInGoal) {
		this.boxesInGoal = boxesInGoal;
	}

	// POST: The int[] will be diferent depending on the initial conditions of the map and player:
	// int[0] = -1  Means that it is not possible to move the player in that direction
	// int[0] = 0 Means that only the player has moved
	// int[0] = 1 Means that along with the player, a box have been moved
	// In this last scenario the method saves the new coordinates of the box in int[1] and int[2]
	public int[] move(int dir){
		
		int[] res = new int[3];
		int[] newPosPlayer = newPosition(player.getxPos(), player.getyPos(), dir);
		
		// Checks the current situation to decide how the movement will be done or if even is going to be done
		res[0] = checkPosition(newPosPlayer[position.X.ordinal()], newPosPlayer[position.Y.ordinal()]);
		switch(res[0]) {
		
		case 0: // There is no wall nor box in that direction so...
			player.move(newPosPlayer[position.X.ordinal()], newPosPlayer[position.Y.ordinal()]); // Moves the player
			player.getScore().incrementLevelScore();       // And increases the score of the current level
			break;
		
		case 1: // There is a box in that direction, so...
			
			int[] newPosBox = newPosition(newPosPlayer[position.X.ordinal()], newPosPlayer[position.Y.ordinal()], dir);
			
			if(checkPosition(newPosBox[position.X.ordinal()], newPosBox[position.Y.ordinal()]) == 0) { // A box only can be moved if there is nothing in the next position
				
				
				
				player.move(newPosPlayer[position.X.ordinal()], newPosPlayer[position.Y.ordinal()]); // Moves the player
				player.getScore().incrementLevelScore();       // And increases the score of the current level
				
				// Moves the box, doing the necessary changes in the map 
				// The method is called with newPosPlayer as its first argument because the player is now in the box old position
				moveBox(newPosPlayer, newPosBox); 
				
				res[1] = newPosBox[position.X.ordinal()];
				res[2] = newPosBox[position.Y.ordinal()];
				
			}else {
				res[0]=-1;
			}
			break;
		default : // -1 means that there is a wall so the move can not be done
		}
		return res;
	}
	
	
	// This method is used to move the player during an undo()
	// Despite the name of the method, the int "dir" points to the correct position.
	public void reverseMovePlayer(int dir) {
		int[] newPosPlayer = newPosition(player.getxPos(), player.getyPos(), dir);
		player.move(newPosPlayer[position.X.ordinal()], newPosPlayer[position.Y.ordinal()]); // Moves the player
		player.getScore().decrementLevelScore();
	}
	
	
	// This method is used to move the player during an undo()
	// Despite the name of the method, the int "dir" points to the correct position.
	public void reverseMoveBox(int dir, int[] boxPreUndoPosition){
		
		int[] newPosBox = newPosition(boxPreUndoPosition[position.X.ordinal()], boxPreUndoPosition[position.Y.ordinal()], dir);
		
		moveBox(boxPreUndoPosition, newPosBox);
		
	}
	
	private int[] newPosition(int posX, int posY, int dir) {
		
		switch(dir) {
		case KeyEvent.VK_UP:
			return new int []{posX, posY-1};
		case KeyEvent.VK_DOWN:
			return new int []{posX, posY+1};
		case KeyEvent.VK_RIGHT:
			return new int []{posX+1, posY};
		case KeyEvent.VK_LEFT:
			return new int []{posX-1, posY};
		default :
			return new int []{posX, posY};
		}
	}
	
	private int checkPosition(int posX, int posY) {
		
		int mapItem = map[posY][posX]; //It checks what kind of item is in that position
		if(mapItem<2) { // If the item is an empty position or a goal position (without boxes)
			return 0;   // The action "move" can always be performed
		}else if(mapItem>2) { //If the item is a box
			return 1;  //The action "move" could happen or not depending on the context
		}else {    // If the item is a wall
			return -1; // the action "move" can not be performed
		}
		
	}
	
	
	// Since a box can be placed in a Goal or not, this method not only moves the box
	// but checks if there is a goal position in their new positions to change the map correctly
	private void moveBox(int[] oldPosBox, int[] newPosBox) {
		
		if(map[oldPosBox[position.Y.ordinal()]][oldPosBox[position.X.ordinal()]] == 3) {// Checks if there was a goal position where the box was before the move
			map[oldPosBox[position.Y.ordinal()]][oldPosBox[position.X.ordinal()]] = 0;
		}else {
			map[oldPosBox[position.Y.ordinal()]][oldPosBox[position.X.ordinal()]] = 1;
			boxesInGoal--;
		}
		
		if(map[newPosBox[position.Y.ordinal()]][newPosBox[position.X.ordinal()]] == 0) {// Checks if there is a goal position where the box currently is
			map[newPosBox[position.Y.ordinal()]][newPosBox[position.X.ordinal()]] = 3;
		}else {
			map[newPosBox[position.Y.ordinal()]][newPosBox[position.X.ordinal()]] = 4;
			boxesInGoal++;
		}
	}
	
	public PlayerInterface getPlayer() {
		return player;
	}
	
	public void setPlayer(PlayerInterface player) {
		this.player = player;
	}
	
	public int[][] getMap() {
		return map;
	}
	public int getFailureStatus() {
	return failureStatus;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
	

	
}
  	