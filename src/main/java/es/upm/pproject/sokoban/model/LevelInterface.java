package es.upm.pproject.sokoban.model;

public interface LevelInterface {

	public int getnGoals();
	
	public void setnGoals(int nGoals);

	public String getName();

	public int getBoxesInGoal();

	public int getfailureStatus();

	public void setBoxesInGoal(int boxesInGoal);

	public int[] move(int dir);
	
	public void reverseMovePlayer(int dir);
	
	public void reverseMoveBox(int dir, int[] boxPreUndoPosition);

	public PlayerInterface getPlayer();

	public void setPlayer(PlayerInterface player);

	public int[][] getMap();

	public int getFailureStatus();
	
	public void setMap(int[][] map);

}