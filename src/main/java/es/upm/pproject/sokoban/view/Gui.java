package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.*;
import es.upm.pproject.sokoban.model.*;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Gui extends JFrame {

    private JPanel contentPane;
    private TitlePanel titleContainer;
    private GamePanel gameContainer;
    private ButtonPanel buttonContainer;

    private ControllerInterface controller;

    public Gui() {
        contentPane = new JPanel();
        titleContainer = new TitlePanel();
        buttonContainer = new ButtonPanel();
        gameContainer = new GamePanel();

        setTitle("Sokoban");

        // Screen Dimension retrieval
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = 640;
        int frameHeight = 480;
        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // set window location
        setBounds(x, y, frameWidth, frameHeight);

        // Aux container to handle Game and Button containers
        JPanel viewContainer = new JPanel();
        viewContainer.setLayout(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.weightx = 0.8;
        gbc1.weighty = 0.8;
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.anchor = GridBagConstraints.CENTER;
        viewContainer.add(gameContainer, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.weightx = 0.25;
        gbc2.weighty = 0.8;
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.anchor = GridBagConstraints.CENTER;
        viewContainer.add(buttonContainer, gbc2);

        // ContentPane properties set up
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.add(titleContainer);
        contentPane.add(viewContainer);

        // Add KeyListener to the JFrame
        addKeyListener(new KeyAdapter() {
        	@Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    controller.move(KeyEvent.VK_UP);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    controller.move(KeyEvent.VK_RIGHT);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    controller.move(KeyEvent.VK_DOWN);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    controller.move(KeyEvent.VK_LEFT);
                } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                    controller.undo();
                }
            }
        });

        // Set the JFrame focusable to receive key events
        setFocusable(true);
        requestFocusInWindow();

    }

    public void setController(ControllerInterface controller) {
        this.controller = controller;
        buttonContainer.setController(controller);
    }

    public void updateView() {
        LevelInterface level = controller.getLevel();
        PlayerInterface player = level.getPlayer();
        ScoreInterface score = player.getScore();
        int[][] map = level.getMap();
        titleContainer.setLevel(level.getName());
        gameContainer.setMap(map);
        gameContainer.setPlayerX(player.getxPos());
        gameContainer.setPlayerY(player.getyPos());
        gameContainer.setColumns(map[0].length);
        gameContainer.setRows(map.length);
        buttonContainer.setLevelScore(score);
        gameContainer.update(gameContainer.getGraphics());
        if (level.getBoxesInGoal() == level.getnGoals()) {
            controller.nextLevel();
        }
        requestFocusInWindow();
    }

    public void createDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
        requestFocusInWindow();
    }

    public void endGame() {
        JOptionPane.showMessageDialog(this, "Congratulations! You have beaten sokoban. \n Your total score is : "
                + controller.getLevel().getPlayer().getScore().getTotalScore());
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }


}
