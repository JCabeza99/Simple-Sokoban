package es.upm.pproject.sokoban.model;

import java.io.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class LevelFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(LevelFactory.class);

	private LevelFactory() {
	}

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
		int[][] mat = null;
		boolean placed = false;
		int nBox = 0;
		int nGoals = 0;
		int nBoxOnGoal = 0;
		String name = "";
		File file = new File(path + "level_" + level + ".txt");

		if (!file.exists()) {
				return new Level(mat, name, nGoals, nBoxOnGoal, player, -1);
			}

		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr)) {

			
			// we save the name of the level
			name = br.readLine();
			// we save the files an columns of the level
			String[] rowCol = br.readLine().split("\\s+");
			int fil = Integer.parseInt(rowCol[0]);
			int col = Integer.parseInt(rowCol[1]);
			
			// generation of the matrix
			mat = new int[fil][col];
			// loop to fill the matrix of the level
			int i = 0;
			String line = "";
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
			failureStatus = checkFailureStatus(failureStatus, nBox, nGoals, nBoxOnGoal, placed);

		} catch (Exception e) {
			LOGGER.error("Error while reading the level: ", e);
		}
		player.setLevel(level);
		return new Level(mat, name, nGoals, nBoxOnGoal, player, failureStatus);
	}

	private static int checkFailureStatus(int failureStatus, int nBox, int nGoals, int nBoxOnGoal, boolean placed) {
		if (failureStatus == 0) {
			if (nBox != nGoals) {
				failureStatus = 3;
			} else if (!placed) {
				failureStatus = 4;
			} else if (nGoals == nBoxOnGoal) {
				failureStatus = 5;
			}
		}
		return failureStatus;
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
