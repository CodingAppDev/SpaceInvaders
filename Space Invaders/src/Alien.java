/*
    This class represents a basic alien in the game
 */

import java.awt.*;
import java.awt.geom.*;

public class Alien extends GameObject {

	private Image image = ImageLoader.loadCompatibleImage("sprites/alienA1.png");
	private int updateCounter = 0;
	private int laserCountdown = 240;
	private boolean positionCounter = false;

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
		//Fires the laser every 3 seconds

		if(laserCountdown >= 0) {
			laserCountdown--;
		}


	}

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
	public void render(Graphics2D g) {

		g.drawImage(image,
				(int)getBounds().x,
				(int)getBounds().y,
				(int)getBounds().width,
				(int)getBounds().height,
				null);

	}
}