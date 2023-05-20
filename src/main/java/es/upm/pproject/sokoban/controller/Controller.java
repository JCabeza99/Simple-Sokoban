package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.*;
public class Controller implements ControllerInterface {
	Level level;
	Player player;
	//String path;
	public Controller(){
		player = new Player(0,0);
		level = LevelFactory.createLevel(1, player);
	}
	 @Override
	public void move(int input) {
		 level.move(input);
	 }

	 @Override
	public void nextLevel() {
		int playerLevel = player.getLevel();
		player.getScore().nextLevel();
		level = LevelFactory.createLevel(playerLevel + 1, player);
	 }
	 
	 @Override
	public void reStartGame(){
		int playerLevel = player.getLevel();
		player.getScore().resetLevelScore();
		level = LevelFactory.createLevel(playerLevel, player);
	 }
	 
	 @Override
	public Level getLevel() {
		 return level;
	 }
}
