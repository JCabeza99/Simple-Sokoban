package es.upm.pproject.sokoban.model;

public class Player {
	int xPos;
	int yPos;
	int level;
	Score score;

  	
	public Player(int xPos, int yPos){ 
		this.xPos = xPos;
		this.yPos = yPos;
		this.level = 1;
		score = new Score();
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
  	public void move(int xPos, int yPos) {
  		this.xPos = xPos;
  		this.yPos = yPos;
  	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
  	
	

}
