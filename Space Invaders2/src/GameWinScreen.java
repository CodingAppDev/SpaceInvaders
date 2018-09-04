



import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class GameWinScreen extends Screen 
{
	int nextLevel = 2;
	public GameWinScreen(GameState s, int w, int h) {
		super(s, w, h);
	}
    
	public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Geneva", Font.BOLD, 30));
        g.drawString("Congratulations! You win! Time for the next level", 50, 50);
	}
	
	public void update() {
	}
	
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER)
			state.switchToGameScreen();
	}
    
    public void keyReleased(KeyEvent e)
	{
	}
	
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
}

