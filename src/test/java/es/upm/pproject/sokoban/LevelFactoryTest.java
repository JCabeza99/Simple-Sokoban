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
	int test=1;
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
	@BeforeEach
	public void init() {
		level = LevelFactory.createLevel(test, player,path);
		test++;
	}

	@Test
    public void Correct() {
		assertEquals(level.getFailureStatus(),0);
		assertArrayEquals(level.getMap(),map1);
		assertEquals(level.getnGoals(),1);
        assertEquals(2,player.getyPos());
        assertEquals(4,player.getxPos());
	}
	@Test
    public void fail2Player() {
        assertEquals(level.getFailureStatus(),1);
	}
	
	@Test
    public void Symbol() {
		assertEquals(level.getFailureStatus(),2);
	}
	
	@Test
    public void NboxandNgoal1() {
		assertEquals(level.getFailureStatus(),3);
	}
	
	@Test
    public void NboxandNgoal2() {
		assertEquals(level.getFailureStatus(),4);
	}
	
	@Test
    public void noPlayer() {
		assertEquals(level.getFailureStatus(),5);
	}
	
	@Test
    public void noGoal_noBox() {
		assertEquals(level.getFailureStatus(),6);
	}
	
	@Test
    public void solved() {
		assertEquals(level.getFailureStatus(),6);
	}
	
	@Test
    public void NoMoreTest() {
		assertEquals(level.getFailureStatus(),-1);
	}
	
	
}
