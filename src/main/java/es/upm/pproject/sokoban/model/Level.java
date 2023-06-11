package es.upm.pproject.sokoban.model;

import java.awt.event.KeyEvent;

public class Level implements LevelInterface {
	
	private int map[][];
	
	
	// ints meanings:
	//  0 = Empty Square
	//  1 = Goal without boxes
	// 	2 = Wall
	//  3 = Box
	//  4 = Box in Goal
	
	
	
	private int nGoals;
	private int boxesInGoal;
	private PlayerInterface player;
	private String name;
	private int failureStatus;
	
	public Level(String name, int[][] map, int nGoals, int boxesInGoal, PlayerInterface player, int failureStatus){
		this.name=name;
		this.map = map;
		this.player = player;
		this.nGoals = nGoals;
		this.boxesInGoal = boxesInGoal;
		this.failureStatus=failureStatus;
  	}
	
	
	public int getnGoals() {
		return nGoals;
	}


	public void setnGoals(int nGoals) {
		this.nGoals = nGoals;
	}


	public int getBoxesInGoal() {
		return boxesInGoal;
	}

	public int getfailureStatus() {
		return failureStatus;
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
		switch(res[0] = checkPosition(newPosPlayer[0], newPosPlayer[1])) {
		
		case 0: // There is no wall nor box in that direction so...
			player.move(newPosPlayer[0], newPosPlayer[1]); // Moves the player
			player.getScore().incrementLevelScore();       // And increases the score of the current level
			break;
		
		case 1: // There is a box in that direction, so...
			
			int[] newPosBox = newPosition(newPosPlayer[0], newPosPlayer[1], dir);
			
			if(checkPosition(newPosBox[0], newPosBox[1]) == 0) { // A box only can be moved if there is nothing in the next position
				
				
				
				player.move(newPosPlayer[0], newPosPlayer[1]); // Moves the player
				player.getScore().incrementLevelScore();       // And increases the score of the current level
				
				// Moves the box, doing the necessary changes in the map 
				// The method is called with newPosPlayer as its first argument because the player is now in the box old position
				moveBox(newPosPlayer, newPosBox); 
				
				res[1] = newPosBox[0];
				res[2] = newPosBox[1];
				
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
		player.move(newPosPlayer[0], newPosPlayer[1]); // Moves the player
		player.getScore().decrementLevelScore();
	}
	
	
	// This method is used to move the player during an undo()
	// Despite the name of the method, the int "dir" points to the correct position.
	public void reverseMoveBox(int dir, int[] boxPreUndoPosition){
		
		int[] newPosBox = newPosition(boxPreUndoPosition[0], boxPreUndoPosition[1], dir);
		
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
		
		if(map[oldPosBox[1]][oldPosBox[0]] == 3) {// Checks if there was a goal position where the box was before the move
			map[oldPosBox[1]][oldPosBox[0]] = 0;
		}else {
			map[oldPosBox[1]][oldPosBox[0]] = 1;
			boxesInGoal--;
		}
		
		if(map[newPosBox[1]][newPosBox[0]] == 0) {// Checks if there is a goal position where the box currently is
			map[newPosBox[1]][newPosBox[0]] = 3;
		}else {
			map[newPosBox[1]][newPosBox[0]] = 4;
			boxesInGoal++;
		}
	}
	
	public PlayerInterface getPlayer() {
		return player;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPlayer(PlayerInterface player) {
		this.player = player;
	}
	
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}
	

	
}
  	