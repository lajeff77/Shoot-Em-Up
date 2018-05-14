package states;

import graphics.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import graphics.StarCycle;
import main.KeyManager;
import main.MouseManager;

public class GameOverState extends State 
{
	
	private ArrayList<Button> buttons;
	private GameState gameState;
	private int selection;
	private int difficulty;

	public GameOverState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height,GameState gameState, int difficulty) 
	{
		super(stateManager,starCycle,keyManager, mouseManager, width, height);
		this.gameState = gameState;
		this.difficulty = difficulty;
		
		selection = 0;
		
		buttons = new ArrayList<Button>();
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"NEW GAME",100,500,200,80,1));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"RETURN TO MENU",500,500,200,80,2));
	}

	@Override
	public void update()
	{
		gameState.update();
		
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

	@Override
	public void render(Graphics g) 
	{
		gameState.render(g);
	
		g.setFont(new Font("Courier New",1,100));
		g.setColor(Color.RED);
		g.drawString("GAME OVER",(width/2)-(g.getFontMetrics().stringWidth("GAME OVER"))/2,height/2);
		/*g.setFont(new Font("New Courier",1,25));
		g.setColor(Color.WHITE);
		g.drawString("Press enter for a new game or backspace to return to the menu.",25,520);*/
		
		drawButtons(g);
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
