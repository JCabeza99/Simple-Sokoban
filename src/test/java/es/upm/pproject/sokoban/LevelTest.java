package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.upm.pproject.sokoban.model.*;
import java.io.*;
class LevelTest {
	PlayerInterface player = new Player(0,0);
	int x;
	int y;
	int test=10;
	LevelInterface level;
	String path= "tests"+File.separatorChar ;
	//This test class is still on prgress
	
	@BeforeEach
	void init() {
		level = LevelFactory.createLevel(test, player,path);
		test++;
		x = player.getxPos();
		y = player.getyPos();
	}

	@Test
    void testCorrect() {
        assertEquals(y,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    void testMoveUP() {
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    void testMoveRIGHT() {
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    void testMoveDOWN() {
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    void testMoveLEFT() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	
	@Test
    void testMoveBoxUP() {
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    void testMoveBoxRIGHT() {
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    void testMoveBoxDOWN() {
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    void testMoveBoxLEFT() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	@Test
    void testObstBox() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	@Test
    void testObstBoxWall() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	@Test
    void testObstWall() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
}
