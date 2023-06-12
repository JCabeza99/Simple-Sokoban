package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.LevelFactory;
import es.upm.pproject.sokoban.model.LevelInterface;
import es.upm.pproject.sokoban.model.Player;
import es.upm.pproject.sokoban.model.PlayerInterface;

public class LevelFactoryTest {
	PlayerInterface player = new Player(0,0);
	LevelInterface level;
	String path= "tests"+File.separatorChar ;
	//This test class is still on prgress
	int[][] map1={{2,2,2,2,0,0,0,0},
		  	 {2,0,0,2,0,0,0,0},
		  	 {2,0,0,2,2,2,2,2},
		  	 {2,0,0,0,0,0,0,2},
		  	 {2,2,0,1,2,3,0,2},
		  	 {2,0,0,0,2,0,0,2},
		  	 {2,0,0,0,2,2,2,2},
		  	 {2,2,2,2,2,0,0,0}};

	@Test
    public void Correct() {
		level = LevelFactory.createLevel(1, player,path);
		assertEquals(level.getFailureStatus(),0);
		assertArrayEquals(level.getMap(),map1);
		assertEquals(level.getnGoals(),1);
        assertEquals(4,player.getyPos());
        assertEquals(2,player.getxPos());
	}
	@Test
    public void fail2Player() {
		level = LevelFactory.createLevel(2, player,path);
        assertEquals(1,level.getFailureStatus());
	}
	
	@Test
    public void Symbol() {
		level = LevelFactory.createLevel(3, player,path);
		assertEquals(2,level.getFailureStatus());
	}
	
	@Test
    public void NboxandNgoal1() {
		level = LevelFactory.createLevel(4, player,path);
		assertEquals(3,level.getFailureStatus());
	}
	
	@Test
    public void NboxandNgoal2() {
		level = LevelFactory.createLevel(5, player,path);
		assertEquals(3,level.getFailureStatus());
	}
	
	@Test
    public void noPlayer() {
		level = LevelFactory.createLevel(6, player,path);
		assertEquals(4,level.getFailureStatus());
	}
	
	@Test
    public void noGoal_noBox() {
		level = LevelFactory.createLevel(7, player,path);
		assertEquals(5,level.getFailureStatus());
	}
	
	@Test
    public void solved() {
		level = LevelFactory.createLevel(8, player,path);
		assertEquals(5,level.getFailureStatus());
	}
	
	@Test
    public void NoMoreTest() {
		level = LevelFactory.createLevel(9, player,path);
		assertEquals(-1,level.getFailureStatus());
	}
	
	
}
