package es.upm.pproject.sokoban.model;

public class LevelFactory {

	//Still on progress
	/*
	 * 0 vacio
	 * 1 meta
	 * 2 pared
	 * 3 caja
	 * 4 caja meta
	 */
	//This class is expected to parse a level from a text document and build it inside the application
    private static final String PATH = "";
	
	private static final int[][][] LEVELS = {//Level 1
											{{2,2,2,2,0,0,0,0},
										  	 {2,0,0,2,0,0,0,0},
										  	 {2,0,0,2,2,2,2,2},
										  	 {2,0,0,0,0,0,0,2},
										  	 {2,2,0,1,2,3,0,2},
										  	 {2,0,0,0,2,0,0,2},
										  	 {2,0,0,0,2,2,2,2},
										  	 {2,2,2,2,2,0,0,0}},
											{{0,2,2,2,2,2,2},
										  	 {0,2,0,0,0,0,2},
										  	 {2,2,3,0,1,0,2},
										  	 {2,0,0,0,0,0,2},
										  	 {2,0,3,2,1,0,2},
										  	 {2,0,0,2,2,2,2},
										  	 {2,2,2,2,0,0,0}},
											 //Level 3
											{{2,2,2,2,0,0},
											 {2,1,0,2,0,0},
											 {2,1,0,2,2,2},
										     {2,0,3,0,0,2},
											 {2,0,3,0,0,2},
											 {2,0,0,2,2,2},
											 {2,2,2,2,0,0}},
											};
	private static final int[] GOALS = {1,2,2};
	private static final int[][] POS= {{2,4},{3,3},{1,3}};

	public static Level createLevel(int level, Player player) {
		player.move(POS[level-1][0], POS[level-1][1]);
		player.setLevel(level);
		return new Level(LEVELS[level-1],GOALS[level-1],player);
	}


	
		

}
