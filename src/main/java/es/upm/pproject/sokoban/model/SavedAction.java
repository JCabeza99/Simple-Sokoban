package es.upm.pproject.sokoban.model;

public class SavedAction {

    int dir;// The direction in which the player will move id the action is undone
	int[] boxCurrentPos;
	
    public SavedAction() {}

    public SavedAction(ActionInterface action) {
        this.dir = action.getDirection();
        if(action instanceof ActionPlayerAndBoxMoved) {
            this.boxCurrentPos = ((ActionPlayerAndBoxMoved)action).getBoxCurrentPos(); 
        }
    }

    public int getDir() {
        return dir;
    }
    
    public int[] getBoxCurrentPos() {
        return boxCurrentPos;
    }
    
}
