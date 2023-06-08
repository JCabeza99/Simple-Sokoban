package es.upm.pproject.sokoban.model;

import java.io.*;

public class LevelFactory {

	
	

	
	//This class is expected to parse a level from a text document and build it inside the application
	
	private static final int[][][] LEVELS = {//Level 1
											{{2,2,2,2,0,0,0,0},
										  	 {2,0,0,2,0,0,0,0},
										  	 {2,0,0,2,2,2,2,2},
										  	 {2,0,0,0,0,0,0,2},
										  	 {2,2,0,1,2,3,0,2},
										  	 {2,0,0,0,2,0,0,2},
										  	 {2,0,0,0,2,2,2,2},
										  	 {2,2,2,2,2,0,0,0}},
											//level 2
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
	/*0 vacio
	 * 1 meta
	 * 2 pared
	 * 3 caja
	 * 4 caja meta
	 */
	public static LevelInterface createLevel(int level, PlayerInterface player,String path) {
		//initialize var 
		int failureStatus=0;
		File archivo;
		FileReader fr;
		BufferedReader br;
		path=path+"level_" + level + ".txt";
		System.out.print(path);
		int[][] mat=null;
		boolean placed=false;
		int nBox=0;
		int nGoals=0;
		int nBoxOnGoal=0;
		String name=null;
		try {
			//initialize reader
			archivo=new File(path);
			fr=new FileReader(archivo);
			br=new BufferedReader(fr);
			 //we save the name of the level
			name = br.readLine(); 
			//we save the files an columns of the level 
			String line = br.readLine();
			int fil=0;//numero fil y col no tienen por que ser de un Digito. corregir 
			int col=0;
			int i;
			int length = line.length();//we need the length because readLine puts a 0 at the end of the String
			for(i=0;line.charAt(i)!=' ';i++) {//we get the files
				fil=fil*10;
				fil=fil+Character.getNumericValue(line.charAt(i));
			}
			i++;
			//the loop begin where the loop of files ends
			for(;i<length&&line.charAt(i)!=' '&&line.charAt(i)!='\n';i++) {//we get the columns
				col=col*10;
				col=col+Character.getNumericValue(line.charAt(i));
			}
			//generation of the matrix 
			mat= new int[fil][col];
			//loop to fill the matrix of the level 
			i =0;
			while(i<fil&&failureStatus==0) {
				line = br.readLine();
				int j=0;
				while(j<col&&failureStatus==0) {
					switch(line.charAt(j)) {
						case '.': 	mat[i][j]=0;//case Void 
									break;
									
						case '+' :	mat[i][j]=2;//case Wall
									break;
				
						case '*':	mat[i][j]=1;//case Goal 
									nGoals++;
									break;
									
						case '#':	mat[i][j]=3;//case Box
									nBox++;
									break;
									
						case 'G':	mat[i][j]=4;//case Box
									nBox++;
									nGoals++;
									nBoxOnGoal++;
									break;
							
						case 'W': 	if (placed) failureStatus=1;//case player
									mat[i][j]=0;
									player.move(j,i);
									placed =true;
									break;
						
						default: 	failureStatus=2;// if there is another char fails1
									break;
					}
					j++;
				}
				i++;
			}
		if(nBox!=nGoals) {
			failureStatus=3;
		}
		if(!placed) {
			failureStatus=4;
		}
		if(nGoals==nBoxOnGoal) {
			failureStatus=5;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Level(name,mat,nGoals,player,failureStatus);
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
