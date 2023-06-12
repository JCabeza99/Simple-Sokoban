package es.upm.pproject.sokoban.model;

import java.util.Stack;

public class ActionManager {

	private ActionFactory actionFactory;
	private Stack<ActionInterface> actions;
	
	
	public ActionManager() {
		this.actionFactory = new ActionFactory();
		this.actions = new Stack<ActionInterface>();
	}
	
	public ActionManager(Stack<ActionInterface> actions) {
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
	
	public Stack<ActionInterface>  getActions(){
		return actions;
	}
}
