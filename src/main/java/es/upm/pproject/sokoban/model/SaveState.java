package es.upm.pproject.sokoban.model;

import java.util.Iterator;
import java.util.Stack;

public class SaveState {

	private int[][] map;
	private int nGoals;
	private int boxesInGoal;
	private int playerX;
	private int playerY;
	private int playerLevel;
	private int[] score;
	private String name;
	private Stack<SavedAction> savedActions;

	public SaveState() {
	}

	public SaveState(ActionManager ac, LevelInterface level) {
		PlayerInterface player = level.getPlayer();
		score = new int[2];
		map = level.getMap();
		nGoals = level.getnGoals();
		name = level.getName();
		boxesInGoal = level.getBoxesInGoal();
		playerX = level.getPlayer().getxPos();
		playerY = level.getPlayer().getyPos();
		playerLevel = level.getPlayer().getLevel();
		score[0] = player.getScore().getLevelScore();
		score[1] = player.getScore().getTotalScore();
		savedActions = new Stack<SavedAction>();
		Iterator<ActionInterface> iterador = ac.getActions().iterator();
		while (iterador.hasNext()) {
			ActionInterface accion = iterador.next();
			savedActions.push(new SavedAction(accion));
		}
	}

	public LevelInterface getLevel() {
		Player player = new Player(playerX, playerY);
		player.setLevel(playerLevel);
		player.setScore(new Score(score[0], score[1]));
		return new Level(map,name,nGoals, boxesInGoal, player, 0);
	}

	public ActionManager getActionManager() {
		Iterator<SavedAction> iterador = savedActions.iterator();
		Stack<ActionInterface> actionsSaved = new Stack<ActionInterface>();
		while (iterador.hasNext()) {
			SavedAction accion = iterador.next();
			int dir = accion.getDir();
			int[] boxCurrentPos = accion.getBoxCurrentPos();
			if (boxCurrentPos == null) {
				actionsSaved.push(new ActionPlayerOnly(dir));
			} else {
				actionsSaved.push(new ActionPlayerAndBoxMoved(dir, boxCurrentPos));
			}
		}
		return new ActionManager(actionsSaved);
	}

}
