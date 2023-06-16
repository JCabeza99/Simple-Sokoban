package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.LevelFactory;
import es.upm.pproject.sokoban.model.LevelInterface;
import es.upm.pproject.sokoban.model.Player;
import es.upm.pproject.sokoban.model.PlayerInterface;

class LevelFactoryTest {
	PlayerInterface player = new Player(0, 0);
	LevelInterface level;
	String path = "tests" + File.separatorChar;
	// This test class is still on prgress
	int[][] map1 = { { 2, 2, 2, 2, 0, 0, 0, 0 },
			{ 2, 0, 0, 2, 0, 0, 0, 0 },
			{ 2, 0, 0, 2, 2, 2, 2, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 2, 0, 1, 2, 3, 0, 2 },
			{ 2, 0, 0, 0, 2, 0, 0, 2 },
			{ 2, 0, 0, 0, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 0, 0, 0 } };

	@Test
	void Correct() {
		level = LevelFactory.createLevel(1, player, path);
		assertEquals(0, level.getFailureStatus());
		assertArrayEquals(map1, level.getMap());
		assertEquals(1, level.getnGoals());
		assertEquals(4, player.getyPos());
		assertEquals(2, player.getxPos());
	}

	@Test
	void fail2Player() {
		level = LevelFactory.createLevel(2, player, path);
		assertEquals(1, level.getFailureStatus());
	}

	@Test
	void Symbol() {
		level = LevelFactory.createLevel(3, player, path);
		assertEquals(2, level.getFailureStatus());
	}

	@Test
	void NboxandNgoal1() {
		level = LevelFactory.createLevel(4, player, path);
		assertEquals(3, level.getFailureStatus());
	}

	@Test
	void NboxandNgoal2() {
		level = LevelFactory.createLevel(5, player, path);
		assertEquals(3, level.getFailureStatus());
	}

	@Test
	void noPlayer() {
		level = LevelFactory.createLevel(6, player, path);
		assertEquals(4, level.getFailureStatus());
	}

	@Test
	void noGoal_noBox() {
		level = LevelFactory.createLevel(7, player, path);
		assertEquals(5, level.getFailureStatus());
	}

	@Test
	void solved() {
		level = LevelFactory.createLevel(8, player, path);
		assertEquals(5, level.getFailureStatus());
	}

	@Test
	void NoMoreTest() {
		level = LevelFactory.createLevel(9, player, path);
		assertEquals(-1, level.getFailureStatus());
	}

}
