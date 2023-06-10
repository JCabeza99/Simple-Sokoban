package es.upm.pproject.sokoban.model;

public interface LevelInterface {

	public int getnGoals();

	public void setnGoals(int nGoals);

	public int getBoxesInGoal();

	public void setBoxesInGoal(int boxesInGoal);

	public int[] move(int dir);
	
	public void reverseMovePlayer(int dir);
	
	public void reverseMoveBox(int dir, int[] boxPreUndoPosition) throws WrongActionException ;

	public PlayerInterface getPlayer();

	public void setPlayer(PlayerInterface player);

	public int[][] getMap();

	public void setMap(int[][] map);

}