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
    private int score;
    private int currentHighScore;
    private int nextLevel = 2;
    
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
        
        //On the first level, these aliens are created
        if(nextLevel == 2){
        	player = new Player(width/2 - 23, height - 24, 45, 24);
        	aliens.add(new Alien(0, 100, 37, 25));
        	aliens.add(new Alien(0, 70, 37, 25));
        	aliens.add(new Alien(0, 30, 37, 25));
        	aliens.add(new Alien(50, 100, 37, 25));
        	aliens.add(new Alien(50, 70, 37, 25));
        	aliens.add(new Alien(50, 30, 37, 25));
        	aliens.add(new Alien(100, 100, 37, 25));
        	aliens.add(new Alien(100, 70, 37, 25));
        	aliens.add(new Alien(100, 30, 37, 25));
        	score = 0;
        }
        //Second level
        if(nextLevel == 3) {
        	aliens.add(new Alien(0, 300, 37, 25));
        	aliens.add(new Alien(0, 370, 37, 25));
        	aliens.add(new Alien(0, 330, 37, 25));
        	aliens.add(new Alien(50, 300, 37, 25));
        	aliens.add(new Alien(50, 370, 37, 25));
        	aliens.add(new Alien(50, 330, 37, 25));
        	aliens.add(new Alien(100, 300, 37, 25));
        	aliens.add(new Alien(100, 370, 37, 25));
        	aliens.add(new Alien(100, 330, 37, 25));
        }
        //Fourth level
        if(nextLevel == 4) {
        	aliens.add(new Alien(0, 300, 37, 25));
        	aliens.add(new Alien(0, 370, 37, 25));
        	aliens.add(new Alien(0, 330, 37, 25));
        	aliens.add(new Alien(50, 300, 37, 25));
        	aliens.add(new Alien(50, 370, 37, 25));
        	aliens.add(new Alien(50, 330, 37, 25));
        	aliens.add(new Alien(100, 300, 37, 25));
        	aliens.add(new Alien(100, 370, 37, 25));
        	aliens.add(new Alien(100, 330, 37, 25));
        	aliens.add(new Alien(0, 100, 37, 25));
        	aliens.add(new Alien(0, 70, 37, 25));
        	aliens.add(new Alien(0, 30, 37, 25));
        	aliens.add(new Alien(50, 100, 37, 25));
        	aliens.add(new Alien(50, 70, 37, 25));
        	aliens.add(new Alien(50, 30, 37, 25));
        	aliens.add(new Alien(100, 100, 37, 25));
        	aliens.add(new Alien(100, 70, 37, 25));
        	aliens.add(new Alien(100, 30, 37, 25));
        }
        
        //Boss level  
    	if(nextLevel == 5) {
    		aliens.add(new Alien(0, 100, 37, 25));
        	aliens.add(new Alien(0, 70, 37, 25));
        	aliens.add(new Alien(0, 30, 37, 25));
        	aliens.add(new Alien(50, 100, 37, 25));
        	aliens.add(new Alien(50, 70, 37, 25));
        	aliens.add(new Alien(50, 30, 37, 25));
        	aliens.add(new Alien(100, 100, 37, 25));
        	aliens.add(new Alien(100, 70, 37, 25));
        	aliens.add(new Alien(100, 30, 37, 25));
        	aliens.add(new Alien(0, 300, 100, 100));
    	}
    }
        
	
    //render all the game objects in the game
	public void render(Graphics2D g) {
		
	//Renders each alien in the alien array	
		for(Alien a: aliens)
            a.render(g);
		
	//Renders each laser object	in the laser array
        for(Laser l: laserList)
            l.render(g);
        
    //Renders the player object
        player.render(g);
    
    //Renders the current score and session high score
        g.setFont(new Font("Geneva", Font.BOLD, 20));
		g.setColor(Color.GREEN);
		g.drawString("Current score:" + score, 640, 25);
		
		g.setFont(new Font("Geneva", Font.BOLD, 12));
		g.drawString("Session high score:" + currentHighScore, 650, 50);
	
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
            
            //Runs through the possible intersections between player and alien lasers
            if(laserList.size() != 0) {
            	for(int j = laserList.size()-1; j >= 0; j--) {
            		Laser current = laserList.get(j);
            	
            			for(int i = 0; i < aliens.size(); i++) {
            				Alien alien = aliens.get(i);
            			
            					//Alien gets hit by a player laser
            					if(alien.intersects(current) && current.getDirection() == -1){
            						aliens.remove(alien);
            						laserList.remove(current);
            						
            						score += alien.getPoints();
            						if(score >= currentHighScore) {
            							currentHighScore = score;            							
            						}
            						
            					}
            					
            					//If the player gets hit by an alien laser, go to game over screen 
            					//and re-initialize the game to start over from level 1
            					if(player.intersects(current) && current.getDirection() == 1){
            						gameOver();
            						initGame();
            						return;
            					}
            					
            					//If no more aliens remain, show game win screen and go to next level
            					if(aliens.size() == 0) {
            						if(nextLevel == 5) {
            							state.switchToWelcomeScreen();
            						}
            						
            						nextLevel++;
        							gameWin();
        							initGame();
        							return;
        							   							
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
    
    //Should be called when the player defeats all the aliens
   public void gameWin() {
    	//sets up the next game
    	initGame();
    	
    	//switches to the game win screen
    	state.switchToGameWinScreen();
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