package states;

import graphics.Button;
import graphics.StarCycle;
import main.KeyManager;
import main.MouseManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class MenuState extends State 
{
	private ArrayList<Button> buttons;
	private int selection;
	
	public MenuState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height) 
	{
		super(stateManager,starCycle,keyManager, mouseManager, width, height);

		
		buttons = new ArrayList<Button>();
		selection = 1;
		
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"START",250,250,300,100,1));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"HIGH SCORES",250,400,300,100,2));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"INSTRUCTIONS",250,550,300,100,3));
		buttons.add(new Button(Color.GRAY,Color.LIGHT_GRAY,"EXIT",250,700,300,40,4));
	}

	@Override
	public void update() 
	{
		starCycle.update();
		
        /*if(keyManager.up)    //moves the  selected button up and sets it false
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
            selection=4;
        if(selection>4) //if selection is out of bounds in the positive direction we set it to be 1
            selection=1; */
        
        if(keyManager.enter ||mouseManager.getPressed())
        {
        	mouseManager.setPressed(false);
        	keyManager.enter = false;
        	switch(selection)
        	{
        		case 1:
        			stateManager.setState(new DifficultyState(stateManager,starCycle,keyManager,mouseManager,width,height));
        			break;
        		case 2:
        			stateManager.setState(new HighScoreState(stateManager,starCycle,keyManager,mouseManager,width, height));
        			break;
        		case 3:
        			stateManager.setState(new InstructionState(stateManager,starCycle,keyManager,mouseManager,width, height));
        			break;
        		case 4:
        			System.exit(0);
        		default:
        			stateManager.setState(new DifficultyState(stateManager,starCycle,keyManager,mouseManager,width,height));
        			break;
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
		g.drawString("Shoot 'Em Up!",400-(g.getFontMetrics().stringWidth("Shoot 'Em Up!")/2),160);
		
		drawButtons(g);

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

}

