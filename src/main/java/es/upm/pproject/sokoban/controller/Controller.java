package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.*;
public class Controller {
	Level level;
	Player player;
	String path;
	LevelFactory Generador;
	public Controller(String path){
		this.path=path;
		Generador=new LevelFactory(path);
		player = new Player(0,0);
		level=new Level(player);
		Generador.generateLevel(level);
	}
	 public void move(int input) {
		 level.Move(input);
	 }
	 
	 public void reStartGame(){
		 Generador=new LevelFactory(path);
		 Generador.generateLevel(level);
	 }
	 public Level getLevel() {
		 return level;
	 }
}
