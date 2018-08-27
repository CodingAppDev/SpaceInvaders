/*
    Screen implementation that models a game
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class GameScreen extends Screen
{
    //variables that represent the different GameObjects in the game
    private ArrayList<Alien> aliens;
    private Player player;
    private int gameOverStatus;
    
    //this class inherits the following final variables (so you can't change them!)
    //
    //  GameState state;    //variable that lets you switch to another screen
	//  int width, height;  //the width and height of this screen

	public GameScreen(GameState s, int w, int h) {
		super(s, w, h);
        initGame();
	}
    
    //initialize our variables before the next game begins
    public void initGame() {
        aliens = new ArrayList<Alien>();
        laserList = new ArrayList<Laser>();
        
        player = new Player(width/2 - 23, height - 24, 45, 24);
        aliens.add(new Alien(0, 100, 37, 25));
        aliens.add(new Alien(25, 70, 37, 25));
        aliens.add(new Alien(50, 30, 37, 25));
    }
	
    //render all the game objects in the game
	public void render(Graphics2D g) {
		
		for(Alien a: aliens)
            a.render(g);
		
        for(Laser l: laserList)
            l.render(g);
        
        player.render(g);
	}		
	
	
    //update all the game objects in the game
	public void update() {
        for(int i = 0; i < aliens.size(); i++) {
            Alien a = aliens.get(i);
            a.update();
            Laser laser = a.shoot();
            if(!(laser == null)) {
            	laserList.add(laser);
            }
            
        }
          
            for(int i = 0; i < laserList.size(); i++) {
            	Laser current = laserList.get(i);
            		current.update();
            }
            
            int alienSize = aliens.size();
            
            if(laserList.size() != 0) {
            	for(int j = laserList.size()-1; j >= 0; j--) {
            		Laser current = laserList.get(j);
            	
            			for(int i = 0; i < aliens.size(); i++) {
            				Alien alien = aliens.get(i);
            			
            					if(alien.intersects(current) && current.getDirection() == -1){
            						aliens.remove(alien);
            						laserList.remove(current);  
            						gameOverStatus+= 1;
            						
            						//if(gameOverStatus == alienSize) {
            						//	gameOver();
            						//}
            					}            			
            			}
            	}
            }

        player.update();
        
	}
	
    //handles key press events
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_Q)
			state.switchToWelcomeScreen();
        else if(code == KeyEvent.VK_LEFT)
            player.setMovingLeft(true);
		
		if(code == KeyEvent.VK_E)
			state.switchToWelcomeScreen();
		  else if(code == KeyEvent.VK_RIGHT)
	            player.setMovingRight(true);
		
		if(code == KeyEvent.VK_SPACE) {
			Laser currentShot = player.shoot();
			if(!(currentShot == null)){
				laserList.add(currentShot);
			}
		}
	}	
			
	
	
    //handles key released events
	public void keyReleased(KeyEvent e)
	{
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_LEFT)
            player.setMovingLeft(false);
	
        int code2 = e.getKeyCode();
        if(code2 == KeyEvent.VK_RIGHT)
        	player.setMovingRight(false);
	}
    
    //should be called when the game is over
    public void gameOver() {
        //sets up the next game
        initGame();
        
        //switch to the game over screen
        state.switchToGameOverScreen();
    }
	
    //implement these methods if your player can use the mouse
	public void mousePressed(Point2D p)
	{
	}
	public void mouseReleased(Point2D p)
	{
	}
	public void mouseMoved(Point2D p)
	{
	}
	public void mouseDragged(Point2D p)
	{
	}
	
	//Arraylist of laser objects
	ArrayList<Laser> laserList = new ArrayList<Laser>();
	
}