package es.upm.pproject.sokoban.model;

import java.awt.event.KeyEvent;

public class Level implements LevelIntergace {
	
	private int map[][];
	
	
	// ints meanings:
	//  0 = Empty Square
	//  1 = Goal without boxes
	// 	2 = Wall
	//  3 = Box
	//  4 = Box in Goal
	
	
	
	private int nGoals;
	private int boxesInGoal;
	private Player player;
	
	
	public Level(int[][] map, int nGoals, Player player){
		this.map = map;
		this.player = player;
		this.nGoals = nGoals;
		boxesInGoal = 0;
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


	public void setBoxesInGoal(int boxesInGoal) {
		this.boxesInGoal = boxesInGoal;
	}


	public void move(int dir){
		
		
		int[] newPosPlayer = newPosition(player.getxPos(), player.getyPos(), dir);
		
		switch(checkPosition(newPosPlayer[0], newPosPlayer[1])) {
		case 0:
			player.move(newPosPlayer[0], newPosPlayer[1]);
			player.getScore().incrementLevelScore();
			break;
		case 1:
			
			int[] newPosBox = newPosition(newPosPlayer[0], newPosPlayer[1], dir);
			
			if(checkPosition(newPosBox[0], newPosBox[1]) == 0) {
				
				player.move(newPosPlayer[0], newPosPlayer[1]);
				player.getScore().incrementLevelScore();
				
				if(map[newPosPlayer[1]][newPosPlayer[0]] == 3) {
					map[newPosPlayer[1]][newPosPlayer[0]] = 0;
				}else {
					map[newPosPlayer[1]][newPosPlayer[0]] = 1;
					boxesInGoal--;
				}
				
				if(map[newPosBox[1]][newPosBox[0]] == 0) {
					map[newPosBox[1]][newPosBox[0]] = 3;
				}else {
					map[newPosBox[1]][newPosBox[0]] = 4;
					boxesInGoal++;
				}
				
			}
			break;
		default : return;
		}
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
			return 1;  //The action "move" could not happen depending on the context
		}else {    // If the item is a wall
			return -1; // the action "move" can not be performed
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}
	
}
  	