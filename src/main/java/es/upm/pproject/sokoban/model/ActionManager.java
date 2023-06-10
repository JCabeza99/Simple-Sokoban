package es.upm.pproject.sokoban.model;

import java.util.Stack;

public class ActionManager {

	private final LevelInterface currentLevel;
	private ActionFactory actionFactory;
	private Stack<ActionInterface> actions;
	
	
	public ActionManager(LevelInterface level) {
		this.currentLevel = level;
		this.actionFactory = new ActionFactory();
		this.actions = new Stack<ActionInterface>();
	}
	
	public void createAction(int actionId, int[] additionalInfo) {
		ActionInterface newAction = actionFactory.createAction(actionId, additionalInfo);
		actions.push(newAction);
	}
	
	public void undo() {
		ActionInterface action = actions.pop();
		action.undo(currentLevel);
	}
	
	public Stack<ActionInterface>  getActions(){
		return actions;
	}
}
