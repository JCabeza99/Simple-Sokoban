package es.upm.pproject.sokoban.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


public class SaveState {

	private int[][] map;
	private int nGoals;
	private int boxesInGoal;
	private int playerX;
	private int playerY;
	private int playerLevel;
	private Score score;
	private String name;
	private Deque<SavedAction> savedActions;

	public SaveState() {
	}

	public SaveState(ActionManagerInterface ac, LevelInterface level) {
		
		PlayerInterface player = level.getPlayer();
		map = level.getMap();
		nGoals = level.getnGoals();
		name = level.getName();
		boxesInGoal = level.getBoxesInGoal();
		playerX = level.getPlayer().getxPos();
		playerY = level.getPlayer().getyPos();
		playerLevel = level.getPlayer().getLevel();
		ScoreInterface aux = player.getScore();
		score = new Score(aux.getLevelScore(),aux.getTotalScore());
		savedActions = new ArrayDeque<>();
		Iterator<ActionInterface> iterador = ac.getActions().iterator();
		while (iterador.hasNext()) {
			ActionInterface accion = iterador.next();
			savedActions.push(new SavedAction(accion));
		}
	}

	public LevelInterface getLevel() {
		Player player = new Player(playerX, playerY);
		player.setLevel(playerLevel);
		player.setScore(score);
		return new Level(map,name,nGoals, boxesInGoal, player, 0);
	}

	public ActionManagerInterface getActionManager() {
		Iterator<SavedAction> iterador = savedActions.iterator();
		Deque<ActionInterface> actionsSaved = new ArrayDeque<>();
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
