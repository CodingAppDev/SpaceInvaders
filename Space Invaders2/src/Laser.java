

import java.awt.Graphics2D;
import java.awt.Image;

public class Laser extends GameObject {
	
	 private Image image = ImageLoader.loadCompatibleImage("sprites/alienStraightLaser2.png");
	 private Image image2 = ImageLoader.loadCompatibleImage("sprites/playerLaser.png");
	 private int updateCounter = 0;
	 
	 //Keeps track of the direction of lasers fired by aliens and player
	 private int laserDirection;
	 
	public Laser(int x, int y, int w, int h, int direction) {
        super(x, y, w, h);
        laserDirection = direction;
	}
	
	public void update() {
		updateCounter++;
		
		if(laserDirection == 1) {
		if(updateCounter % 01 == 0) 
            this.getBounds().y += 05*laserDirection;
		}
	
		if(laserDirection == -1) {
			if(updateCounter % 01 == 0) {
	            this.getBounds().y += 10*laserDirection;
			}
		}
	}
	
	public void render(Graphics2D g) {
		if(laserDirection == 1) {
		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width-30,
                    (int)getBounds().height-10,
                    null);       
		}
		
		if(laserDirection == -1) {
			g.drawImage(image2,
	                    (int)getBounds().x,
	                    (int)getBounds().y,
	                    (int)getBounds().width-30,
	                    (int)getBounds().height-10,
	                    null);       
		}
	}
	
	
	public int getDirection() {
		return laserDirection;
	}
	
}