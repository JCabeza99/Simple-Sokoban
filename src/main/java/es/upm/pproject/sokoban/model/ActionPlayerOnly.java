package es.upm.pproject.sokoban.model;

public class ActionPlayerOnly implements ActionInterface{

	int dir;// The direction in which the player will move id the action is undone
	
	public ActionPlayerOnly(int dir) {
		this.dir = dir;
	}
	
	public void undo(LevelInterface level) {
		level.reverseMovePlayer(dir);
	}

	public int getDirection() {
		return dir;
    }
	
	public int getActionCode() {
		return 0;
	}
}
