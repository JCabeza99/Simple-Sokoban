package es.upm.pproject.sokoban.model;

public interface PlayerInterface {

	public int getxPos();

	public int getyPos();

	public ScoreInterface getScore();

	public void setScore(ScoreInterface score);

	public void move(int xPos, int yPos);

	public int getLevel();

	public void setLevel(int level);

}