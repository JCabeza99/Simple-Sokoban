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

	Controller controller;

	int[][] map;
	int rows;
	int columns;
	int y;
	int x;

	private JPanel contentPane;
	int levelScore = 0;
	int TotalScore = 0;

	String sSistemaOperativo = System.getProperty("os.name");

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("GEORGIA", PLAIN, 40));
		g2d.drawString("SOKOBAN", 300, 100);
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
				else {
					g2d.drawImage (caja, 80 + j*40, 140 + i*40, 40, 40, this); //change to box in goal
				}
			}
		}
		g2d.drawImage(player, 80 + x * 40, 140 + y * 40, 40, 40, this);

	}

	/**
	 * Create the frame.
	 */
	public Gui() {

		controller = new Controller();
		Level mapAux = controller.getLevel();
		map = mapAux.getMap();
		columns = map[0].length;
		rows = map.length;
		x = mapAux.getPlayer().getxPos();
		y = mapAux.getPlayer().getyPos();

		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double h = screenSize.getHeight();
		double w = screenSize.getWidth();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (w - 700) / 2, (int) (h - 700) / 2, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblZ = new JLabel("Undo");
		lblZ.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblZ.setText(lblZ.getText() + ".");
				// manda señal a controler 4

			}
		});
		lblZ.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblZ.setBounds(520, 140, 100, 40); // margen por izquierda margen por arriba ancho y alto
		lblZ.setHorizontalAlignment(JLabel.LEFT);

		JLabel lblRG = new JLabel("Restart game");
		lblRG.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRG.setText(lblRG.getText() + ".");
				// manda señal a controler 5
			}
		});
		lblRG.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblRG.setBounds(520, 180, 100, 40);
		lblRG.setHorizontalAlignment(JLabel.LEFT);

		JLabel lblRL = new JLabel("Restart level");
		lblRL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRL.setText(lblRL.getText() + ".");
				// manda señal a controler 6
			}
		});
		lblRL.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblRL.setBounds(520, 220, 100, 40);
		lblRL.setHorizontalAlignment(JLabel.LEFT);

		JLabel lblS = new JLabel("Save");
		lblS.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblS.setText(lblS.getText() + ".");
				// manda señal a controler 7
			}
		});
		lblS.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblS.setBounds(520, 260, 100, 40);
		lblS.setHorizontalAlignment(JLabel.LEFT);

		JLabel lblL = new JLabel("Load");
		lblL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblL.setText(lblL.getText() + ".");
				// manda señal a controler 7
			}
		});
		lblL.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblL.setBounds(520, 300, 100, 40);
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
		Level level = controller.getLevel();
		Player player = level.getPlayer();
		Score score = player.getScore();
		map = level.getMap();
		x = player.getxPos();
		y = player.getyPos();
		levelScore = score.getLevelScore();
		TotalScore = score.getTotalScore();
		if (level.getBoxesInGoal() == level.getnGoals()) {
			if (player.getLevel() == 3) {
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			controller.nextLevel();
			level = controller.getLevel();
			player = level.getPlayer();
			score = player.getScore();
			map = level.getMap();
			x = player.getxPos();
			y = player.getyPos();
			columns = map[0].length;
			rows = map.length;
			levelScore = score.getLevelScore();
			TotalScore = score.getTotalScore();
		}
		update(getGraphics());
	}
}
