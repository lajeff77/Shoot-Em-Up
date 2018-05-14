package elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 * <h1>Health Bar Class</h1>
 *
 *@author Lauryn 
 *@version 5/23/17
 *
 *
 *The Health Bar class is a class in which the players health is displayed. It is an element of graphics and not the
 *health itself.
 *
 *
 */


public class HealthBar 
{
	private int x, y;
	private final int WIDTH = 100,HEIGHT=5;
	private double healthRatio;
	private Color color;
	private int baseHealth;
	private int speed;
	
	private int currentWidth;
	
	/**
	 * <h2>Health Bar Constructor</h2>
	 * 
	 * Sets up the Health Bar on the screen
	 * 
	 * @param bx the starting x position for the health bar on the screen
	 * @param by the starting y position for the health bar on the screen
	 * @param color the color that the health bar will display
	 */
	public HealthBar(int x, int y, Color color,int baseHealth,int speed)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.color = color;
		healthRatio = 1;
		this.baseHealth = baseHealth;
		currentWidth = WIDTH;
	
	}
	
	public void update()
	{
		y += 1*speed;
	}
	
	/**
	 *<h2>Health Bar setHealth</h2>
	 *
	 *By sending the reduced health of the player the width of the box that displays the health
	 *left will become smaller as well.
	 * 
	 * @param w the new health that the health bar should display.
	 */
	public void setHealth(int currentHealth)
	{
		healthRatio = (double)currentHealth / (double)baseHealth;
		currentWidth = (int)(healthRatio*WIDTH);
	}
	
	
	/**
	 * <h2>Health Bar display</h2>
	 * 
	 * Displays the health at the top of the screen.
	 * 
	 * @param g The Graphics object associated with the JPanel
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.YELLOW);//border
		g.drawRect(x-1, y-1, WIDTH+1, HEIGHT+1);
		g.setColor(Color.GRAY);//how much health they have
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(color);//depleted health
		g.fillRect(x,y,currentWidth,HEIGHT);
	}
	

}
