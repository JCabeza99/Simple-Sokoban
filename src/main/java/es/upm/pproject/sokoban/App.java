package es.upm.pproject.sokoban;

import java.awt.EventQueue;

import es.upm.pproject.sokoban.controller.Controller;
import es.upm.pproject.sokoban.controller.ControllerInterface;
import es.upm.pproject.sokoban.view.Gui;

public class App{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
				try {
					Gui frame = new Gui();
					ControllerInterface controller = new Controller(frame);
					frame.setController(controller);
					frame.updateView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		});
	}

}
