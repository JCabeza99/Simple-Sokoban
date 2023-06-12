package es.upm.pproject.sokoban.model;

public interface ActionInterface {
	
	public void undo(LevelInterface level);
	
	public int getActionCode();

	public int getDirection();

}
