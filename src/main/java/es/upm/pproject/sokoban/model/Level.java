package es.upm.pproject.sokoban.model;

public class Level {
	int map[][];
	int score;
	public Level(int[][] mapa){
		this.map=mapa;
		score=0;
  	}
	public void Move(int dir){
		
	}
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
  	