package es.upm.pproject.sokoban.model;

public class Player {
	int X;
	int Y;
	int scoreTotal;
	int scoreLevel;
  	
	public Player(){ 
		scoreTotal=0;
		scoreLevel=0;
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
	

}
