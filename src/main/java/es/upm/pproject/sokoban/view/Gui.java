package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.*;
import es.upm.pproject.sokoban.view.*;
import es.upm.pproject.sokoban.model.*;

import static java.awt.Font.PLAIN;
import static java.awt.Font.BOLD;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame {

	Controller controller;
	
    int[][] nivel  = {	{'+','+','+','+','+','+','+'},
    					{'+','w','.','.','.','.','+'},
    					{'+','.','.','.','.','.','+'},
    					{'+','.','*','#','.','.','+'},
    					{'+','.','.','.','.','.','+'},
    					{'+','.','.','.','.','.','+'},
    					{'+','.','.','.','.','.','+'},
    					{'+','.','.','.','.','.','+'},
    					{'+','.','.','.','.','.','+'},
    					{'+','+','+','+','+','+','+'}};
    int filas = nivel.length;
    int columnas = nivel[0].length;
	int fil = 4;
	int col = 4;
	
	char muro = '+';

	private JPanel contentPane;
	int movimientosLvl = 0;
	int movimientosAnt = 0;
	
    String sSistemaOperativo = System.getProperty("os.name");

    public void paint (Graphics g)
    {

        for(int i = 0; i<filas;i++) {
        	for (int j = 0; j < columnas; j++) {
    			if(nivel[i][j]=='w') {
    		        fil = i;
    		        col = j;
    			}
    		}
        }
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("GEORGIA",PLAIN,40));
        g2d.drawString("SOKOBAN", 300, 100);
        g2d.setFont(new Font("GEORGIA",BOLD,15));
        g2d.drawString("Movimientos:", 520, 400);
        g2d.drawString(movimientosLvl + "", 520, 440);
        g2d.drawString("Movimientos totales", 520, 480);
        g2d.drawString(movimientosLvl + movimientosAnt + "", 520, 520);
        
        Image pared;
        Image suelo;
        Image player;
        Image caja;
        Image meta;
        

        Toolkit t = Toolkit.getDefaultToolkit ();
        if(sSistemaOperativo.equals("Linux")) { //Como las rutas relativas son distintas en windows yu linux hay que comprobar en que SO estas
        	pared = t.getImage ("Photos/pared.jpg");
    		suelo = t.getImage ("Photos/suelo.jpg");
    		player = t.getImage ("Photos/player.png");
    		caja = t.getImage ("Photos/caja.png");
    		meta = t.getImage ("Photos/meta.png");
        }
        else {
        	pared = t.getImage ("Photos\\pared.jpg");
    		suelo = t.getImage ("Photos\\suelo.jpg");
    		player = t.getImage ("Photos\\player.png");
    		caja = t.getImage ("Photos\\caja.png");
    		meta = t.getImage ("Photos\\meta.png");

        }

//		Image pared = t.getImage ("fotos/pared.jpg");
//		Image suelo = t.getImage ("fotos/suelo.jpg");
//		Image player = t.getImage ("fotos/player.png");
//		Image caja = t.getImage ("fotos/caja.png");
        for(int i = 0; i<filas;i++) {

        	for (int j = 0; j < columnas; j++) {

				if(nivel[i][j]=='+') {
			        g2d.drawImage (pared, 80 + j*40, 140 + i*40, 40, 40, this);
				}
				else if(nivel[i][j]=='#'){
					g2d.drawImage (caja, 80 + j*40, 140 + i*40, 40, 40, this);
				}
				else if(nivel[i][j]=='*'){
					g2d.drawImage (suelo, 80 + j*40, 140 + i*40, 40, 40, this);
					g2d.drawImage (meta, 80 + j*40, 140 + i*40, 40, 40, this);
				}
				else {
					g2d.drawImage (suelo, 80 + j*40, 140 + i*40, 40, 40, this);
				}
			}
        }
        g2d.drawImage (player,80 + col*40, 140 + fil*40, 40, 40, this);

    }
    

    
    


	/**
	 * Create the frame.
	 */
	public Gui() {
		
		controller = new Controller("patata");
		
		 Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    double h= screenSize.getHeight();
	    double w= screenSize.getWidth();
		    


		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int)(w-700)/2, (int)(h-700)/2, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
		
	    
	    JLabel lblZ = new JLabel("Undo");
	    lblZ.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		lblZ.setText(lblZ.getText() + ".");
	          	//manda señal a controler 4

			}
		});
	    lblZ.setFont(new Font("Georgia", Font.PLAIN, 14));
	    lblZ.setBounds(520, 140, 100, 40);					//margen por izquierda margen por arriba ancho y alto		
	    lblZ.setHorizontalAlignment(JLabel.LEFT);
	    
	    
	    
	    JLabel lblRG = new JLabel("Restart game");
	    lblRG.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
				lblRG.setText(lblRG.getText() + ".");
	          	//manda señal a controler 5
			}
		});
	    lblRG.setFont(new Font("Georgia", Font.PLAIN, 14));
	    lblRG.setBounds(520, 180, 100, 40);					
	    lblRG.setHorizontalAlignment(JLabel.LEFT);
	    
	    
	    
	    JLabel lblRL = new JLabel("Restart level");
	    lblRL.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
				lblRL.setText(lblRL.getText() + ".");
	          	//manda señal a controler 6
			}
		});
	    lblRL.setFont(new Font("Georgia", Font.PLAIN, 14));
	    lblRL.setBounds(520, 220, 100, 40);					
	    lblRL.setHorizontalAlignment(JLabel.LEFT);
	    
	    
	    
	    JLabel lblS = new JLabel("Save");
	    lblS.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		lblS.setText(lblS.getText() + ".");
	          	//manda señal a controler 7
			}
		});
	    lblS.setFont(new Font("Georgia", Font.PLAIN, 14));
	    lblS.setBounds(520, 260, 100, 40);					
	    lblS.setHorizontalAlignment(JLabel.LEFT);
	    
	    
	    JLabel lblL = new JLabel("Load");
	    lblL.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		lblL.setText(lblL.getText() + ".");
	          	//manda señal a controler 7
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
	          Level level = controller.getLevel();
			  Player player = level.getPlayer();
			  Score score = player.getScore();
	          nivel = level.getMap();
	          col = player.getxPos();
	          fil = player.getyPos();
			  movimientosLvl = score.getLevelScore();
			  movimientosAnt = score.getTotalScore();
	          update(getGraphics());
	        }
	      });
	    

	    
	}
	

}
