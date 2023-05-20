package es.upm.pproject.sokoban.model;

public interface ScoreInterface {

	public int getLevelScore();

	public int getTotalScore();

	public void incrementLevelScore();

	public void decrementLevelScore();

	public void resetLevelScore();

	public void nextLevel();

	public void setLevelScore(int levelScore);

	public void setTotalScore(int totalScore);

}