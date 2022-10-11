
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;

public class Log {

	private int lx , ly; 							//position of log
	private int lwidth = 208, lheight = 40; 		//width and height of log
	private Image limg;								//log image
	private int lvx, lvy;	
	
 

public Log(String fileName) {
	lx = 100;
	ly = 400;
	lvx = 0;
	lvy = 0;
	
	//ok to be blackboxed and not explain
	limg = getImage(fileName);
	init(lx, ly);
}

//log constructor
public Log(String fileName, int startLX, int startLY) {
	lx = startLX;
	ly = startLY;
	lvx = 0;
	lvy = 0;
	
	//ok to be blackboxed and not explain
	limg = getImage(fileName);
	init(lx, ly);
}

//logs can't move diagonally
public void setLvx(int lxVelocity) {
	lvx = lxVelocity;
	
	if(lvy!=0) {
		lvx = 0;
	}
}

public void setLvy(int lyVelocity) {
	lvy = lyVelocity;
	if(lvx!=0) {
		lvy = 0;
	}
}

//the logs reset to where they started when they reach the end of the screen
public void move() {
	ltx.translate(lvx,  lvy);
	
	if (ltx.getTranslateX() > 1600 && lvx > 0) {
		ltx.setToTranslation(-470, ly);
		lvx = 8;
	}
	
	if (ltx.getTranslateX() < -900 && lvx < 0) {
		ltx.setToTranslation(1500, ly);
		lvx = -8;
	}
}



private AffineTransform ltx = AffineTransform.getTranslateInstance(lx, ly);



//draw the affinetransform
public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(limg, ltx, null);
//	g2.draw(new Rectangle((int)(ltx.getTranslateX()), (int)(ltx.getTranslateY()), lwidth, lheight)); 		
}


private void init(double a, double b) {
	ltx.setToTranslation(a, b);
	ltx.scale(1, 1);
}

	//getters and setters for log variables	

	public int getLx() {
	return (int) ltx.getTranslateX();
}

public void setLx(int lx) {
	this.lx = (int) ltx.getTranslateX();
}

public int getLy() {
	return (int) ltx.getTranslateY();
}

public void setLy(int ly) {
	this.ly = (int) ltx.getTranslateY();
}


public int getLwidth() {
	return lwidth;
}

public void setLwidth(int lwidth) {
	this.lwidth = lwidth;
}

public int getLheight() {
	return lheight;
}

public void setLheight(int lheight) {
	this.lheight = lheight;
}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Car.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
}
