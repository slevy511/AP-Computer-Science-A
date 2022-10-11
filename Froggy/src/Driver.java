/*
 * Samuel Levy
 * Data Structures
 * p.1
 * Frogger
 * 
 * Feedback:
 * spicy game, actually pretty difficult
 * you can boost your score by holding the arrow keys after dying
 * instead of stopping at the walls you just reset
 * you can make your score negative by going backwards
 * 
 * game is great. the logs part is actually really hard. everything else is rlly good
 * difficult game, but has well made collisions. Score does not reset to 0 when you die tho
 * amazing game
 * 
 * this game works really well though its kinda hard I appreciate your completion I like the end screen
 * 
 * 
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {

	//variable declarations
	int screen_width = 900;
	int screen_height = 900;
	int lives = 5;
	int wins = 0;
	int alive = 0;
	int score = 0;
	
	//Froggy Declaration
	Froggy froggy;

	//Car Declarations
	Car [] carRow0 = new Car[5];
	Car [] carRow1 = new Car[6];
	Car [] carRow2 = new Car[4];
	Car [] carRow3 = new Car[6];
	Car [] carRow4 = new Car[5];
	
	//Log Declarations
	Log [] logRow0 = new Log[4];
	Log [] logRow1 = new Log[4];
	Log [] logRow2 = new Log[4];
	Log [] logRow3 = new Log[4];
	Log [] logRow4 = new Log[4];
	Log [] logRow5 = new Log[4];
	
	//Background Declaration
	Background bg;
	
	
	public void paint(Graphics g) {
		

		//When the game resets, the ending screen is cleared and the stats all reset
		if (alive == 2) {
			g.clearRect(0, 0, 900, 900);
			score = 0;
			alive = 0;
			wins = 0;
			froggy.reset();
		}
	
		//gameplay only runs when the player is alive
		if(alive==0) {																
		
		super.paintComponent(g);
		bg.paint(g);
		
		
		
		//draw the stats at the top of the screen
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString("Lives:"+lives, 30, 50);
		g.drawString("Wins:"+wins, 320, 50);
		g.drawString("Score:"+score, 560, 50);
		g.drawString("Press r to restart", 160, 100);
		
		
		//draw the froggy character
		g.setColor(Color.YELLOW);
		froggy.paint(g);
		
		
		//resets the frog when it goes out of bounds, and decrements the player's score as a penalty
		if(froggy.getFx() < 0 || froggy.getFx() >880) {
			froggy.reset();
			score -=20;
		}
		
		if(froggy.getFy() > 880) {
			froggy.reset();
			score +=10;
		}
		
		//the player wins when the frog makes it past all the obstacles. 
		//The frog then resets and the player's score increments
		if(froggy.getFy() < 180) {
			froggy.reset();
			wins++;
			score +=100;
		}
		
		
		//draw the cars
		for (int i = 0; i < carRow0.length; i++) {
			carRow0[i].paint(g);
			
			//if the frog collides with a car, the frog loses a life. 
			//Then the frog resets and the player's score decrements
			if(froggy.collided(carRow0[i].getCx(), carRow0[i].getCy(),
					carRow0[i].getCwidth(), carRow0[i].getCheight())) {
				
				froggy.reset();
				lives--;
				score-=50;
		
			}
					}
		
		
		//draw the cars
		for (int i = 0; i < carRow1.length; i++) {
			carRow1[i].paint(g);
			
			//if the frog collides with a car, the frog loses a life. 
			//Then the frog resets and the player's score decrements
			if(froggy.collided(carRow1[i].getCx(),carRow1[i].getCy(),
					carRow1[i].getCwidth(),carRow1[i].getCheight())) {
				
				froggy.reset();
				lives--;
				score-=50;
		
			}
		}
		
		//draw the cars
		for (int i = 0; i < carRow2.length; i++) {
			carRow2[i].paint(g);
			
			//if the frog collides with a car, the frog loses a life. 
			//Then the frog resets and the player's score decrements
			if(froggy.collided(carRow2[i].getCx(),carRow2[i].getCy(),
					carRow2[i].getCwidth(),carRow2[i].getCheight())) {
				
				froggy.reset();
				lives--;
				score-=50;

			}
		}
		
		//draw the cars
		for (int i = 0; i < carRow3.length; i++) {
			carRow3[i].paint(g);

			//if the frog collides with a car, the frog loses a life. 
			//Then the frog resets and the player's score decrements
			if(froggy.collided(carRow3[i].getCx(),carRow3[i].getCy(),
					carRow3[i].getCwidth(),carRow3[i].getCheight())) {
				
				froggy.reset();
				lives--;
				score-=50;
		
			}
		}
		
		//draw the cars
		for (int i = 0; i < carRow4.length; i++) {
			carRow4[i].paint(g);
			
			//if the frog collides with a car, the frog loses a life. 
			//Then the frog resets and the player's score decrements
			if(froggy.collided(carRow4[i].getCx(),carRow4[i].getCy(),
					carRow4[i].getCwidth(),carRow4[i].getCheight())) {

				froggy.reset();
				lives--;
				score-=50;
	
			}
					}
		//draw the logs
		for (int i = 0; i < logRow0.length; i++) {
			logRow0[i].paint(g);
		
			//if the frog jumps onto a log, it will move with the log. 
			//If the frog lands in the water, it loses a life and resets, and the player's score decrements
			if(froggy.getFy()> 180 && froggy.getFy() < 230) {
				if(froggy.getFy()%50 == 35) {
			
					if(froggy.collided(logRow0[0].getLx(),logRow0[0].getLy(),
							logRow0[0].getLwidth(),logRow0[0].getLheight()) || 
					   froggy.collided(logRow0[1].getLx(),logRow0[1].getLy(),
							   logRow0[1].getLwidth(),logRow0[1].getLheight()) ||  
					   froggy.collided(logRow0[2].getLx(),logRow0[2].getLy(),
							   logRow0[2].getLwidth(),logRow0[2].getLheight()) || 
					   froggy.collided(logRow0[3].getLx(),logRow0[3].getLy(),
							   logRow0[3].getLwidth(),logRow0[3].getLheight())) {
				
							froggy.setVx(-8);
				
					}else {
			
						froggy.reset();
						lives--;
						score-=50;
			
					}
				} 
			}}
		
		//draw the logs
		for (int i = 0; i < logRow1.length; i++) {
			logRow1[i].paint(g);
			
			//if the frog jumps onto a log, it will move with the log. 
			//If the frog lands in the water, 
			//player loses a life and resets, and the player's score decrements
			if(froggy.getFy()> 230 && froggy.getFy() < 280) {
				if(froggy.getFy()%50 == 35) {
			
					if(froggy.collided(logRow1[0].getLx(),logRow1[0].getLy(),
							logRow1[0].getLwidth(),logRow1[0].getLheight()) || 
					   froggy.collided(logRow1[1].getLx(),logRow1[1].getLy(),
							   logRow1[1].getLwidth(),logRow1[1].getLheight()) ||  
					   froggy.collided(logRow1[2].getLx(),logRow1[2].getLy(),
							   logRow1[2].getLwidth(),logRow1[2].getLheight()) || 
					   froggy.collided(logRow1[3].getLx(),logRow1[3].getLy(),
							   logRow1[3].getLwidth(),logRow1[3].getLheight())) {
				
							froggy.setVx(8);
				
					}else {
		
							froggy.reset();
							lives--;
							score-=50;
			
					}
				}}
		}
		
		//draw the logs
		for (int i = 0; i < logRow2.length; i++) {
			logRow2[i].paint(g);
			
			//if the frog jumps onto a log, it will move with the log. 
			//If the frog lands in the water, 
			//player loses a life and resets, and the player's score decrements
			if(froggy.getFy()> 280 && froggy.getFy() < 330) {
				if(froggy.getFy()%50 == 35) {
			
					if(froggy.collided(logRow2[0].getLx(),logRow2[0].getLy(),
							logRow2[0].getLwidth(),logRow2[0].getLheight()) || 
					   froggy.collided(logRow2[1].getLx(),logRow2[1].getLy(),
							   logRow2[1].getLwidth(),logRow2[1].getLheight()) ||  
					   froggy.collided(logRow2[2].getLx(),logRow2[2].getLy(),
							   logRow2[2].getLwidth(),logRow2[2].getLheight()) || 
					   froggy.collided(logRow2[3].getLx(),logRow2[3].getLy(),
							   logRow2[3].getLwidth(),logRow2[3].getLheight())) {
				
							froggy.setVx(-8);
					}else{
			
						froggy.reset();
						lives--;
						score-=50;
			
					}
				}}

		}
		
		//draw the logs
		for (int i = 0; i < logRow3.length; i++) {
			logRow3[i].paint(g);
			
			//if the frog jumps onto a log, it will move with the log. 
			//If the frog lands in the water, 
			//player loses a life and resets, and the player's score decrements
			if(froggy.getFy()> 330 && froggy.getFy() < 380) {
				if(froggy.getFy()%50 == 35) {
			
					if(froggy.collided(logRow3[0].getLx(),logRow3[0].getLy(),
							logRow3[0].getLwidth(),logRow3[0].getLheight()) || 
					   froggy.collided(logRow3[1].getLx(),logRow3[1].getLy(),
							   logRow3[1].getLwidth(),logRow3[1].getLheight()) ||  
					   froggy.collided(logRow3[2].getLx(),logRow3[2].getLy(),
							   logRow3[2].getLwidth(),logRow3[2].getLheight()) || 
					   froggy.collided(logRow3[3].getLx(),logRow3[3].getLy(),
							   logRow3[3].getLwidth(),logRow3[3].getLheight())) {
				
							froggy.setVx(8);
						
					}else{
					
						froggy.reset();
						lives--;
						score-=50;
					
					}
				}}
		}
		
		//draw the logs
		for (int i = 0; i < logRow4.length; i++) {
			logRow4[i].paint(g);
			
			//if the frog jumps onto a log, it will move with the log. 
			//If the frog lands in the water, 
			//player loses a life and resets, and the player's score decrements
			if(froggy.getFy()> 380 && froggy.getFy() < 430) {
				if(froggy.getFy()%50 == 35) {
			
					if(froggy.collided(logRow4[0].getLx(),logRow4[0].getLy(),
							logRow4[0].getLwidth(),logRow4[0].getLheight()) || 
					   froggy.collided(logRow4[1].getLx(),logRow4[1].getLy(),
							   logRow4[1].getLwidth(),logRow4[1].getLheight()) ||  
					   froggy.collided(logRow4[2].getLx(),logRow4[2].getLy(),
							   logRow4[2].getLwidth(),logRow4[2].getLheight()) || 
					   froggy.collided(logRow4[3].getLx(),logRow4[3].getLy(),
							   logRow4[3].getLwidth(),logRow4[3].getLheight())) {
				
							froggy.setVx(-8);
			
					}else{
			
						froggy.reset();
						lives--;
						score-=50;
			
					}
				}}
		
		}
		
		//draw the logs
		for (int i = 0; i < logRow5.length; i++) {
			logRow5[i].paint(g);
			
			//if the frog jumps onto a log, it will move with the log. 
			//If the frog lands in the water, 
			//player loses a life and resets, and the player's score decrements
			if(froggy.getFy()> 430 && froggy.getFy() < 480) {
				if(froggy.getFy()%50 == 35) {
			
					if(froggy.collided(logRow5[0].getLx(),logRow5[0].getLy(),
							logRow5[0].getLwidth(),logRow5[0].getLheight()) || 
					   froggy.collided(logRow5[1].getLx(),logRow5[1].getLy(),
							   logRow5[1].getLwidth(),logRow5[1].getLheight()) ||  
					   froggy.collided(logRow5[2].getLx(),logRow5[2].getLy(),
							   logRow5[2].getLwidth(),logRow5[2].getLheight()) || 
					   froggy.collided(logRow5[3].getLx(),logRow5[3].getLy(),
							   logRow5[3].getLwidth(),logRow5[3].getLheight())) {
				
							froggy.setVx(8);
				
					}else {
		
			froggy.reset();
			lives--;
			score-=50;
			
					}
				}}
		
		}
		
		//left-right movement is set to 0 when the frog's velocity is not being affected by a log
		if(froggy.getFy()<50 || froggy.getFy() > 480) {
			froggy.setVx(0);
		}
		
		froggy.paint(g);
		
		}
		
		//losing screen appears when frog runs out of lives
		if(lives<=0) {
			alive = 1;
		}
		
		
		if(alive == 1) {
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.fillRect(0,0,900,900);
			g.setColor(Color.GREEN);
			g.drawString("You lose!", 315, 400);
			g.drawString("Final Score: "+score, 200, 450);
			g.drawString("("+wins+" wins)", 330, 500);
			g.drawString("Press r to reset", 200, 550);
			
		}
		
		//winning screen appears when frog wins 5 times
		if (wins >=5) {
			alive = 3;
		}
		if(alive==3) {
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.fillRect(0,0,900,900);
			g.setColor(Color.BLUE);
			g.drawString("You win!", 315, 400);
			g.drawString("Final Score: "+score, 200, 450);
			g.drawString("Press r to reset", 200, 500);
		}

		
	}
	
	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	public void update() {
		
		//gameplay only runs when the player is alive
		if(alive==0) {
			
		//froggy movement
		froggy.move();
		
		//car movement, and car velocity is set
		for (int i = 0; i < carRow0.length; i++) {
			carRow0[i].move();
			carRow0[i].setCvx(-8);
			
		}
		for (int i = 0; i < carRow1.length; i++) {
			carRow1[i].move();
			carRow1[i].setCvx(-8);
		}
		for (int i = 0; i < carRow2.length; i++) {
			carRow2[i].move();
			carRow2[i].setCvx(8);
			
		}
		for (int i = 0; i < carRow3.length; i++) {
			carRow3[i].move();
			carRow3[i].setCvx(8);
		}
		for (int i = 0; i < carRow4.length; i++) {
			carRow4[i].move();
			carRow4[i].setCvx(8);
			
		}
		
		//log movement, and log velocity is set
		for (int i = 0; i < logRow0.length; i++) {
			logRow0[i].move();
			logRow0[i].setLvx(-8);
			
		}
		for (int i = 0; i < logRow1.length; i++) {
			logRow1[i].move();
			logRow1[i].setLvx(8);
		}
		for (int i = 0; i < logRow2.length; i++) {
			logRow2[i].move();
			logRow2[i].setLvx(-8);
			
		}
		for (int i = 0; i < logRow3.length; i++) {
			logRow3[i].move();
			logRow3[i].setLvx(8);
			
		}
		for (int i = 0; i < logRow4.length; i++) {
			logRow4[i].move();
			logRow4[i].setLvx(-8);
			
		}
		for (int i = 0; i < logRow5.length; i++) {
			logRow5[i].move();
			logRow5[i].setLvx(8);
			
		}
	
	
		}}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
		
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		
		f.setTitle("Frogger");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		
	if(alive==0) {
		
		//sprite instantiation 
		
		//Froggy instantiation
		froggy = new Froggy("froggy.png");

		
		//car instantiation
		for (int i = 0; i < carRow0.length; i++) {
			carRow0[i] = new Car("Car.png", i*700+10, 580);	
		}
		for (int i = 0; i < carRow1.length; i++) {
			carRow1[i] = new Car("Car.png", i*800-240, 680);	
		}
		for (int i = 0; i < carRow2.length; i++) {
			carRow2[i] = new Car("Car0.png", i*660-210, 530);	
		}
		for (int i = 0; i < carRow3.length; i++) {
			carRow3[i] = new Car("Car0.png", i*560+730, 630);	
		}
		for (int i = 0; i < carRow4.length; i++) {
			carRow4[i] = new Car("Car0.png", i*840+330, 730);	
		}
		
		//log instantiation
		for (int i = 0; i < logRow0.length; i++) {
			logRow0[i] = new Log("log.png", i*700+10, 180);	
		}
		for (int i = 0; i < logRow1.length; i++) {
			logRow1[i] = new Log("log.png", i*900+200, 230);	
		}
		for (int i = 0; i < logRow2.length; i++) {
			logRow2[i] = new Log("log.png", i*660-190, 280);	
		}
		for (int i = 0; i < logRow3.length; i++) {
			logRow3[i] = new Log("log.png", i*1000+300, 330);	
		}
		for (int i = 0; i < logRow4.length; i++) {
			logRow4[i] = new Log("log.png", i*660+690, 380);	
		}
		for (int i = 0; i < logRow5.length; i++) {
			logRow5[i] = new Log("log.png", i*660-190, 430);	
		}
		
		
		
		//player.addMouseListener(this);
		bg = new Background("bg.png");
		//do not add to frame, call paint on
		//these objects in paint method
	}
		
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {

	
		//froggy jumps when arrow keys are pressed. Score is also incremented slightly
		froggy.jump(50, e.getKeyCode());
		
		
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
		if(alive==0) {
		//frog velocity set to zero after it jumps and they player releases the key
		//score increases when jumping forward, decreases when jumping backward
		//score is not affected by jumping sideways	
		if(e.getKeyCode()==38) {
			froggy.setVy(0);
			score+=10;
		}
		if(e.getKeyCode()==40) {
			froggy.setVy(0);
			score-=10;
		}
		
		if(e.getKeyCode()==37) {
			froggy.setVx(0);
		}
		if(e.getKeyCode()==39) {
			froggy.setVx(0);
		}
		}
		//resets the stats when the reset button is pressed
		if(e.getKeyCode()==82 && (alive == 1 || alive ==3 || alive==0)) {
			
			alive = 2;
			lives = 5;
			wins = 0;
			score = 0;
			
		}
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
