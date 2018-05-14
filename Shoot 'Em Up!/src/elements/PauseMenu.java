package elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import graphics.Button;
import graphics.StarCycle;
import main.KeyManager;
import main.MouseManager;
import states.DifficultyState;
import states.GameState;
import states.MenuState;
import states.StateManager;

public class PauseMenu 
{
	private int x, y;
	private int boxWidth, boxHeight;
	private int selection;
	
	private MouseManager mouseManager;
	private StateManager stateManager;
	private StarCycle starCycle;
	private KeyManager keyManager;
	
	private int width;
	private int height;
	
	private GameState gameState;
	
	private ArrayList<Button> buttons;
	
	private boolean unpaused;
	
	
	public PauseMenu(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height,GameState gameState)
	{
		x = 100;
		y = 100;
		
		boxWidth = 600;
		boxHeight = 400;
		
		selection = 1;
		
		this.stateManager = stateManager;
		this.starCycle = starCycle;
		this.keyManager = keyManager;
		this.mouseManager = mouseManager;
		this.width = width;
		this.height = height;
		this.gameState = gameState;
		
		unpaused = false;
		
		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"RESUME",275,175,250,60,1));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"CHANGE DIFFICULTY",275,250,250,60,2));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"TOGGLE MUSIC ON/OFF",275,325,250,60,3));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"QUIT",275,400,250,60,4));
		
	}
	
	public void update()
	{
		if(mouseManager.getPressed())
		{
			mouseManager.setPressed(false);
			
			switch(selection)
			{
			case 1:
				unpaused = true;
				break;
			case 2:
				if(gameState.isMusicOn())
					gameState.toggleMusic();
				stateManager.setState(new DifficultyState(stateManager, starCycle, keyManager, mouseManager, width, height));
				break;
			case 3:
				gameState.toggleMusic();
				break;
			case 4:
				if(gameState.isMusicOn())
					gameState.toggleMusic();
				stateManager.setState(new MenuState(stateManager, starCycle, keyManager, mouseManager, width, height));
				break;
			default:
				break;
			}
		}
	}
	
	public void render(Graphics g)
	{
		//the outer box
		g.setColor(Color.GRAY);
		g.fillRect(x, y, boxWidth, boxHeight);
		
		//the inner box
		g.setColor(Color.DARK_GRAY);
		g.fillRect( x+10, y+10, boxWidth-20, boxHeight-20);
		
		drawButtons(g);
		
		//label
		g.setFont(new Font("Courier New",1,60));
		g.setColor(Color.WHITE);
		g.drawString("PAUSED",400 - (int)(g.getFontMetrics().stringWidth("PAUSED")/2),160);	
	}
	
	
	public void drawButtons(Graphics g)
	{
		  
		for(Button b : buttons)
          {                                                 
              b.draw(g);
              if(b.isHighlighted(selection)) //finds out if this button is selected
              {
                  b.highlight(g); //highlights the button
              }
          }
		
	 	for(int i = 0; i < buttons.size();i++)
    	{
    		if(buttons.get(i).mouseHoverHighlight(g, mouseManager.getMouseX(),mouseManager.getMouseY()))
    			selection = i+1;
    	}
	}
	
	public boolean getUnpaused()
	{
		return unpaused;
	}
	
	public void setUnpaused(boolean unpaused)
	{
		this.unpaused = unpaused;
	}

}
