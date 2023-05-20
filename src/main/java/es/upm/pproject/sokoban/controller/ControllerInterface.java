package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.LevelInterface;

public interface ControllerInterface {

	public void move(int input);

	public void nextLevel();

	public void reStartGame();

	public LevelInterface getLevel();

}