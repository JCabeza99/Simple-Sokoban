package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private int rows;
    private int columns;
    private int[][] map;
    private int playerX;
    private int playerY;



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Image pared;
        Image suelo;
        Image player;
        Image caja;
        Image meta;
        Image boxInGoal;

        Toolkit t = Toolkit.getDefaultToolkit();

        pared = t.getImage("Photos" + File.separatorChar + "pared.jpg");
        suelo = t.getImage("Photos" + File.separatorChar + "suelo.jpg");
        player = t.getImage("Photos" + File.separatorChar + "player.png");
        caja = t.getImage("Photos" + File.separatorChar + "caja.png");
        meta = t.getImage("Photos" + File.separatorChar + "meta.png");
        boxInGoal = t.getImage("Photos" + File.separatorChar + "GoalBox.png");

        int cellSize = Math.min(getWidth() / columns, getHeight() / rows);
        int widthStart = (getWidth() - columns * cellSize) / 2;
        int heightStart = (getHeight() - rows * cellSize) / 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = widthStart + j * cellSize; // Coordenada x
                int y = heightStart + i * cellSize; // Coordenada y
    
                if (map[i][j] == 2) {
                    g2d.drawImage(pared, x, y, cellSize, cellSize, this);
                } else if (map[i][j] == 3) {
                    g2d.drawImage(caja, x, y, cellSize, cellSize, this);
                } else if (map[i][j] == 1) {
                    g2d.drawImage(suelo, x, y, cellSize, cellSize, this);
                    g2d.drawImage(meta, x, y, cellSize, cellSize, this);
                } else if (map[i][j] == 0) {
                    g2d.drawImage(suelo, x, y, cellSize, cellSize, this);
                } else {
                    g2d.drawImage(boxInGoal, x, y, cellSize, cellSize, this);
                }
            }
        }
    
        int playerWidth = widthStart + playerX * cellSize; // Coordenada x del jugador
        int playerHeight = heightStart + playerY * cellSize; // Coordenada y del jugador
        g2d.drawImage(player, playerWidth, playerHeight, cellSize, cellSize, this);
    }

    //getters y setters

    public GamePanel() {
        setVisible(true);
    }


	public void setRows(int rows) {
		this.rows = rows;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}


	public void setMap(int[][] map) {
		this.map = map;
	}


	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}


	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

}
