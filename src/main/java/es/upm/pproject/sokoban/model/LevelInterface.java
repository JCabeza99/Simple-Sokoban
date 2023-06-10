package es.upm.pproject.sokoban.model;

public interface LevelInterface {

	public int getnGoals();
	
	public String getName();

	public void setnGoals(int nGoals);

	public int getBoxesInGoal();

	public void setBoxesInGoal(int boxesInGoal);

	public void move(int dir);

	public PlayerInterface getPlayer();

	public void setPlayer(PlayerInterface player);

	public int[][] getMap();

	public void setMap(int[][] map);

}