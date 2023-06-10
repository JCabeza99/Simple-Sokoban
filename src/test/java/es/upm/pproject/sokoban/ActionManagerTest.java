package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import es.upm.pproject.sokoban.model.*;
import es.upm.pproject.sokoban.controller.*;
import org.junit.jupiter.api.BeforeEach;

public class ActionManagerTest {

	ControllerInterface controller;
	ActionManager actionManager;
	Stack<ActionInterface> pilaAcciones;
	
	@BeforeEach
	public void init() {
		controller = new Controller();
		actionManager = controller.getActionManager();
		pilaAcciones = actionManager.getActions();
	}
	
	@Test
	public void createActionPlayerOnly() {
		
		int[] data = new int[3];
		
		data[0] = KeyEvent.VK_UP;
		
		actionManager.createAction(0, data);
		
		assertEquals(pilaAcciones.size(), 1);
		assertEquals(pilaAcciones.peek().getActionCode(), 0);
	}
	
	@Test
	public void actionTestCreateActionPlayerAndBoxMoved() {
		
		int[] data = new int[3];
		
		data[0] = KeyEvent.VK_UP;
		data[1] = 0;
		data[2] = 0;
		
		actionManager.createAction(1, data);
		
		assertEquals(1, pilaAcciones.size());
		assertEquals(1, pilaAcciones.peek().getActionCode());
	}
	
	@Test
	public void actionTestStackFirstMove(){
		PlayerInterface jugador = controller.getLevel().getPlayer();
		int x = jugador.getxPos();
		int y = jugador.getyPos();
		
		controller.move(KeyEvent.VK_UP);
		
		
		assertEquals(1, pilaAcciones.size());
		assertEquals(0, pilaAcciones.peek().getActionCode());
		
		controller.undo();
		
		assertEquals(0, pilaAcciones.size());
	}
	
	@Test
	public void actionTestWorksFirstMove(){
		PlayerInterface jugador = controller.getLevel().getPlayer();
		int x = jugador.getxPos();
		int y = jugador.getyPos();
		
		controller.move(KeyEvent.VK_UP);
		
		controller.undo();
		
		assertEquals(x, jugador.getxPos());
		assertEquals(y, jugador.getyPos());
	}
	
	@Test
	public void actionTestNoMove() {
		controller.move(KeyEvent.VK_LEFT);
		
		assertEquals(0, pilaAcciones.size());
	}
		
}
