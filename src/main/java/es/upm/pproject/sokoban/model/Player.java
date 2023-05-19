package es.upm.pproject.sokoban.model;

public class Player {
	int X;
	int Y;
	int score;
  	
	public Player(){ 
		score=0;
  	}
  	
  	public int getX() {
		return X;
	}
	
  	public void setX(int x) {
		X = x;
	}
	
  	public int getY() {
		return Y;
	}
	
  	public void setY(int y) {
		Y = y;
	}
	
  	public int getScore() {
		return score;
	}
	
  	public void setScore(int punt) { 
 		this.score = punt;
	}
}
