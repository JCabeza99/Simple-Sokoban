package es.upm.pproject.sokoban;


import java.awt.event.KeyEvent;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import es.upm.pproject.sokoban.model.ActionManager;
import es.upm.pproject.sokoban.model.ActionManagerInterface;
import es.upm.pproject.sokoban.model.LevelInterface;
import es.upm.pproject.sokoban.model.Level;
import es.upm.pproject.sokoban.model.Player;
import es.upm.pproject.sokoban.model.SaveState;
import es.upm.pproject.sokoban.model.SaveStateManager;
import es.upm.pproject.sokoban.model.Score;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SaveLoadTest {
       
	
	private String path = "tests" + File.separatorChar;
	private SaveState save;
	private ActionManagerInterface actionManager;
	private LevelInterface level;
	
	@BeforeAll
	void init() {
		int[][] map1 = { { 2, 2, 2, 2, 0, 0, 0, 0 },
				{ 2, 0, 0, 2, 0, 0, 0, 0 },
				{ 2, 0, 0, 2, 2, 2, 2, 2 },
				{ 2, 0, 0, 0, 0, 0, 0, 2 },
				{ 2, 2, 0, 1, 2, 0, 0, 2 },
				{ 2, 0, 0, 0, 2, 3, 0, 2 },
				{ 2, 0, 0, 0, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 0, 0, 0 } };
		
		level = new Level(map1, "Test_Level",1, 0, new Player(5, 4), 0);
		level.getPlayer().setScore(new Score(5, 5));
		actionManager = new ActionManager();
		actionManager.createAction(0, new int[]{KeyEvent.VK_DOWN, 0, 0});
		actionManager.createAction(0, new int[]{KeyEvent.VK_LEFT, 0, 0});
		actionManager.createAction(0, new int[]{KeyEvent.VK_LEFT, 0, 0});
		actionManager.createAction(0, new int[]{KeyEvent.VK_LEFT, 0, 0});
		actionManager.createAction(1, new int[]{KeyEvent.VK_UP, 0, 0});
		
		save = new SaveState(actionManager, level);
	}
	
	@Test
	void saveEnds(){
		File file = new File(path + "saveTest.json");
		SaveStateManager.save(save, file);
		assertTrue(file.exists());
	}
	
	@Test
	void loadLevel() {
		File file = new File(path + "savedGame-test1.json");
		SaveState nuevoSave = SaveStateManager.load(file);
		LevelInterface nuevoLevel = nuevoSave.getLevel();
		LevelInterface correctLevel = save.getLevel();
		assertArrayEquals(correctLevel.getMap(), nuevoLevel.getMap());
		assertEquals(correctLevel.getPlayer().getxPos(), nuevoLevel.getPlayer().getxPos());
		assertEquals(correctLevel.getPlayer().getyPos(), nuevoLevel.getPlayer().getyPos());
		assertEquals(correctLevel.getPlayer().getScore().getLevelScore(), nuevoLevel.getPlayer().getScore().getLevelScore());
		assertEquals(correctLevel.getPlayer().getScore().getTotalScore(), nuevoLevel.getPlayer().getScore().getTotalScore());
	}
	
	@Test
	void loadActionManager() {
		File file = new File(path + "savedGame-test1.json");
		SaveState nuevoSave = SaveStateManager.load(file);
		assertEquals(5,nuevoSave.getActionManager().getActions().size());
		assertEquals(1,nuevoSave.getActionManager().getActions().peek().getActionCode());
		assertEquals(KeyEvent.VK_UP,nuevoSave.getActionManager().getActions().peek().getDirection());
	}
	
	@Test 
	void saveAndLoadLevel(){
		File file = new File(path + "saveAndLoadTest1.json");
		SaveStateManager.save(save, file);
		SaveState nuevoSave = SaveStateManager.load(file);
		LevelInterface nuevoLevel = nuevoSave.getLevel();
		LevelInterface correctLevel = save.getLevel();
		assertArrayEquals(correctLevel.getMap(), nuevoLevel.getMap());
		assertEquals(correctLevel.getPlayer().getxPos(), nuevoLevel.getPlayer().getxPos());
		assertEquals(correctLevel.getPlayer().getyPos(), nuevoLevel.getPlayer().getyPos());
		assertEquals(correctLevel.getPlayer().getScore().getLevelScore(), nuevoLevel.getPlayer().getScore().getLevelScore());
		assertEquals(correctLevel.getPlayer().getScore().getTotalScore(), nuevoLevel.getPlayer().getScore().getTotalScore());
	}
	
	@Test
	void saveAndLoadActionManager() {
		File file = new File(path + "saveAndLoadTest2.json");
		SaveStateManager.save(save, file);
		SaveState nuevoSave = SaveStateManager.load(file);
		assertEquals(5,nuevoSave.getActionManager().getActions().size());
		assertEquals(1,nuevoSave.getActionManager().getActions().peek().getActionCode());
		assertEquals(KeyEvent.VK_UP,nuevoSave.getActionManager().getActions().peek().getDirection());
	}
	
}
