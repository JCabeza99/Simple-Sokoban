package es.upm.pproject.sokoban;

import java.awt.EventQueue;
import es.upm.pproject.sokoban.view.Gui;

public class App implements AppInterface {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
