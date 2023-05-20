package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.*;
public class Controller {
	Level level;
	Player player;
	//String path;
	public Controller(){
		player = new Player(0,0);
		level = LevelFactory.createLevel(1, player);
	}
	 public void move(int input) {
		 level.move(input);
	 }

	 public void nextLevel() {
		int playerLevel = player.getLevel();
		player.getScore().nextLevel();
		level = LevelFactory.createLevel(playerLevel + 1, player);
	 }
	 
	 public void reStartGame(){
		int playerLevel = player.getLevel();
		player.getScore().resetLevelScore();
		level = LevelFactory.createLevel(playerLevel, player);
	 }
	 
	 public Level getLevel() {
		 return level;
	 }
}
