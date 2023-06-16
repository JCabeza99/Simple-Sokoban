package es.upm.pproject.sokoban.model;

public interface ActionInterface extends java.io.Serializable{
	
	public void undo(LevelInterface level);
	
	public int getActionCode();

	public int getDirection();

}
