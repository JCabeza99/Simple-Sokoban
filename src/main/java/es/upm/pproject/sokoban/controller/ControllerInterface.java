package es.upm.pproject.sokoban.controller;

import java.io.File;

import es.upm.pproject.sokoban.model.ActionManager;
import es.upm.pproject.sokoban.model.ActionManagerInterface;
import es.upm.pproject.sokoban.model.LevelInterface;

public interface ControllerInterface extends java.io.Serializable {

	public void move(int input);

	public void nextLevel();

	public void reStartGame();
	
	public void reStartLevel();
	
	public void undo();

	public LevelInterface getLevel();
	
	public ActionManagerInterface getActionManager();

    public void save(File file);

    public void load(File file);

}