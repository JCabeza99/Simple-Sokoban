package es.upm.pproject.sokoban.model;

public class Score {

    int levelScore;
    int totalScore;

    public Score() {}

    //Getters and setters

    public int getLevelScore() {
        return levelScore;
    }

	public int getTotalScore() {
		return totalScore;
	}

	public void incrementLevelScore() {
		levelScore++;
        totalScore++;
	}

    public void decrementLevelScore() {
        levelScore--;
        totalScore--;
    }

    public void resetLevelScore() {
        totalScore -= levelScore;
        levelScore = 0;
    }

    public void setLevelScore(int levelScore) {
		this.levelScore = levelScore;
	}

    public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
    }
    
}
