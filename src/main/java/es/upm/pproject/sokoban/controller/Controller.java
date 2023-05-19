package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.*;
public class Controller {
	Level level;
	String path;
	LevelFactory Generador;
	Controller(String path){
		this.path=path;
		Generador=new LevelFactory(path);
		level=new Level();
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
