package elements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import audio.AudioManager;

public class RockManager
{

	private int difficulty;
	private int speed;
	private long lastTime, timer;
	private int width,height;
	
	private ArrayList<Rock> rocks;
	private ArrayList<Explosion> explosions;
	private Ship ship;
	
	public RockManager(Ship ship,int difficulty, int width,int height)
	{
		this.ship = ship;
		this.width = width;
		this.height = height;
		timer = 0;
		lastTime = System.currentTimeMillis();
		
		this.difficulty = difficulty;
		
		switch(difficulty)
		{
		case 1: 
			speed = 3000;
			break;
		case 2:
			speed = 2500;
			break;
		case 3:
			speed = 2000;
			break;
		default:
			speed = 3000;
			break;
		}
		
		rocks = new ArrayList<Rock>();
		explosions = new ArrayList<Explosion>();
	}

	public void update()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed)
		{
			timer = 0;
			rocks.add(new Rock(getRandom(0,width-100),getRandom(1,1),getRandom(2,5),getRandom(1,3),getRandom(0,360),width,height));
		}
		
		for(Rock r: rocks)
			r.update();
		
		checkCollisions();
		
		
		for(int i = 0; i < rocks.size(); i++)
		{
			Rock r = rocks.get(i);
			if(r.isDead())
			{
				if(!r.isDestroyed())
					ship.loseLife();
				else
					AudioManager.playAudio("/audio/boom.wav");
				
				explosions.add(new Explosion(r.getX(),r.getY()));
				rocks.remove(r);
				i--;
			}
		}
		
		for(int i = 0; i < explosions.size(); i++)
		{
			Explosion e = explosions.get(i);
			if(e.isDone())
			{
				explosions.remove(i);
				i--;
			}
				
		}
		
		for(Explosion e: explosions)
			e.update();
		
	}
	
	public void render(Graphics g)
	{
		for(Rock r: rocks)
			r.render(g);
		
		for(Explosion e: explosions)
			e.render(g);
	}
	
	private int getRandom(int min, int max)
	{
	    Random random = new Random();
	    return random.nextInt((max - min) + 1) + min;
	}
	
	private void checkCollisions()
	{
		for(Rock r:rocks)
		{
			for(Bullet b:ship.getShots())
			{
				if(r.getCollisionBox().intersects(b.getCollisionBox()))
				{
					r.hit();
					ship.addToScore(10*difficulty);
					b.done();
					
				}
			}
			
			if(r.getCollisionBox().intersects(ship.getCollisionBox()))
			{
				ship.loseLife();
				r.die();
			}
		}
	}
}


