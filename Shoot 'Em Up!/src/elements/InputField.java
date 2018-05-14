package elements;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.KeyManager;

public class InputField 
{

	private int x,y;
	//private int index;
	private int maxChars;
	private ArrayList<String> input;
	private KeyManager keyManager;
	
	public InputField(KeyManager keyManager,int x, int y, int maxChars) 
	{
		this.keyManager = keyManager;
		this.x = x;
		this.y = y;
		this.maxChars = maxChars;
		//index = 0;
		
		input = new ArrayList<String>(maxChars);
	}
	
	public void update()
	{
		if(input.size() < maxChars)	
		{
			if(keyManager.a)
			{
				keyManager.a = false;
				input.add("A");
			}
			
			if(keyManager.b)
			{
				keyManager.b = false;
				input.add("B");
			}
			
			if(keyManager.c)
			{
				keyManager.c = false;
				input.add("C");
			}
			
			if(keyManager.d)
			{
				keyManager.d = false;
				input.add("D");
			}
			
			if(keyManager.e)
			{
				keyManager.e = false;
				input.add("E");
			}
			
			if(keyManager.f)
			{
				keyManager.f = false;
				input.add("F");
			}
			
			if(keyManager.g)
			{
				keyManager.g = false;
				input.add("G");
			}
			
			if(keyManager.h)
			{
				keyManager.h = false;
				input.add("H");
			}
			
			if(keyManager.i)
			{
				keyManager.i = false;
				input.add("I");
			}
			
			if(keyManager.j)
			{
				keyManager.j = false;
				input.add("J");
			}
			
			if(keyManager.k)
			{
				keyManager.k = false;
				input.add("K");
			}
			
			if(keyManager.l)
			{
				keyManager.l = false;
				input.add("L");
			}
			
			if(keyManager.m)
			{
				keyManager.m = false;
				input.add("M");
			}
			
			if(keyManager.n)
			{
				keyManager.n = false;
				input.add("N");
			}
			
			if(keyManager.o)
			{
				keyManager.o = false;
				input.add("O");
			}
			
			if(keyManager.p)
			{
				keyManager.p = false;
				input.add("P");
			}
			
			if(keyManager.q)
			{
				keyManager.q = false;
				input.add("Q");
			}
			
			if(keyManager.r)
			{
				keyManager.r = false;
				input.add("R");
			}
			
			if(keyManager.s)
			{
				keyManager.s = false;
				input.add("S");
			}
			
			if(keyManager.t)
			{
				keyManager.t = false;
				input.add("T");
			}
			
			if(keyManager.u)
			{
				keyManager.u = false;
				input.add("U");
			}
			
			if(keyManager.v)
			{
				keyManager.v = false;
				input.add("V");
			}
			
			if(keyManager.w)
			{
				keyManager.w = false;
				input.add("W");
			}
			
			if(keyManager.x)
			{
				keyManager.x = false;
				input.add("X");
			}
			
			if(keyManager.y)
			{
				keyManager.y = false;
				input.add("Y");
			}
			
			if(keyManager.z)
			{
				keyManager.z = false;
				input.add("Z");
			}
		}
		
		if(input.size()>0)
		{
			if(keyManager.backspace)
			{
				keyManager.backspace = false;
				input.remove(input.size()-1);
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.setFont(new Font("Courier New",1,150));
		g.fillRect(x,y + 20,g.getFontMetrics().stringWidth("A")*3,10);
		
		for(int i = 0; i < input.size(); i++)
			g.drawString(input.get(i),x + g.getFontMetrics().stringWidth(input.get(i))*i,y);
	}
	
	public String getName()
	{
		String name = "";
		
		for(String s: input)
			name += s;
		
		return name;
	}
	
	public boolean filledOut()
	{
		return input.size() > 0;
	}

}
