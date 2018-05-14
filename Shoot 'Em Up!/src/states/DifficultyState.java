package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import graphics.Button;
import graphics.StarCycle;
import main.KeyManager;
import main.MouseManager;

public class DifficultyState extends State 
{

	private ArrayList<Button> buttons;
	private int selection;
	private Button backButton;
	private boolean buttonSelected;

	
	public DifficultyState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height) 
	{
		super(stateManager,starCycle,keyManager, mouseManager, width, height);
		
		buttons = new ArrayList<Button>();
		selection = 1;

		backButton = new Button(Color.GRAY,Color.LIGHT_GRAY,"BACK",650,750,90,40,1);
		
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"EASY",250,250,300,100,1));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"MEDIUM",250,400,300,100,2));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"HARD",250,550,300,100,3));
		
		buttonSelected = false;
	}

	@Override
	public void update() 
	{
		starCycle.update();
		
        if(keyManager.up)    //moves the  selected button up and sets it false
        {
            selection--;
            keyManager.up = false;
        }
        if(keyManager.down)    //moves the selected button down and sets it false
        {
           selection++;
           keyManager.down = false;
        }
        
        if(selection<1) //if selection is out of bounds in the negative direction we set it to be 3
            selection=3;
        if(selection>3) //if selection is out of bounds in the positive direction we set it to be 1
            selection=1; 
        
        if(mouseManager.getPressed() && !buttonSelected )
        {
        	mouseManager.setPressed(false);
        	stateManager.setState(new GameState(stateManager,starCycle,keyManager,mouseManager,width,height,selection));
        }
        else
        {
        	if(mouseManager.getPressed() && buttonSelected)
        	{
        		mouseManager.setPressed(false);
    			stateManager.setState(new MenuState(stateManager, starCycle, keyManager,mouseManager,width,height));
        	}
        }
        
        
	}

	@Override
	public void render(Graphics g) 
	{
		starCycle.render(g);
		
		g.setColor(Color.GRAY);
		g.fillRect(100, 100, 600, 100);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(103, 103, 594, 94);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",1,45));
		g.drawString("Select Difficulty:",400-(g.getFontMetrics().stringWidth("Select Difficulty:")/2),160);
		
		drawButtons(g);
		
		renderButton(g);

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
	
	private void renderButton(Graphics g) 
	{
		backButton.draw(g);
		buttonSelected = backButton.mouseHoverHighlight(g,mouseManager.getMouseX(),mouseManager.getMouseY());	
	}

}
