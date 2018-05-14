package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import graphics.Assets;

public class Rock extends GameElement 
{
	//private int size;
	private int speed;
	private int health;
	//private int angle;
	
	private int height;//width,
	
	private int x,y;
	private int totalHealth;
	
	private HealthBar healthBar;
	
	public Rock(int x,int size,int speed, int health,int angle,int width,int height)
	{
		this.x = x;
		y = -32;
		this.speed = speed;
		//this.size = size;
		
		//this.width = width;
		this.height = height;
		totalHealth = this.health = health;
		//this.angle = angle;
		
	}
	
	@Override
	public void update() 
	{
		y += 1* speed;
		/*angle += 1;
		if(angle > 360)
			angle = 0;*/
		if(healthBar != null)
		{
			healthBar.update();
			healthBar.setHealth(health);
		}
		
	}

	@Override
	public void render(Graphics g)
	{
		
		g.drawImage(Assets.rock, x,y,100,100,null);

		if(healthBar != null)
			healthBar.render(g);
	}

	@Override
	public Rectangle getCollisionBox()
	{
		return new Rectangle(x+16,y+32,70,40);
	}
	
	public void hit()
	{
		health -= 1;
		if(healthBar == null)
			healthBar = new HealthBar(x,y,Color.RED,totalHealth,speed);
	}
	
	public boolean isDestroyed()
	{
		return health <= 0;
	}
	
	public boolean isDead()
	{
		return y>height-128 || isDestroyed();
	}
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	public void die()
	{
		health = 0;
	}
}
