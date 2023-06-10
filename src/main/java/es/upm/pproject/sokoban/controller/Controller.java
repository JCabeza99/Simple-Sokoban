package es.upm.pproject.sokoban.controller;
import java.awt.event.KeyEvent;

import es.upm.pproject.sokoban.model.*;
import java.io.*;
public class Controller implements ControllerInterface {
	LevelInterface level;
	PlayerInterface player;

	String path= "levels"+File.separatorChar ;
	//String path;

	ActionManager actionManager;
	//String path;
	public Controller(){
		player = new Player(0,0);
		level = LevelFactory.createLevel(1, player,path);
		actionManager = new ActionManager(level);
	}

	public void move(int input) {
		 int[] movementData = level.move(input);
		 
		 if(movementData[0] >= 0) {
			 
			 int actionId = movementData[0]; // The current implementation of the actionManger needs movementData[0] to be the direction
			 
			 switch(input) { // Inverts the direction so that actions can undo the move
				case KeyEvent.VK_UP:
					movementData[0] = KeyEvent.VK_DOWN;
					break;
				case KeyEvent.VK_DOWN:
					movementData[0] = KeyEvent.VK_UP;
					break;
				case KeyEvent.VK_RIGHT:
					movementData[0] = KeyEvent.VK_LEFT;
					break;
				case KeyEvent.VK_LEFT:
					movementData[0] = KeyEvent.VK_RIGHT;
					break;
				default :
					return;
				}
			 
			 actionManager.createAction(actionId, movementData);
			 
		 }
	 }
	
	public void undo() {
		actionManager.undo();
	}


	public void nextLevel() {
		int playerLevel = player.getLevel();
		player.getScore().nextLevel();

		level = LevelFactory.createLevel(playerLevel + 1, player,path);

		actionManager = new ActionManager(level);

	 }
	 

	public void reStartLevel(){
		int playerLevel = player.getLevel();
		player.getScore().resetLevelScore();
		level = LevelFactory.createLevel(playerLevel, player,path);
	 }
	 
	public void reStartGame(){
		player = new Player(0,0);
		level = LevelFactory.createLevel(1, player,path);
	 }
	
	
	public LevelInterface getLevel() {
		 return level;
	 }
	
	public ActionManager getActionManager(){
		return actionManager;
	}
}
