package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import elements.InputField;
import elements.Score;
import graphics.Button;
import graphics.StarCycle;
import main.Game;
import main.KeyManager;
import main.MouseManager;

public class NewHighScoreState extends State
{
	private InputField inputField;
	
	private boolean inputing;
	
	private long score;
	private ArrayList<Color> randomColors;
	
	private int color;
	private int width, height;

	private int difficulty;
	private int selection;
	
	private ArrayList<Button> buttons;

	
	
	public NewHighScoreState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height, long score, int difficulty) 
	{
		super(stateManager,starCycle,keyManager, mouseManager, width, height);
		inputField = new InputField(keyManager,280,500,3);

		color = 0;
		
		inputing = true;
		this.score = score;
		randomColors = new ArrayList<Color>();
		randomColors.add(Color.RED);
		randomColors.add(Color.YELLOW);
		randomColors.add(Color.BLUE);
		randomColors.add(Color.GREEN);
		randomColors.add(Color.ORANGE);
		randomColors.add(Color.MAGENTA);
		

		selection = 0;
		
		buttons = new ArrayList<Button>();
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"NEW GAME",100,500,200,80,1));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"RETURN TO MENU",500,500,200,80,2));
		
		this.width = width;
		this.height = height;
		this.difficulty = difficulty;
	}

	@Override
	public void update() 
	{
		starCycle.update();
		
		if(inputing)
			inputField.update();
		
		if(inputing && keyManager.enter && inputField.filledOut())
		{
			inputing = false;
			keyManager.enter = false;
			Game.newHighScore(new Score(inputField.getName(),score));
			
		}
		
		if(!inputing)
		{
			if(mouseManager.getPressed())
			{
				mouseManager.setPressed(false);
				switch(selection)
				{
					case 1:
						stateManager.setState(new GameState(stateManager, starCycle, keyManager, mouseManager,width, height, difficulty));
						break;
					case 2:
						stateManager.setState(new MenuState(stateManager,starCycle, keyManager, mouseManager, width, height));
						break;
					default:
						break;
				}	
			}
		}
		
		color = (int)(System.nanoTime()%6);
	}

	@Override
	public void render(Graphics g) 
	{
		starCycle.render(g);
		
		if(inputing)
			inputField.render(g);
		
		g.setFont(new Font("New Courier",1,75));
		g.setColor(randomColors.get(color));
		g.drawString("NEW HIGH SCORE",90,100);
		
		if(inputing)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("New Courier",1,50));
			g.drawString("Please enter your intials:", 100, 200);
		}
		
		if(!inputing)
		{
			drawButtons(g);
		}
		
	}
	
	
	public void drawButtons(Graphics g)
	{
		g.setFont(new Font("New Courier",1,25));
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

}
