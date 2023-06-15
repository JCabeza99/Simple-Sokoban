package es.upm.pproject.sokoban.model;

import java.io.Serializable;
import java.util.Deque;

public interface ActionManagerInterface extends Serializable{

	public void createAction(int actionId, int[] additionalInfo);
	
	public ActionInterface undo();
	
	public Deque<ActionInterface>  getActions();
}
