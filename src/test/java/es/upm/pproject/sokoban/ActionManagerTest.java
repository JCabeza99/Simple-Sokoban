package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;


import org.junit.jupiter.api.Test;
import es.upm.pproject.sokoban.model.*;
import org.junit.jupiter.api.BeforeEach;

class ActionManagerTest {

	private ActionManager actionManager;

	@BeforeEach
	void init() {
		actionManager = new ActionManager();
	}

	@Test
	void createActionPlayerOnly() {

		int[] data = new int[3];

		data[0] = KeyEvent.VK_UP;

		actionManager.createAction(0, data);

		assertEquals(1,actionManager.getActions().size());
		assertEquals(0,actionManager.getActions().peek().getActionCode());
	}

	@Test
	void actionTestCreateActionPlayerAndBoxMoved() {

		int[] data = new int[3];

		data[0] = KeyEvent.VK_UP;
		data[1] = 0;
		data[2] = 0;

		actionManager.createAction(1, data);

		assertEquals(1, actionManager.getActions().size());
		assertEquals(1, actionManager.getActions().peek().getActionCode());
	}

	@Test
	void actionTestUndo() {

		int[] data = new int[3];

		data[0] = KeyEvent.VK_UP;
		data[1] = 0;
		data[2] = 0;

		actionManager.createAction(1, data);

		assertEquals(1, actionManager.getActions().size());
		assertEquals(1, actionManager.getActions().peek().getActionCode());

		actionManager.undo();

		assertEquals(0, actionManager.getActions().size());
	}

	@Test
	void actionTestUndoMultipleMoves() {
		int[] data = new int[3];

		data[0] = KeyEvent.VK_UP;
		data[1] = 0;
		data[2] = 0;

		actionManager.createAction(1, data);

		data[0] = KeyEvent.VK_LEFT;
		data[1] = 0;
		data[2] = 0;

		actionManager.createAction(0, data);

		assertEquals(2, actionManager.getActions().size());
		assertEquals(0, actionManager.getActions().peek().getActionCode());

		actionManager.undo();

		assertEquals(1, actionManager.getActions().size());
		assertEquals(1, actionManager.getActions().peek().getActionCode());

		actionManager.undo();

		assertEquals(0, actionManager.getActions().size());
	}

	@Test
	void actionTestNoMove() {
		assertEquals(0, actionManager.getActions().size());
	}

}
