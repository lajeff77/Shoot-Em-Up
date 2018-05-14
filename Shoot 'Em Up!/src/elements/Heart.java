package elements;

import java.awt.Graphics;

import graphics.Assets;

public class Heart 
{
	
	private int x,y;
	
	public Heart(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.heart,x,y,128,128,null);
	}

}
