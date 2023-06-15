package es.upm.pproject.sokoban.model;

import java.io.Serializable;

public class ActionFactory implements Serializable{
	
	enum addInfo{
		DIRECTION,
		POSX,
		POSY
	}

	public ActionFactory() {//only creates Acction objects. No need for set anything in the constructor 

	}

	// In the actions where movement is involved, additionalInfo will always have
	// the direction
	public ActionInterface createAction(int actionId, int[] additionalInfo) {

		switch (actionId) {
			case 0:
				return new ActionPlayerOnly(additionalInfo[addInfo.DIRECTION.ordinal()]);
			case 1:
				return new ActionPlayerAndBoxMoved(additionalInfo[addInfo.DIRECTION.ordinal()],
						new int[] { additionalInfo[addInfo.POSX.ordinal()], additionalInfo[addInfo.POSY.ordinal()] });
			default:
				return null;
		}
	}
}
