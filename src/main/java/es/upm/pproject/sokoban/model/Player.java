package es.upm.pproject.sokoban.model;

public class Player {
	int xPos;
	int yPos;
	Score score;
  	
	public Player(int xPos, int Ypos){ 
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
  	
	

}
