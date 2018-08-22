/*
    Class that represents a player in the game
*/

import java.awt.*;
import java.awt.geom.*;

public class Player extends GameObject{
	

    //the image used for the player
    private Image image = ImageLoader.loadCompatibleImage("sprites/player.png");
    
    //keeps track of whether the player is moving to the left
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private int shootCountdown = 0;

	public Player(int x, int y, int w, int h) {
        super(x, y, w, h);
	}
	

	public void update() {
		
		//Checks before moving left to determine if the player is at the edge
        if(getBounds().x == 0) {
			movingLeft = false;
		}
        
        //if we're moving to the left, decrease the player's boundary x by 1		
        if(movingLeft)
            getBounds().x--;
        
        //Checks after moving left to determine if player is at edge
        if(getBounds().x == 0) {
			movingLeft = false;
		}
        
      //Checks before moving right to determine if player is at edge
        if(getBounds().x == 755) {
			movingRight = false;
		}
        
        //If the player is movingRight, then increase xPos
		if(movingRight) {
			getBounds().x++;
		}
		
		//If the player is at the right edge, don't allow moving right
		if(getBounds().x == 755) {
			movingRight = false;
		}
		
	}
			
    
    //tell the player if they should be moving left or right
    public void setMovingLeft(boolean ml) {
        movingLeft = ml;
    }
    
    public void setMovingRight(boolean m2) {
    	movingRight = m2;
    }
    
    //draw the player with the player's image
	public void render(Graphics2D g) {

		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width,
                    (int)getBounds().height,
                    null);
	}


}