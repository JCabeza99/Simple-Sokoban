package es.upm.pproject.sokoban.controller;

import java.awt.event.KeyEvent;

import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.view.Gui;

import java.io.*;

public class Controller implements ControllerInterface {
	private LevelInterface level;
	private Gui frame;

	private static final String PATH = "levels" + File.separatorChar;

	ActionManagerInterface actionManager;

	public Controller(Gui frame) {
		level = LevelFactory.createLevel(1, new Player(0, 0), PATH);
		actionManager = new ActionManager();
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
			ActionInterface action = actionManager.undo();
			action.undo(level);
		} else {
			frame.createDialog("Info: There are no actions to undo.");
		}
		frame.updateView();
	}

	public void nextLevel() {
		PlayerInterface player = level.getPlayer();
		int newlevel = player.getLevel() + 1;
		player.getScore().nextLevel();

		level = createLevel(newlevel, player);

		actionManager = new ActionManager();

		frame.updateView();
	}

	public void reStartLevel() {
		PlayerInterface player = level.getPlayer();
		int playerLevel = player.getLevel();
		player.getScore().resetLevelScore();
		level = createLevel(playerLevel, player);
		frame.updateView();
	}

	public void reStartGame() {
		PlayerInterface player = new Player(0, 0);
		level = createLevel(1, player);
		frame.updateView();
	}

	public LevelInterface getLevel() {
		return level;
	}

	public ActionManagerInterface getActionManager() {
		return actionManager;
	}

	public void save(File file) {
		if (file != null) {
			SaveState save = new SaveState(actionManager, level);
			try {
				SaveStateManager.save(save, file);
				frame.createDialog("Your file has been saved in: " + file.getAbsolutePath());
			} catch (Exception e) {
				frame.createDialog("Unexpected error while saving the file, please check the log.");
				e.printStackTrace();
			}
		}
		frame.requestFocus();
	}

	public void load(File file) {
		if (file != null) {
			try {
				SaveState save = SaveStateManager.load(file);
				actionManager = save.getActionManager();
				level = save.getLevel();
				frame.createDialog("Your file has been loaded succesfully");
				frame.updateView();
			} catch (Exception e) {
				frame.createDialog("Unexpected error while loading the file, please check the log.");
				e.printStackTrace();
			}
		}
		frame.requestFocus();
	}

	private LevelInterface createLevel(int level, PlayerInterface player) {
		LevelInterface createdlevel = LevelFactory.createLevel(level, player, PATH);
		int failureStatus = createdlevel.getFailureStatus();
		boolean stop = false;
		while (failureStatus > 0 && !stop) {
			String error ="Error: Level";
			switch (failureStatus) {
				case 1:
					frame.createDialog(error + level
							+ " contains more than one warehouse man. Next level will be loaded");
					break;
				case 2:
					frame.createDialog(
							error + level + " contains an invalid symbol. Next level will be loaded");
					break;
				case 3:
					frame.createDialog(error + level
							+ " contains different number of goals and boxes. Next level will be loaded");
					break;
				case 4:
					frame.createDialog(error+ level
							+ " does not have a warehouse man set. Next level will be loaded");
					break;
				case 5:
					frame.createDialog(error + level + " is solved. Next level will be loaded");
					break;
				default:
					stop = true;
					break;
			}
			level++;
			createdlevel = LevelFactory.createLevel(level, player, PATH);
		}

		if (failureStatus == -1) {
			frame.endGame();
		}
		return createdlevel;
	}
}