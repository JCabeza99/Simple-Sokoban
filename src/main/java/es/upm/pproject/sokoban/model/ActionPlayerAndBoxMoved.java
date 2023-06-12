package es.upm.pproject.sokoban.model;

public class ActionPlayerAndBoxMoved implements ActionInterface {

	int dir;// The direction in which the player will move id the action is undone
	int[] boxCurrentPos;

	public ActionPlayerAndBoxMoved(int dir, int[] boxCurrentPos) {
		this.dir = dir;
		this.boxCurrentPos = boxCurrentPos;
	}

	public void undo(LevelInterface level) {
		level.reverseMovePlayer(dir);
		level.reverseMoveBox(dir, boxCurrentPos);
	}

	public int getDirection() {
		return dir;
	}

	public int[] getBoxCurrentPos() {
		return boxCurrentPos;
	}

	public int getActionCode() {
		return 1;
	}
}
