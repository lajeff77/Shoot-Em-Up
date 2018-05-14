package elements;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Animation;
import graphics.Assets;

public class Explosion 
{
	private Animation explosion;
	private BufferedImage firstFrame;
	private int iterations;
	private boolean done;
	private int x,y;
	
	public Explosion(int x, int y)
	{
		this.x = x;
		this.y = y;
		explosion = new Animation(250,Assets.explosion);
		firstFrame = explosion.getCurrentFrame();
		done = false;
	}
	
	public void update()
	{
		explosion.update();
		if(explosion.getCurrentFrame() == firstFrame)
			iterations++;
		
		if(iterations > 20)
			done = true;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(explosion.getCurrentFrame(), x,y, null);
	}
	
	public boolean isDone()
	{
		return done;
	}
}
