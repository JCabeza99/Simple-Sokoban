package es.upm.pproject.sokoban.model;

import java.io.*;

public class LevelFactory {

	private LevelFactory() {}

	/*
	 * 0 empty square
	 * 1 goal
	 * 2 wall
	 * 3 box
	 * 4 box in goal
	 */
	public static LevelInterface createLevel(int level, PlayerInterface player, String path) {
		// initialize var
		int failureStatus = 0;
		File file;
		FileReader fr;
		BufferedReader br;
		path=path+"level_" + level + ".txt";
		int[][] mat=null;
		boolean placed=false;
		int nBox=0;
		int nGoals=0;
		int nBoxOnGoal=0;
		String name="";
		try {
			// initialize reader
			file = new File(path);
			if(file == null ||!file.exists()) {
				return new Level(mat,name, nGoals, nBoxOnGoal, player, -1);
			}
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			// we save the name of the level
			name = br.readLine();
			// we save the files an columns of the level
			String line = br.readLine();
			int fil = 0;// numero fil y col no tienen por que ser de un Digito. corregir
			int col = 0;
			int i;
			int length = line.length();// we need the length because readLine puts a 0 at the end of the String
			for (i = 0; line.charAt(i) != ' '; i++) {// we get the files
				fil = fil * 10;
				fil = fil + Character.getNumericValue(line.charAt(i));
			}
			i++;
			// the loop begin where the loop of files ends
			for (; i < length && line.charAt(i) != ' ' && line.charAt(i) != '\n'; i++) {// we get the columns
				col = col * 10;
				col = col + Character.getNumericValue(line.charAt(i));
			}
			// generation of the matrix
			mat = new int[fil][col];
			// loop to fill the matrix of the level
			i = 0;
			while (i < fil && failureStatus == 0) {
				line = br.readLine();
				int j = 0;
				while (j < col && failureStatus == 0) {
					switch (line.charAt(j)) {
						case '.':
							mat[i][j] = 0;// case Void
							break;

						case '+':
							mat[i][j] = 2;// case Wall
							break;

						case '*':
							mat[i][j] = 1;// case Goal
							nGoals++;
							break;

						case '#':
							mat[i][j] = 3;// case Box
							nBox++;
							break;

						case 'G':
							mat[i][j] = 4;// case Box
							nBox++;
							nGoals++;
							nBoxOnGoal++;
							break;

						case 'W':
							if (placed)
								failureStatus = 1;// case player
							mat[i][j] = 0;
							player.move(j, i);
							placed = true;
							break;

						default:
							failureStatus = 2;// if there is another char fails1
							break;
					}
					j++;
				}
				i++;
			}
			if (failureStatus == 0) {
				if (nBox != nGoals) {
					failureStatus = 3;
				} else if (!placed) {
					failureStatus = 4;
				} else if (nGoals == nBoxOnGoal) {
					failureStatus = 5;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		player.setLevel(level);
		return new Level(mat,name, nGoals, nBoxOnGoal, player, failureStatus);
	}
	/*
	 * cases of failureStatus
	 * 0=no failure
	 * 1=there was 2 player
	 * 2=symbol not valid in the map
	 * 3=number of goals different of the number of boxes
	 * 4=there is no player in the map
	 * 5=level already solved or not box o goals
	 */
}
