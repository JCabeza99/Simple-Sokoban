package es.upm.pproject.sokoban.controller;

import java.awt.event.KeyEvent;

import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.view.Gui;

import java.io.*;

public class Controller implements ControllerInterface {
	LevelInterface level;
	PlayerInterface player;
	Gui frame;

	String path = "levels" + File.separatorChar;
	// String path;

	ActionManager actionManager;

	// String path;
	public Controller(Gui frame) {
		player = new Player(0, 0);
		level = LevelFactory.createLevel(1, player, path);
		actionManager = new ActionManager(level);
		this.frame = frame;
	}

	public void move(int input) {
		int[] movementData = level.move(input);

		if (movementData[0] >= 0) {

			int actionId = movementData[0]; // The current implementation of the actionManger needs movementData[0] to
											// be the direction

			switch (input) { // Inverts the direction so that actions can undo the move
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
				default:
					return;
			}

			actionManager.createAction(actionId, movementData);
			frame.updateView();
		}
	}

	public void undo() {
		if (!actionManager.getActions().isEmpty()) {
			actionManager.undo();
		} else {
			frame.createDialog("Warning: There are no actions to undo.");
		}
		frame.updateView();
	}

	public void nextLevel() {
		int newlevel = player.getLevel() + 1;
		player.getScore().nextLevel();

		level = LevelFactory.createLevel(newlevel, player, path);
		int failureStatus = level.getfailureStatus();
		while(failureStatus > 0) {
			switch (failureStatus) {
				case 1:
					frame.createDialog("Error: Level" + newlevel +" contains more than one warehouse man. Next level will be loaded");
					break;
				case 2:
					frame.createDialog("Error: Level" + newlevel +" contains an invalid symbol. Next level will be loaded");
					break;
				case 3:
					frame.createDialog("Error: Level" + newlevel +" contains different number of goals and boxes. Next level will be loaded");
					break;
				case 4:
					frame.createDialog("Error: Level" + newlevel +" does not have a warehouse man set. Next level will be loaded");
					break;
				case 5:
					frame.createDialog("Error: Level" + newlevel +" is solved. Next level will be loaded");
				default:
					break;	
			}
			newlevel++;
			level = LevelFactory.createLevel(newlevel, player, path);
		}
		if(failureStatus == - 1)
		{
			frame.endGame();
		}
		actionManager = new ActionManager(level);


		
		frame.updateView();
	}

	public void reStartLevel() {
		int playerLevel = player.getLevel();
		player.getScore().resetLevelScore();
		level = LevelFactory.createLevel(playerLevel, player, path);
		frame.updateView();
	}

	public void reStartGame() {
		player = new Player(0, 0);
		level = LevelFactory.createLevel(1, player, path);
		frame.updateView();
	}

	public LevelInterface getLevel() {
		return level;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'save'");
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'load'");
	}
}
