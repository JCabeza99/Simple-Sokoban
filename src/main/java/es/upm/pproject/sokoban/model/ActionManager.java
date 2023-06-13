package es.upm.pproject.sokoban.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionManager implements ActionManagerInterface{

	private ActionFactory actionFactory;
	private Deque<ActionInterface> actions;
	
	
	public ActionManager() {
		this.actionFactory = new ActionFactory();
		this.actions = new ArrayDeque<>();
	}
	
	public ActionManager(Deque<ActionInterface> actions) {
		this.actionFactory = new ActionFactory();
		this.actions = actions;
	}

	public void createAction(int actionId, int[] additionalInfo) {
		ActionInterface newAction = actionFactory.createAction(actionId, additionalInfo);
		actions.push(newAction);
	}
	
	public ActionInterface undo() {
		return actions.pop();
		
	}
	
	public Deque<ActionInterface>  getActions(){
		return actions;
	}
}
