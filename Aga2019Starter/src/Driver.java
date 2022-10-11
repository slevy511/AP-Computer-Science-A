




/*        C O M M E N T  S E C T I O N
 * 
 * - sepulvaball slightly breaks the game
 * - nice creative mechanics that are different than the original agario (like that balls grow bigger over time)
 * - controls work well
 * - little eeeeeeeeeeeeeeeez tho
 * like the lives aspect, but i think the player ball grows bigger at too fast a rate
 * Very smooth and player movement works well, however the space bar can make the ball invisible
 * good game. You should restrict how small you can get
 * the game is smooth and the lives are a great addition when playing
 * 
 */






import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
import java.util.*;
import java.lang.*;
import java.io.*;

 //Name of the class has to be "Main" only if the class is public. 
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner input = new Scanner(System.in);  //for user input
		System.out.println("Please enter a name");

		String name = input.next();
	}
}
*/

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	// size of jframe
	int screen_width = 900;
	int screen_height = 800;

	// variables for ball movement
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;

	// max number
	int max = 9001;

	// array for radii of each circle

	int[] ew = new int[max]; // arrays holding all the different radii of the enemies
	int[] ex = new int[max]; // x and y arrays for the enemy position
	int[] ey = new int[max];

	// int[] evx = new int [max];
	// int[] evy = new int [max];

	Color[] colors = new Color[max];

