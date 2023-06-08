package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.*;
import java.io.*;
public class Controller implements ControllerInterface {
	LevelInterface level;
	PlayerInterface player;
	String path= "levels"+File.separatorChar ;
	//String path;
	public Controller(){
		player = new Player(0,0);
		level = LevelFactory.createLevel(1, player,path);
	}

	public void move(int input) {
		 level.move(input);
	 }


	public void nextLevel() {
		int playerLevel = player.getLevel();
		player.getScore().nextLevel();
		level = LevelFactory.createLevel(playerLevel + 1, player,path);
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
	
	public void undo() {
		
	}
	
	public LevelInterface getLevel() {
		 return level;
	 }
}
