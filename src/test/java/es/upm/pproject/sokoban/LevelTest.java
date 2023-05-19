package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;
import es.upm.pproject.sokoban.model.*;
public class LevelTest {
	Score punt= new Score();
	Player player = new Player(0,0);
	Level nivel=new Level(player);
	LevelFactory car=new LevelFactory("pru");
	
	@Test
    public void moveTest1() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_UP);
        assertEquals(y-1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void moveTest2() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_RIGHT);
        assertEquals(y,player.getyPos());
        assertEquals(x+1 ,player.getxPos());
	}
	@Test
    public void moveTest3() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_DOWN);
        assertEquals(y+1,player.getyPos());
        assertEquals(x ,player.getxPos());
	}
	@Test
    public void moveTest4() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1 ,player.getxPos());
	}
	@Test
    public void moveBoxObstacleTest1() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x-1 ,player.getxPos());
	}
	@Test
    public void moveBoxObstacle2() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x,player.getxPos());
	}
	@Test
    public void moveWallObstacle() {
		int y=player.getyPos();
		int x=player.getxPos();
        car.generateLevel(nivel);
		nivel.Move(KeyEvent.VK_LEFT);
        assertEquals(y,player.getyPos());
        assertEquals(x,player.getxPos());
	}
}
