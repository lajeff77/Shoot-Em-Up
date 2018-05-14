package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import elements.Score;
import graphics.Button;
import graphics.StarCycle;
import main.Game;
import main.KeyManager;
import main.MouseManager;

public class HighScoreState extends State
{
	private ArrayList<Score> highScores;
	private Button backButton;
	private boolean buttonSelected;
	
	public HighScoreState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height) 
	{
		super(stateManager,starCycle, keyManager, mouseManager, width, height);
		backButton = new Button(Color.GRAY,Color.LIGHT_GRAY,"BACK",650,750,90,40,1);
		highScores = Game.getHighScores();
		buttonSelected = false;
	}

	@Override
	public void update() 
	{
		starCycle.update();
		
		
		if(buttonSelected && (mouseManager.getPressed()))
		{
			mouseManager.setPressed(false);
			stateManager.setState(new MenuState(stateManager, starCycle, keyManager,mouseManager,width,height));
		}
	}

	@Override
	public void render(Graphics g) 
	{
		starCycle.render(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New",1,100));
		
		g.drawString("HIGH SCORES",400 - (int)(g.getFontMetrics().stringWidth("HIGH SCORES"))/2,100);
		
		
		g.setFont(new Font("Courier New",1,45));
		for(int i = 0; i <highScores.size(); i++)
		{
			g.drawString(highScores.get(i).toString(),400 - (int)(g.getFontMetrics().stringWidth(highScores.get(i).toString())/2.0), 200+i*60);
		}
		
		renderButton(g);
		
		g.setFont(new Font("Courier New",1, 20));
		g.setColor(Color.WHITE);
		//g.drawString("Press Backspace to return to the menu.",400 - (int)(g.getFontMetrics().stringWidth("Press Backspace to return to the menu."))/2, 780);
	}
	
	private void renderButton(Graphics g) 
	{
		backButton.draw(g);
		buttonSelected = backButton.mouseHoverHighlight(g,mouseManager.getMouseX(),mouseManager.getMouseY());	
	}
}
