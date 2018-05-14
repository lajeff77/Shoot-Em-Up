package elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import audio.AudioManager;
import graphics.Animation;
import graphics.Assets;
import main.KeyManager;

public class Ship extends GameElement
{

	private Animation shipAnimation;
	private KeyManager keyManager;
	private int width;//,height;
	private int shipX, shipY;
	private ArrayList<Bullet> shots;
	
	private int lives;
	private boolean gameOver;
	private boolean hasShot;
	private boolean overheated;
	
	private long score;
	//private int cont;
	
	public Ship(KeyManager keyManager,int width,int height)
	{
		this.width = width;
		//this.height = height;
		this.keyManager = keyManager;
		shipAnimation = new Animation(100,Assets.ship);
		shipX = (int)(width/2.0) - (int)(Assets.WIDTH/2.0);
		shipY = (int)(height - height/4.0) -(int)(Assets.HEIGHT/2.0);
		shots = new ArrayList<Bullet>();
		
		gameOver = false;
		lives = 3;
		//cont = 0;
		
		hasShot = false;
		
		overheated = false;
	}
	
	@Override
	public void update()
	{
		if(!isGameOver())
		{
			if(keyManager.left)
				shipX -=4;
			if(keyManager.right)
				shipX +=4;
			if(shipX < -20)
				shipX = -20;
			if(shipX > width-104)
				shipX= width-104;

			if(!overheated && keyManager.shoot)
			{
				
				if(shots.isEmpty())
				{
					shots.add(new Bullet(shipX+10,shipY-4));
					hasShot = true;
				}
				else
					if(shots.get(shots.size()-1).getY() <= shipY-29)
					{
						shots.add(new Bullet(shipX+10,shipY-4));
						hasShot = true;
					}
					else
						hasShot = false;

			}
			else 
				hasShot = false;

			for(int i = 0; i < shots.size(); i++)
			{
				shots.get(i).update();
				if(shots.get(i).isDone())
				{
					shots.remove(i);
					i--;
				}
			}

		}
		else
		{
			while(!shots.isEmpty())
				shots.remove(0);
		}
		shipAnimation.update();
	}

	@Override
	public void render(Graphics g)
	{
		
		
		for(int i = 0; i < shots.size(); i ++)
			shots.get(i).render(g);
		
		g.drawImage(shipAnimation.getCurrentFrame(),shipX,shipY,null);
			
	}


	@Override
	public Rectangle getCollisionBox()
	{
		return new Rectangle(shipX+36,shipY+28,52,72);
	}
	
	public void loseLife()
	{
		lives -= 1;
		if (lives == 0)
			gameOver = true;
		
		if(lives >= 0)
			AudioManager.playAudio("/audio/hurt.wav");
	}
	
	public ArrayList<Bullet> getShots()
	{
		return shots;
	}
	
	public int getShotAmount()
	{
		return shots.size();
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public void setKeyManager(KeyManager keyManager)
	{
		this.keyManager = keyManager;
	}
	
	public void addToScore(long number)
	{
		score = score + number;
	}
	
	public long getScore()
	{
		return score;
	}
	
	public void overheat(boolean overheated)
	{
		this.overheated = overheated;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public boolean hasShot()
	{
		return hasShot;
	}

}


