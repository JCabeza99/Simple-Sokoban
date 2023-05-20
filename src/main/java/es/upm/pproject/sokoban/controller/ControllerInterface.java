package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.Level;

public interface ControllerInterface {

	void move(int input);

	void nextLevel();

	void reStartGame();

	Level getLevel();

}