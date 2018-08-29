/*
    This class represents a basic alien in the game
 */

import java.awt.*;
import java.awt.geom.*;

public class Alien extends GameObject {

	private Image image = ImageLoader.loadCompatibleImage("sprites/alienB1.png");
	private Image imageTwo = ImageLoader.loadCompatibleImage("sprites/alienB2.png");
	private int updateCounter = 0;
	private int laserCountdown = 240;
	private boolean positionCounter = false;
	private int currentImage = 1;

	public Alien(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void update() {

		//keep track of how many times this Alien has been updated
		updateCounter++;

		//every 120th update, move the bounds of the alien half it's width to the right
		if(updateCounter % 05 == 0) {

			//Make the alien bounce off the screen
			if(getBounds().x >= 760) {
				positionCounter = true;
			}
			if(positionCounter == true) {
				getBounds().x -= getBounds().width/2;
			}

			if(getBounds().x <= 0) {
				positionCounter = false;
			}
			if(positionCounter == false) {
				getBounds().x += getBounds().width/2;
			}
		}
		
		//Changes value of currentImage to draw different image
		if(updateCounter % 05 == 0) {
			if(currentImage == 1) {
				currentImage = 0;
		
				
			}
			if(currentImage == 0) {
				currentImage = 1;
			}
		}
		
		//Fires the laser every 3 seconds
		if(laserCountdown >= 0) {
			laserCountdown--;
		}


	}
		//Class that allows the alien to shoot a laser
	public Laser shoot() {
		int xPos = (int)this.getBounds().x;
		int yPos = (int)this.getBounds().y;
		int width = (int)this.getBounds().width;
		int height = (int)this.getBounds().height;
		System.out.println(laserCountdown);
		if(laserCountdown <= 0) {
			laserCountdown = 240;
			Laser laser = new Laser(xPos-10, yPos-10, width, height, 1);
			System.out.println("Worked");
			return laser;
		}

		return null;
	}


	//draw the image represented by the alien
	//Changes what is rendered depending on value of currentImage
	public void render(Graphics2D g) {
		if(currentImage == 1){
		g.drawImage(image,
				(int)getBounds().x,
				(int)getBounds().y,
				(int)getBounds().width,
				(int)getBounds().height,
				null);
		}
		
		if(currentImage == 0){
			g.drawImage(imageTwo,
					(int)getBounds().x,
					(int)getBounds().y,
					(int)getBounds().width,
					(int)getBounds().height,
					null);
			}

	}
	
	public int getPoints() {
		return 10;
	}
	
}