import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;


public class Background {

	//attributes of background
	
	private int width, height; 		//width and height of background
	private Image img;				//background image
	
	//background constructor
	public Background(String fileName) {

		//ok to be blackboxed and not explain
		img = getImage(fileName);
	
	}

	private AffineTransform btx = AffineTransform.getTranslateInstance(0, 0);

	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, btx, null);
	}

	private void init(double a, double b) {
		btx.setToTranslation(a, b);
		btx.scale(1, 1);
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


