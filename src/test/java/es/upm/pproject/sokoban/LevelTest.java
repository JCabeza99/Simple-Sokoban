package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import es.upm.pproject.sokoban.model.*;
import java.io.*;
class LevelTest {
	PlayerInterface player = new Player(0,0);
	int x;
	int y;
	LevelInterface level;
	String path= "tests"+File.separatorChar ;
	//This test class is still on prgress
	

	@Test
    void testCorrect() {
		level = LevelFactory.createLevel(10, player,path);
		x = player.getxPos();
		y = player.getyPos();
        assertEquals(y,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    void testMoveUP() {
		level = LevelFactory.createLevel(11, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    void testMoveRIGHT() {
		level = LevelFactory.createLevel(12, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    void testMoveDOWN() {
		level = LevelFactory.createLevel(13, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    void testMoveLEFT() {
		level = LevelFactory.createLevel(13,player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	
	@Test
    void testMoveBoxUP() {
		level = LevelFactory.createLevel(14, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    void testMoveBoxRIGHT() {
		level = LevelFactory.createLevel(15, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    void testMoveBoxDOWN() {
		level = LevelFactory.createLevel(16, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    void testMoveBoxLEFT() {
		level = LevelFactory.createLevel(17, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}

	@ParameterizedTest
	@ValueSource(ints = {18,19,20})
	void testMoveAgainstObstacle(int levelNumber) {
		level = LevelFactory.createLevel(levelNumber, player,path);
		x = player.getxPos();
		y = player.getyPos();
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x,player.getxPos());
	}

}
