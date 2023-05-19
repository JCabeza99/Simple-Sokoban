package es.upm.pproject.sokoban.model;

public class Level {
	int map[][];
	int nLevel;
	Player player;
	public Level(){
  	}
	public void Move(int dir){
		System.out.println("muevo");
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
}
  	