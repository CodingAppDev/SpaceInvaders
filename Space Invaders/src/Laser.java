

import java.awt.Graphics2D;
import java.awt.Image;

public class Laser extends GameObject {
	
	 private Image image = ImageLoader.loadCompatibleImage("sprites/alienStraightLaser2.png");
	 private int updateCounter = 0;
	 
	public Laser(int x, int y, int w, int h, int direction) {
        super(x, y, w, h);
        direction = 1;
	}
	
	public void update() {
		updateCounter++;
		
		if(updateCounter % 10 == 0)
            this.getBounds().y += 10;
		
		
		
	}
	
	public void render(Graphics2D g) {

		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width,
                    (int)getBounds().height,
                    null);       
	}
	
}