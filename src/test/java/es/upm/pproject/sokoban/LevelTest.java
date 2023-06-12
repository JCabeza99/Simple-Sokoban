package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.upm.pproject.sokoban.model.*;
import java.io.*;
public class LevelTest {
	PlayerInterface player = new Player(0,0);
	int x;
	int y;
	int test=10;
	LevelInterface level;
	String path= "tests"+File.separatorChar ;
	//This test class is still on prgress
	
	@BeforeEach
	public void init() {
		level = LevelFactory.createLevel(test, player,path);
		test++;
		x = player.getxPos();
		y = player.getyPos();
	}

	@Test
    public void testCorrect() {
        assertEquals(y,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void testMoveUP() {
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    public void testMoveRIGHT() {
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    public void testMoveDOWN() {
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void testMoveLEFT() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	
	@Test
    public void testMoveBoxUP() {
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    public void testMoveBoxRIGHT() {
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    public void testMoveBoxDOWN() {
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void testMoveBoxLEFT() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	@Test
    public void testObstBox() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	@Test
    public void testObstBoxWall() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
	@Test
    public void testObstWall() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1,player.getxPos());
	}
}
