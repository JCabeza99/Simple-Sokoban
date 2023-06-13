package es.upm.pproject.sokoban.model;

import java.util.Deque;

public interface ActionManagerInterface {

	public void createAction(int actionId, int[] additionalInfo);
	
	public ActionInterface undo();
	
	public Deque<ActionInterface>  getActions();
}
