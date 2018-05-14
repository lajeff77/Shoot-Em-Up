package elements;

import java.awt.Color;
import java.awt.Graphics;

import audio.AudioManager;

public class OverheatBar
{
	private int x, y;
	private final int WIDTH = 150,HEIGHT=20;
	private final int maxShots;
	private double overheatRatio;
	private Color color;
	
	private int currentWidth;
	
	private int speed;
	private long lastTime, timer;
	
	private int shots;
	
	private boolean overheated;
	
	/**
	 * <h2>Health Bar Constructor</h2>
	 * 
	 * Sets up the Health Bar on the screen
	 * 
	 * @param bx the starting x position for the health bar on the screen
	 * @param by the starting y position for the health bar on the screen
	 * @param color the color that the health bar will display
	 */
	public OverheatBar(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		timer = 0;
		lastTime = System.currentTimeMillis();
		overheatRatio = 0;
		currentWidth = 0;
		speed = 750;
		maxShots = 10;
		shots = 0;
		overheated = false;
	}
	
	public void update()
	{

		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(overheated)
			speed = 250;
		else
			speed = 750;
		
		if(timer > speed)
		{
			timer = 0;
			if(shots > 0)
				shots--;
			
			if(overheated && shots == 0)
				overheated = false;
			
			overheatRatio = (double)shots / (double)maxShots;
			currentWidth = (int) (WIDTH * overheatRatio);
		}
			
	}
	
	public void setOverheat()
	{
		shots++;
		
		if(shots <= 10)
			overheatRatio = (double)shots / (double)maxShots;
		
		currentWidth = (int) (WIDTH * overheatRatio);
		
		if(shots == 11)
		{
			overheated = true;
			AudioManager.playAudio("/audio/overheat.wav");
		}

	}
	
	public boolean isOverheated()
	{
		return overheated;	
	}
	
	public void render(Graphics g)
	{
		g.setColor(color);//border
		g.drawRect(x-1, y-1, WIDTH+1, HEIGHT+1);
		g.setColor(Color.ORANGE);//how much health they have
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(color);//depleted health
		g.fillRect(x,y,currentWidth,HEIGHT);
	}
	
}

