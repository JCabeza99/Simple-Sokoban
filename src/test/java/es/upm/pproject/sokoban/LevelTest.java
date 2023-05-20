package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.upm.pproject.sokoban.model.*;
public class LevelTest {
	Player player = new Player(0,0);
	int x;
	int y;
	Level level;
	
	@BeforeEach
	public void init() {
		level = LevelFactory.createLevel(1, player);
		x = player.getxPos();
		y = player.getyPos();
	}

	@Test
    public void moveTestUP() {
		level.move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void moveTestRIGHT() {
		level.move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    public void moveTestDOWN() {
		level.move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void moveTestLEFT() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    public void moveBoxObstacleTest1() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1 ,player.getxPos());
	}
	@Test
    public void moveBoxObstacle2() {
		level.move(KeyEvent.VK_UP);
		level.move(KeyEvent.VK_RIGHT);
		level.move(KeyEvent.VK_RIGHT);
		level.move(KeyEvent.VK_RIGHT);
		level.move(KeyEvent.VK_DOWN);
        int[][] map = level.getMap();
		assertEquals(3, map[5][5]);
	}
	@Test
    public void moveWallObstacle() {
		level.move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x,player.getxPos());
	}
}
