/**
 * <h1>Bullet Class</h1>
 * 
 * Created:6/21/17
 * Last Updated:9/14/17
 * 
 * <p>The Bullet class is a representation of the bullet in the game. 
 * It keeps track of it's own position and whether it is still on the 
 * screen or not.</p>
 * 
 * @author Lauryn Jefferson
 *
 */
package elements;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Assets;

public class Bullet extends GameElement
{
	//variables for positions
	private int x,y;
	
	//variables for dimensions of bullet
	private int width,height;
	private int xOffset, yOffset;
	
	private int speed;
	private int yBoundary;
	
	//variables to denote whether it should still exist on the screen
	private boolean visible;
	private boolean done;
	
	/**
	 * <h2>Bullet() Constructor</h2>
	 * 
	 * <p>This default constructor makes a bullet with default
	 *  coordinates as (0,0). </p>
	 */
	public Bullet()
	{
		this(0,0);
	}
	
	
	/**
	 * <h2>Bullet() Constructor</h2>
	 * 
	 * <p>This constructor sets up the Bullet object with the 
	 * beginning coordinates and sets up the bullet to be used.</p>
	 * 
	 * @param x the starting x coordinate for the bullet
	 * @param y the starting y coordinate for the bullet
	 */
	public Bullet(int x,int y)
	{
		this.x = x;
		this.y = y;
		
		speed = -4;
		yBoundary = -128;
		
		width = height = 16;
		
		xOffset = 44;
		yOffset = 48;
		
		visible = true;
		done = false;
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method keeps the bullet moving in the game cycle.
	 * It also determines when it shouldn't show up on the screen 
	 * anymore</p>
	 * 
	 */
	@Override
	public void update()
	{
		y += speed;
		if(y < yBoundary)
			visible = false;
	}
	
	/**
	 * <h2>render() method</p>
	 * 
	 * <p>This method takes in a graphics object for the place we want to 
	 * draw to and displays the bullet at its coordinates.</p>
	 * 
	 * @param g The graphics object of the location that we want to draw to
	 */
	@Override	
	public void render(Graphics g)
	{
		g.drawImage(Assets.bullet,x,y,null);
	}
	
	/**
	 * <h2> getY() method</h2>
	 * <p>This method returns the y coordinate of the bullet.</p>
	 * @return current y coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * <h2>isVisible() method</h2>
	 * <p>This method tells us if the y coordinate for the bullet
	 * has left the screen.</p>
	 * @return whether the bullet should be seen
	 */
	public boolean isVisible()
	{
		return visible;
	}
	
	/**
	 * <h2>done() method</h2>
	 * 
	 */
	public void done()
	{
		done = true;
	}

	public boolean isDone()
	{
		return done || !isVisible();
	}
	
	@Override
	public Rectangle getCollisionBox()
	{
		return new Rectangle(x+xOffset,y+yOffset,width,height);
	}

}
