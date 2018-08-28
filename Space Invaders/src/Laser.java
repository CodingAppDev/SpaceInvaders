

import java.awt.Graphics2D;
import java.awt.Image;

public class Laser extends GameObject {
	
	 private Image image = ImageLoader.loadCompatibleImage("sprites/alienStraightLaser2.png");
	 private int updateCounter = 0;
	 
	 //Keeps track of the direction of lasers fired by aliens and player
	 private int laserDirection;
	 
	public Laser(int x, int y, int w, int h, int direction) {
        super(x, y, w, h);
        laserDirection = direction;
	}
	
	public void update() {
		updateCounter++;
		
		if(updateCounter % 01 == 0)
            this.getBounds().y += 10*laserDirection;
		
		
		
	}
	
	public void render(Graphics2D g) {

		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width-30,
                    (int)getBounds().height-10,
                    null);       
	}
	
	public int getDirection() {
		return laserDirection;
	}
	
}