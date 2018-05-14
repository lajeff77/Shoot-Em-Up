package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class StarCycle 
{
	private ArrayList<Integer> xStarPos, yStarPos;
	private int width;
	private int height;

	public StarCycle(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		xStarPos = new ArrayList<Integer>(200);
		yStarPos = new ArrayList<Integer>(200);
		
		for(int i =0; i < 200; i++)
		{
			xStarPos.add((int)(Math.random()*width));
			yStarPos.add(i*4);
		}
	}
	
	public void update()
	{
		if(xStarPos.size()< 200)
		{
			xStarPos.add((int)(Math.random()*width));
			yStarPos.add(0);
		}
	}
	
	public void render(Graphics g)
	{
		//draw background
		g.setColor(new Color(0,10,25));
		g.fillRect(0,0,width,height);
		
		//draw stars
		g.setColor(Color.WHITE);
		for(int i =0; i < xStarPos.size(); i++)
		{
			g.fillOval(((Integer)xStarPos.get(i)).intValue(),((Integer)yStarPos.get(i)).intValue(),2,2);
			yStarPos.set(i,((Integer)yStarPos.get(i)).intValue() + 5);

			if(((Integer)yStarPos.get(i)).intValue() > height)
			{
				xStarPos.remove(i);
				yStarPos.remove(i);
				i--;
			}
		}
	}
}
