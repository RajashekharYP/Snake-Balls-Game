package com.raj.snakeGame;

import javax.swing.*;

public class Snake extends JFrame {

		Snake(){
			super("Snake Game"); //to set title or use setTitle() method
			
			//To get dimention & setPreferredSize from Board class we need to use add() method 
			add(new Board());
			
			pack();		// pack() method will internally call the setPreferredSize 
			
			// we can use setLocation(450, 200) method  or setLocationRelativeTo(null)
			setLocationRelativeTo(null);	
			
			setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
	public static void main(String[] args) {
		new Snake().setVisible(true);
	}

}
