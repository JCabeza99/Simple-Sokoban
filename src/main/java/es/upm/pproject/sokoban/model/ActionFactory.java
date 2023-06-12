package es.upm.pproject.sokoban.model;

public class ActionFactory {

	

	public ActionFactory() {

	}

	// In the actions where movement is involved, additionalInfo will always have
	// the direction
	public ActionInterface createAction(int actionId, int[] additionalInfo) {

		switch (actionId) {
			case 0:
				return new ActionPlayerOnly(additionalInfo[0]);
			case 1:
				return new ActionPlayerAndBoxMoved(additionalInfo[0],
						new int[] { additionalInfo[1], additionalInfo[2] });
			default:
				return null;
		}
	}
}
