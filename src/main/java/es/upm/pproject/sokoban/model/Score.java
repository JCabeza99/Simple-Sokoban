package es.upm.pproject.sokoban.model;

public class Score {

    int levelScore;
    int totalScore;

    public Score() {}

    public int getLevelScore() {
        return levelScore;
    }

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public void setLevelScore(int levelScore) {
		this.levelScore = levelScore;
	}

    
}
