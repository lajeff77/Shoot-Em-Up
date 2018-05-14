package states;

import java.awt.Graphics;

import graphics.StarCycle;
import main.KeyManager;
import main.MouseManager;

public abstract class State 
{
	protected StateManager stateManager;
	protected StarCycle starCycle;
	protected KeyManager keyManager;
	protected MouseManager mouseManager;
	protected int width, height;
	
	public State(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height)
	{
		this.stateManager = stateManager;
		this.starCycle = starCycle;
		this.keyManager = keyManager;
		this.mouseManager = mouseManager;
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
}
