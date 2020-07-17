package com.raj.snakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	private Image apple;
	private Image dot;
	private Image head;
	private int dots, DOT_Size=10;
	private int apple_x ;
	private int apple_y;
	
	private final int B_WIDTH = 300;
	private final int B_HEIGHT = 300;
	private final int ALL_DOTS = 900;
	private final int x[] = new int [ALL_DOTS];
	private final int y[] = new int [ALL_DOTS];
	
    private Timer timer;
    private boolean rightDirection = true ;
    private boolean leftDirection = false ;
    private boolean UpDirection = false ;
    private boolean downDirection = false ;
    private boolean inGame = true;
    
	Board(){
		initBoard();
	}
	
	private void initBoard() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,300));
		
		LoadImages();
        initGame();
        
        //key pickUp
       addKeyListener(new TAdapter());
        
        setFocusable(true);

	}
	private void LoadImages(){

		ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("image/apple.png"));
		apple = I1.getImage();
		ImageIcon I2 = new ImageIcon(ClassLoader.getSystemResource("image/dot.png"));
		dot = I2.getImage();
		ImageIcon I3 = new ImageIcon(ClassLoader.getSystemResource("image/head.png"));
		head = I3.getImage();
	}
	
	private void initGame() {
		dots = 3;
		for(int i=0 ; i< dots; i++) {
			x[i] = 50 - i * 10;
			y[i] = 50;
	
		}
		locateApple();
		timer = new Timer(240, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 checkApple();
		 move();
		 checkCollision();
		 repaint();

		
	}
	


	private void checkApple() {
		if((x[0]== apple_x) && (y[0]== apple_y)) {
			dots++;
			locateApple();
		}
	}

	private void move() {
		for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
		
		if(leftDirection) {
			x[0] -= DOT_Size ;
		}
		if(rightDirection) {
			x[0] += DOT_Size ;
		}
		if(UpDirection) {
			y[0] -= DOT_Size ;
		}
		if(downDirection) {
			y[0] += DOT_Size ;
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	private void draw(Graphics g){
		if(inGame) {
		g.drawImage(apple, apple_x, apple_y, this); 
		
		for(int z=0; z<dots ; z++) {
			if(z ==0) {
				g.drawImage(head, x[z], y[z], this); 
			}
			else {
				g.drawImage(dot, x[z], y[z], this); 
			}
		}
        Toolkit.getDefaultToolkit().sync();

		}
		else {
			gameOver(g);
		}
		}
	
	private void gameOver(Graphics g) {
		
		String msg = "Game Over...!";
		String msg2 = " Thanks For Playing..";
		
		g.setColor(Color.white);
		g.drawString(msg, 110, 130);
		g.drawString(msg2, 100, 150);
	}

	private void locateApple() {
		int r = (int) (Math.random() * 29 );
		apple_x =  ((r * DOT_Size));
		r = (int) (Math.random() * 29 );
		apple_y =  ((r * DOT_Size));

		
	}
	
	private void checkCollision() {
        for (int z = dots; z > 0; z--) {
        	if ((z > 4) && (x[0]== x[z]) && (y[0]==y[z])) {
   			 inGame = false;
        	}
        }
		if((x[0] > B_WIDTH) || (y[0] >= B_WIDTH) || (y[0] < 0) || (x[0] <0)) {
			 inGame = false;
		}	
		
	}
	
	private class TAdapter extends KeyAdapter{
		
	    public void keyPressed(KeyEvent e) {
	    	int key = e.getKeyCode();
	    	if((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
	    		leftDirection = true;
	    		UpDirection = false;
	    		downDirection = false;
	    	}
	    	if((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
	    		rightDirection = true;
	    		UpDirection = false;
	    		downDirection = false;
	    	}
	    	if((key == KeyEvent.VK_UP) && (!downDirection)) {
	    		leftDirection = false;
	    		UpDirection = true;
	    		rightDirection = false;
	    	}
	    	if((key == KeyEvent.VK_DOWN) && (!UpDirection)) {
	    		leftDirection = false;
	    		rightDirection = false;
	    		downDirection = true;
	    	}
	    }

	}

}
