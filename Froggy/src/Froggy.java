import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;

/* class ro represent a Frog object in a game of Frogger */
public class Froggy {

	//attributes of a Frog
	
	private int x , y; 				//position of Froggy;
	private boolean alive;			//is the froggy dead or alive
	private int width, height; 		//width and height of froggy
	private Image img;				//frog image
	private int vx, vy;				//frog velocities
	
	//write the constructor for Froggy which takes in a 
	//String fileName that will be used for the image setup
	
	
	/*public nameOfClass(/* any parameters for constructor ){
			//assignment statements for attributes (instance variables)
	}
	
	*/
	
	public Froggy(String fileName) {
		x = 400;
		y = 835;
		vx = 0;
		vy = 0;
		width = 18;
		height = 15;
		
		//ok to be blackboxed and not explain
		img = getImage(fileName);
		init(x, y);
	
	}
	
	//Froggy constructor
	public Froggy(String fileName, int startX, int startY) {
		x = startX;
		y = startY;
		vx = 0;
		vy = 0;
		
		//ok to be blackboxed and not explain
		img = getImage(fileName);
		init(x, y);
	
	}
	
	//when reset() is called, the frog resets to its starting position
	public void reset () {
		tx.setToTranslation(400, 835);
		
	}
	
	//allows the frog to jump when keys are pressed
	public void jump (int dist, int keyCode) {
		switch(keyCode) {
		case 37:
			tx.setToTranslation(tx.getTranslateX() - dist,tx.getTranslateY());
			break;
		case 38:
			tx.setToTranslation(tx.getTranslateX(),tx.getTranslateY() - dist);
			break;
		case 39:
			tx.setToTranslation(tx.getTranslateX() + dist,tx.getTranslateY());
			break;
		case 40:
			tx.setToTranslation(tx.getTranslateX(),tx.getTranslateY() + dist);
			break;
			
		}
	}
	
	//getters and setters for frog variables
	
	public int getVx() {
		return vx;
	}
	
	public int getVy() {
		return vy;
	}
	
	
	public void setVx(int xVelocity) {
		vx = xVelocity;
		
		if(vy!=0) { //turn off moving diagonally
			vx = 0;
		}
	}
	
	public void setVy(int yVelocity) {
		vy = yVelocity;
		if(vx!=0) { //turn off moving diagonally
			vy = 0;
		}
	}
	
	//checks if obstacles have collided with the frog
	public boolean collided(int ox, int oy, int ow, int oh) {
		Rectangle obs =  new Rectangle(ox, oy, ow, oh); 		//from param
		Rectangle froggy =  new Rectangle((int) tx.getTranslateX()+5,(int) tx.getTranslateY()+5, width, height); //from attributes

		return obs.intersects(froggy);
	}
	
	//getters and setters for frog variables
	
	public int getX() {
		return x;
	}
	
	public int getFx() {
		return (int) tx.getTranslateX();
	}
	
	public int getFy() {
		return (int) tx.getTranslateY();
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move() {
		tx.translate(vx,  vy); 	//move the image according to vx, vy
		x+=vx;					//update the object x, y
		y+=vy;
		
		//change based on direction of your vx
		//for teleporting after going off-screen
		if(x<0) {
			x=900;
			tx.setToTranslation(x, y);
		}
	}

	
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	//	g2.draw(new Rectangle((int)(tx.getTranslateX())+5, (int)(tx.getTranslateY())+5, width, height)); 
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Froggy.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	
}


