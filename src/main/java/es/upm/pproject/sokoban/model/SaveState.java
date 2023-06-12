package es.upm.pproject.sokoban.model;

public class SaveState {

	private Level lv;
	private ActionManager ac;
	SaveState(ActionManager ac, Level level){
		this.ac=ac;
		lv=level;
	}

	public Level getLevel() {
		return lv;
	}
	
	public ActionManager getActionManager() {
		return ac;
	}
}
