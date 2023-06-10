package es.upm.pproject.sokoban.model;

public class ActionPlayerAndBoxMoved implements ActionInterface{

	int dir;// The direction in which the player will move id the action is undone
	int[] boxCurrentPos;
	
	
	public ActionPlayerAndBoxMoved(int dir, int posXBox, int posYBox) {
		this.dir = dir;
		this.boxCurrentPos = new int[] {posXBox, posYBox};
	}
	
	public void undo(LevelInterface level) {
		level.reverseMovePlayer(dir);
		try {
			level.reverseMoveBox(dir, boxCurrentPos);
		} catch (WrongActionException e) {
			// TODO No se si esto esta bien
			e.printStackTrace();
		}
	}
	
	public int getActionCode() {
		return 1;
	}
}
