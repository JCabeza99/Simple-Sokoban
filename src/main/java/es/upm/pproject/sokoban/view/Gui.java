package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.*;
import es.upm.pproject.sokoban.model.*;

import static java.awt.Font.PLAIN;
import static java.awt.Font.BOLD;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame {

	ControllerInterface controller;

	int[][] map;
	int rows;
	int columns;
	int y;
	int x;
	String name;
	
	private JPanel contentPane;
	int levelScore = 0;
	int TotalScore = 0;

	String sSistemaOperativo = System.getProperty("os.name");

	public void paint(Graphics g) {

		if(false) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("GEORGIA", PLAIN, 40));
			g2d.drawString("FELICIDADES", 175, 300);
			g2d.drawString("HAS GANADO", 175, 350);
		}
		else {
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("GEORGIA", PLAIN, 40));
		g2d.drawString("SOKOBAN", 300, 100);
		g2d.setFont(new Font("GEORGIA", PLAIN, 20));
		g2d.drawString(name+ "", 200, 125);
		g2d.setFont(new Font("GEORGIA", BOLD, 15));
		g2d.drawString("Level Score:", 520, 400);
		g2d.drawString(levelScore + "", 520, 440);
		g2d.drawString("Total Score:", 520, 480);
		g2d.drawString(TotalScore + "", 520, 520);


		Image pared;
		Image suelo;
		Image player;
		Image caja;
		Image meta;
		Image boxInGoal;

		Toolkit t = Toolkit.getDefaultToolkit();
		if (sSistemaOperativo.equals("Linux")) { // Como las rutas relativas son distintas en windows yu linux hay que
													// comprobar en que SO estas
			pared = t.getImage("Photos/pared.jpg");
			suelo = t.getImage("Photos/suelo.jpg");
			player = t.getImage("Photos/player.png");
			caja = t.getImage("Photos/caja.png");
			meta = t.getImage("Photos/meta.png");
			boxInGoal = t.getImage("Photos/GoalBox.png");
		} else {
			pared = t.getImage("Photos\\pared.jpg");
			suelo = t.getImage("Photos\\suelo.jpg");
			player = t.getImage("Photos\\player.png");
			caja = t.getImage("Photos\\caja.png");
			meta = t.getImage("Photos\\meta.png");
			boxInGoal = t.getImage("Photos\\GoalBox.png");
		}

		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < columns; j++) {

				if (map[i][j] == 2) {
					g2d.drawImage(pared, 80 + j * 40, 140 + i * 40, 40, 40, this);
				} else if (map[i][j] == 3) {
					g2d.drawImage(caja, 80 + j * 40, 140 + i * 40, 40, 40, this);
				} else if (map[i][j] == 1) {
					g2d.drawImage(suelo, 80 + j * 40, 140 + i * 40, 40, 40, this);
					g2d.drawImage(meta, 80 + j * 40, 140 + i * 40, 40, 40, this);
				} else if (map[i][j] == 0) {
					g2d.drawImage(suelo, 80 + j * 40, 140 + i * 40, 40, 40, this);
				} else {
					g2d.drawImage(boxInGoal, 80 + j * 40, 140 + i * 40, 40, 40, this); // change to box in goal
				}
			}
		}
		g2d.drawImage(player, 80 + x * 40, 140 + y * 40, 40, 40, this);
		}

	}

	/**
	 * Create the frame.
	 */
	public Gui() {

		controller = new Controller();
		LevelInterface mapAux = controller.getLevel();
		map = mapAux.getMap();
		columns = map[0].length;
		rows = map.length;
		x = mapAux.getPlayer().getxPos();
		y = mapAux.getPlayer().getyPos();
		name = mapAux.getName();
		
		

		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double h = screenSize.getHeight();
		double w = screenSize.getWidth();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (w - 700) / 2, (int) (h - 700) / 2, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton lblZ = new JButton("Undo");
		lblZ.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.undo();
				updateView();
			}
		});
		lblZ.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblZ.setBounds(520, 100, 140, 40); // margen por izquierda margen por arriba ancho y alto
		lblZ.setHorizontalAlignment(JLabel.LEFT);

		JButton lblRG = new JButton("Restart game");
		lblRG.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.reStartGame();
				updateView();
			}
		});
		lblRG.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblRG.setBounds(520, 150, 140, 40);
		lblRG.setHorizontalAlignment(JLabel.LEFT);

		JButton lblRL = new JButton("Restart level");
		lblRL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.reStartLevel();
				updateView();
			}
		});
		lblRL.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblRL.setBounds(520, 200, 140, 40);
		lblRL.setHorizontalAlignment(JLabel.LEFT);

		JButton lblS = new JButton("Save");
		lblS.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblS.setText(lblS.getText() + ".");
				// manda señal a controler 7
			}
		});
		lblS.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblS.setBounds(520, 250, 140, 40);
		lblS.setHorizontalAlignment(JLabel.LEFT);

		JButton lblL = new JButton("Load");
		lblL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblL.setText(lblL.getText() + ".");
				// manda señal a controler 7
			}
		});
		lblL.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblL.setBounds(520, 300, 140, 40);
		lblL.setHorizontalAlignment(JLabel.LEFT);
		


		/*
		 * añadir elementos al panel
		 */
		contentPane.add(lblZ, BorderLayout.CENTER);
		contentPane.add(lblRG, BorderLayout.CENTER);
		contentPane.add(lblRL, BorderLayout.CENTER);
		contentPane.add(lblS, BorderLayout.CENTER);
		contentPane.add(lblL, BorderLayout.CENTER);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_UP) {
					System.out.println("Up Arrrow-Key is pressed!");
					controller.move(KeyEvent.VK_UP);
				}

				else if (keyCode == KeyEvent.VK_RIGHT) {
					System.out.println("Right Arrrow-Key is pressed!");
					controller.move(KeyEvent.VK_RIGHT);
				}

				else if (keyCode == KeyEvent.VK_DOWN) {
					System.out.println("Down Arrrow-Key is pressed!");
					controller.move(KeyEvent.VK_DOWN);
				}

				else if (keyCode == KeyEvent.VK_LEFT) {
					System.out.println("Left Arrrow-Key is pressed!");
					controller.move(KeyEvent.VK_LEFT);
				}
				updateView();
			}
		});

	}

	private void updateView() {
		LevelInterface level = controller.getLevel();
		PlayerInterface player = level.getPlayer();
		ScoreInterface score = player.getScore();
		map = level.getMap();
		name = level.getName();
		x = player.getxPos();
		y = player.getyPos();
		levelScore = score.getLevelScore();
		TotalScore = score.getTotalScore();
		columns = map[0].length;
		rows = map.length;
		if (level.getBoxesInGoal() == level.getnGoals()) {
			if (player.getLevel() == 3) {
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			controller.nextLevel();
			level = controller.getLevel();
			player = level.getPlayer();
			score = player.getScore();
			map = level.getMap();
			name = level.getName();
			x = player.getxPos();
			y = player.getyPos();
			columns = map[0].length;
			rows = map.length;
			levelScore = score.getLevelScore();
			TotalScore = score.getTotalScore();
		}
		update(getGraphics());
	}
	


//	public class PopupExample {
//
//
//	        JFrame frame = new JFrame("Popup Example");
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        frame.setSize(300, 200);
//	        frame.setLayout(null);
//
//	        JButton button = new JButton("Mostrar popup");
//	        button.setBounds(100, 50, 100, 30);
//	        frame.add(button);
//
//	        button.addActionListener(e -> {
//	            JOptionPane.showMessageDialog(frame, "¡Hola! Este es un popup.", "Popup", JOptionPane.INFORMATION_MESSAGE);
//	        });
//
//	        frame.setVisible(true);
//	    
//	}
}