//player variables

	int pw = 30;
	int px = screen_width / 2 - pw / 2;
	int py = screen_height / 2 - pw / 2;
	


	// velocity for player
	double pvx = 0;
	double pvy = 0;

	// variable for determining velocity (I added these variables to simplify some
	// speed-changing processes)

	double pvxf = 10.0;
	double pvyf = 10.0;

	// declare a new set of arrays for food

	int[] foodx = new int[max];
	int[] foody = new int[max];

	// arrays for gridlines

	int[] gridx = new int[max / 50];
	int[] gridy = new int[max / 50];

	// variables for being eaten and running out of lives

	boolean lose = false; // determines whether you have been eaten - for lives counter

	int dead = 0; // determines what phase of the game we are in
					// 0 = normal gameplay
					// 1 = losing screen (when you run out of lives)
					// 2 = initiate resetting process (when you press restart)

	// stats counter variables

	int lives = 3;
	int foods = 0;
	int enemies = 0;

	// reading a val from a 1d array
	// System.out.print( x[0]); //reading value
	// x[0] = 5; //writing is similar to regular variables but now you have to
	// specify WHERE
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		

		if (dead == 0) { // gameplay graphics are only printed during the normal gameplay phase

			/*
			 * for(int l = -4500; l < 4501; l+=100 ) { g.setColor(Color.BLACK);
			 * g.drawLine(l, 4500, l, -4500); }
			 * 
			 * for(int w = -4500; w < 4501; w+=100 ) { g.setColor(Color.BLACK);
			 * g.drawLine(4500, w, -4500, w); }
			 */

			// write a loop setup to visit every possible index in x

			for (int i = 0; i < ex.length; i++) {

				// draw enemies
				g.setColor(colors[i]);
				g.fillOval(ex[i], ey[i], ew[i] * 2, ew[i] * 2);

				// draw food
				g.setColor(Color.blue);
				g.fillOval(foodx[i], foody[i], 10, 10);

			}

			// draw the player

			g.setColor(Color.RED);
			g.fillOval(px, py, pw, pw);

			// score printer

			g.setFont(new Font("Courier", 0, 50));
			g.drawString(String.valueOf("lives:" + lives), 340, 50);

			// losing sequence - screen flashes black and white, decrements the lives
			// counter,
			// and re-generates the food and enemies (without resetting the enemies' size)

			if (lose == true) {
				g.setColor(Color.black);
				g.fillRect(-1000, -1000, 5000, 5000);
				g.setColor(Color.white);
				g.fillRect(-1000, -1000, 5000, 5000);
				g.setColor(Color.black);
				g.fillRect(-1000, -1000, 5000, 5000);
				g.setColor(Color.white);
				lives--;

				for (int counter = 0; counter < max; counter++) {

					ex[counter] = (int) (Math.random() * 36001 - 18000); // reset enemy locations (but not size)
					ey[counter] = (int) (Math.random() * 36001 - 18000);

					foodx[counter] = (int) (Math.random() * 9001 - 4500); // reset food locations
					foody[counter] = (int) (Math.random() * 9001 - 4500);

					// re-generate random colors
					Color newColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
							(int) (Math.random() * 255));
					colors[counter] = newColor;

				}

				lose = false; // reset sequence finish
			}
		}

		// when you run out of lives, go to losing screen

		if (lives <= 0) {

			dead = 1;

		}

		if (dead == 1) {

			for (int w = 0; w < 2; w++) {

				if (w == 0) {
					g.setColor(Color.black); // black background
					g.fillRect(-4500, -4500, 9001, 9001);
				}
				if (w == 1) {
					g.setFont(new Font("Courier", 0, 50));
					g.setColor(Color.green);
					g.drawString(String.valueOf("YOU LOSE!"), 330, 200); // writes "You lose!"
					g.setFont(new Font("Courier", 0, 20));
					g.drawString(String.valueOf("YOU ATE " + enemies + " ENEMIES " + "AND " + foods + " FOOD"), 280,
							250); // writes gameplay stats
					g.drawString("PRESS R TO RESET", 360, 300); // writes "Press r to reset"

				}
			}
		}

		// reset gameplay sequence

		if (dead == 2) { // (Player has pressed "r" )
			g.clearRect(-4500, -4500, 9001, 9001); // clear black background

			// Reset enemy size
			for (int counter = 0; counter < max; counter++) {
				ew[counter] = 10;
			}

			dead = 0; // return to gameplay

		}

	}// end of paint method - put code above for anything dealing with drawing -

	Font font = new Font("Courier New", 1, 50);

	public void update() {

		// update food and enemies based on player "moving"

		if (dead == 0) { // only updates during gameplay
			
			
			for (int i = 0; i < foodx.length; i++) {

				foody[i] += pvy; // food updates
				foodx[i] += pvx;

				// distance formula, food: fx, fy player: px, py
				// sqrt (x1-x2)^2+(y1-y2)^2
				// math.sqrt()
				// math.pow(base,exp)

			double distanceF = Math.sqrt(((foodx[i] + 5 - px - pw / 2) * (foodx[i] + 5 - px - pw / 2))
						+ ((foody[i] + 5 - py - pw / 2) * (foody[i] + 5 - py - pw / 2))); // distance between player and
																							// food

			double sumF = 5 + pw / 2; // distance between food and player when they collide

				// if the player collides with food, "eat" the food (food "respawns"), increase
				// player width, and decrease player speed

				if (distanceF <= sumF) {

					foodx[i] = (int) (Math.random() * 9001) - 4500; // food "respawns"
					foody[i] = (int) (Math.random() * 9001) - 4500;
					pw += 1; // increase player width
					pvxf -= 0.05; // decrease player speed
					pvyf -= 0.05;
					foods++; // increment food counter

				}


			
			}
		
		
		
		
				// ENEMIES EATING FOOD
		
			
			double distanceEF = 0;
			double sumEF = 0;
			for (int i = 0; i < ex.length; i++) {
		/*		for (int j = 0; j < foody.length; j++) {
	
					distanceEF = Math.sqrt(((foodx[j] + 5 - ex[i] - ew[i]) * (foodx[j] + 5 - ex[i] - ew[i]))
							+ ((foody[j] + 5 - ey[i] - ew[i]) * (foody[j] + 5 - ey[i] - ew[i])));
					
					//sumEF = 5 + ew[i]; // 5= food radius
			
					sumEF = -100;
					if (distanceEF <= sumEF) {
			
						foodx[j] = (int) (Math.random() * 9001) - 4500;
						foody[j] = (int) (Math.random() * 9001) - 4500;
						ew[i] += 1;
						ex[i] -= 0.05;
						ey[i] -= 0.05;
					}
	
				}*/
				ex[i] += pvx; // enemy updates
				ey[i] += pvy;
	
				// Enemies move diagonally in a random direction
				if (i % 4 == 3) {
					ex[i] += 2;
					ey[i] += 2;
				}
				if (i % 4 == 2) {
					ex[i] -= 2;
					ey[i] += 2;
				}
				if (i % 4 == 1) {
					ex[i] += 2;
					ey[i] -= 2;
				}
				if (i % 4 == 0) {
					ex[i] -= 2;
					ey[i] -= 2;
				}
	
				// If an enemy hits the boundary, it is moved to the opposite boundary and
				// continues moving in the same direciton
				if (ex[i] > 18000) { // right boundary
					ex[i] -= 35999;
				}
				if (ey[i] > 18000) { // bottom boundary
					ey[i] -= 35999;
				}
				if (ex[i] < -18000) { // left boundary
					ex[i] += 35999;
				}
				if (ey[i] < -18000) { // top boundary
					ey[i] += 35999;
				}
	
				// enemies randomly get larger
	
				if (ex[i] % 250 == 0) {
					ew[i] += 1;
				}
	
				if (ey[i] % 250 == 0) {
					ew[i] += 1;
				}
				
				double distanceE = Math.sqrt(((ex[i] + ew[i] - px - pw / 2) * (ex[i] + ew[i] - px - pw / 2))
						+ ((ey[i] + ew[i] - py - pw / 2) * (ey[i] + ew[i] - py - pw / 2))); // distance between enemy and player
	
				double sumE = ew[i] + pw / 2; // distance between enemy and player when they collide
	
				// if the player collides with an enemy and is larger than the enemy, "eat" the
				// enemy, increase player width, and decrease player speed
	
				if (distanceE <= sumE && ew[i] * 2 < pw) {
	
					ex[i] = 99999; // respawn enemy offscreen
					ey[i] = 99999;
					pw += ew[i]; // player width increases
					pvxf -= 1 / pw; // player velocity decreases
					pvyf -= 1 / pw;
					ew[i] = 10; // enemy size resets
					enemies++; // increment enemy counter
	
					// if the player collides with an enemy that is larger than the player, the
					// enemy eats the player and increases in width
					// the player resets size and location and loses a life
					
				} else if (distanceE <= sumE && ew[i] * 2 > pw) {
	
					ew[i] += pw; // enemy increases in size
					pw = 30; // player size, and speed reset
					pvxf = 10;
					pvyf = 10;
					lose = true; // initiate losing sequence
				}
	
			}
			px = screen_width / 2 - pw / 2;
			py = screen_height / 2 - (pw / 2);

		}
			
		}
	// end of update method - put code above for any updates on variable

	// ==================code above ===========================

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
		f.setTitle("Agario");
		f.setSize(screen_width, screen_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);

		// this special "method" is callled the Constructor
		// initialized structures here!

		// this entire block generates random values for our
		// arrays which are the properties of the cells

		for (int counter = 0; counter < max; counter++) {

			// randomize enemies using constructor

			ew[counter] = 10; // all must be the same size to start
			ex[counter] = (int) (Math.random() * 36001 - 18000);
			ey[counter] = (int) (Math.random() * 36001 - 18000);

			// randomize food using constructor (which only runs once! ever)

			foodx[counter] = (int) (Math.random() * 9001 - 4500);
			foody[counter] = (int) (Math.random() * 9001 - 4500);

			// create colors from your color lab
			// students: generate r,g,b values for Color
			Color newColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
					(int) (Math.random() * 255));
			colors[counter] = newColor;

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

		switch (e.getKeyCode()) {

		case 32: // space bar pressed
			pw -= 20; // player width decreases
			pvxf += 100 / pw; // player velocity increases
			pvyf += 100 / pw;

			break;

		case 82: // r key is pressed (resets the game)
			lives = 3; // reset lives counter
			foods = 0; // reset foods counter
			enemies = 0; // reset enemies counter
			dead = 2; // moves to next phase of reset sequence
			break;

		case 38: // up key is pressed
			pvy = pvyf;
			up = true;
			break;

		case 40: // down key is pressed
			pvy = -pvyf;
			down = true;
			break;

		case 37: // left key is pressed
			pvx = pvxf;
			left = true;
			break;
		case 39: // right key is pressed
			pvx = -pvxf;
			right = true;
			break;

		}

		// speed does not change when moving diagonally

		if (up == true && left == true) {
			pvx = (int) Math.sqrt((pvxf * pvxf) / 2);
			pvy = (int) Math.sqrt((pvyf * pvyf) / 2);
		}

		if (down == true && left == true) {
			pvx = (int) Math.sqrt((pvxf * pvxf) / 2);
			pvy = -(int) Math.sqrt((pvyf * pvyf) / 2);
		}

		if (up == true && right == true) {
			pvx = -(int) Math.sqrt((pvxf * pvxf) / 2);
			pvy = (int) Math.sqrt((pvyf * pvyf) / 2);
		}

		if (down == true && right == true) {
			pvx = -(int) Math.sqrt((pvxf * pvxf) / 2);
			pvy = -(int) Math.sqrt((pvyf * pvyf) / 2);
		}

		// (these if-statements are not necessary but make player motion much smoother}

		if (up == true && left == false && right == false) {
			pvy = pvyf;
		}
		if (down == true && left == false && right == false) {
			pvy = -pvyf;
		}
		if (left == true && up == false && down == false) {
			pvx = pvxf;
		}
		if (right == true && up == false && down == false) {
			pvx = -pvxf;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {

		// player velocity resets to 0 when key is released

		case 38: // up key is released
			pvy = 0;
			up = false;
			break;
		case 40: // down key is released
			pvy = 0;
			down = false;
			break;
		case 37: // left key is released
			pvx = 0;
			left = false;
			break;
		case 39: // right key is released
			pvx = 0;
			right = false;
			break;

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
		// TODO Auto-generated method stub
		System.out.println(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub

	}
}

//for zoom out: multiply all x,y , and radius values by a certain amount
