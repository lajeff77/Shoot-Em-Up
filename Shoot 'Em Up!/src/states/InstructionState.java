package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.Arrow;
import graphics.Assets;
import graphics.Button;
import graphics.ImageLoader;
import graphics.StarCycle;
import main.KeyManager;
import main.MouseManager;

public class InstructionState extends State 
{
	private int page;
	private ArrayList<BufferedImage> pages;
	//private ArrayList<ArrayList<String>> text;
	private ArrayList<String>[] text;
	private Arrow leftArrow,rightArrow;
	private Button backButton;
	private boolean buttonSelected;
	

	public InstructionState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height) 
	{
		super(stateManager,starCycle,keyManager, mouseManager, width, height);
		backButton = new Button(Color.GRAY,Color.LIGHT_GRAY,"BACK",650,750,90,40,1);
		pages = Assets.pages;
		//text = new ArrayList<ArrayList<String>>();
		//text = new ArrayList[6];
		buttonSelected = false;
		
		rightArrow = new Arrow(ImageLoader.loadImage("/images/arrow.png"),790 -ImageLoader.loadImage("/images/arrow.png").getWidth(),726-ImageLoader.loadImage("/images/arrow.png").getHeight()/2);
		leftArrow = new Arrow(ImageLoader.rotateImage(ImageLoader.rotateImage(rightArrow.getImage())),10,726-rightArrow.getImage().getHeight()/2);
		
		init();
		page = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void init()
	{
		ArrayList<String>lines = new ArrayList<String>();
		
		lines.add("To start, let's take a look at the command bar at the bottom of the screen.");
		lines.add("In the left corner the overheat bar indicates if too many shots were fired.");
		lines.add("In the middle there is a display to indicate how many lives you have left.");
		lines.add("Finally, the right corner will show what your score is.");
				
		//text.add(lines);
		text[0] = (ArrayList<String>) lines.clone();
		lines.clear();
		
		lines.add("You can shoot bullets from your ship by pressing the up arrow key. You");
		lines.add("can move your ship left and right by using the left and right arrow keys.");
		
		//text.add(lines);
		text[1] = (ArrayList<String>) lines.clone();;
		lines.clear();
	
		lines.add("The goal is to destroy oncoming rocks with your bullets. Be sure to");
		lines.add("keep an eye on the overheat bar because if you shoot too many ");
		lines.add("bullets at once your ship's gun will be disabled.");
		
		//text.add(lines);
		text[2] = (ArrayList<String>) lines.clone();;
		lines.clear();
		
		lines.add("Some rocks need to be shot more than once. However, you won't know ");
		lines.add("until you shoot the rock. If the rock requires more than one shot to");
		lines.add("destroy, a healthbar will appear above the rock to indicate how much");
		lines.add("health is left in the rock.");
		
		//text.add(lines);
		text[3] = (ArrayList<String>) lines.clone();;
		lines.clear();
		
		lines.add("If you fail to destroy a rock and it hits you, you will lose a life.");
		lines.add("Be careful, if you let any rocks pass you, you will also lose a life.");
		
		//text.add(lines);
		text[4] = (ArrayList<String>) lines.clone();;
		lines.clear();
		
		lines.add("When you lose all three lives, the game will be over.");
		lines.add("You can press p at anytime to pause the game. Have fun! ");
		//lines.add("(Press Backspace to return to the menu.)");
		
		//text.add(lines);
		text[5] = (ArrayList<String>) lines.clone();;
		lines.clear();
		
	}

	@Override
	public void update() 
	{
		starCycle.update();
		
		if(leftArrow.isTouchingMouse(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.getPressed())
		{
			page --;
			keyManager.left = false;
		}
		
		if(rightArrow.isTouchingMouse(mouseManager.getMouseX(),mouseManager.getMouseY())&& mouseManager.getPressed())
		{
			page++;
			keyManager.right = false;
		}
			
		if(page < 0)
			page = 0;
		
		if (page > pages.size()-1)
			page = pages.size()-1;
		

		if(buttonSelected && (mouseManager.getPressed()))
		{
			mouseManager.setPressed(false);
			stateManager.setState(new MenuState(stateManager, starCycle, keyManager,mouseManager,width,height));
		}
		
		//System.out.println(rightArrow.isTouchingMouse(mouseManager.getMouseX(),mouseManager.getMouseY()));
		
		if(rightArrow.isTouchingMouse(mouseManager.getMouseX(),mouseManager.getMouseY()))
		{
			rightArrow.enlarge();
			mouseManager.setPressed(false);
		}
		else 
			rightArrow.shrink();
		
		if(leftArrow.isTouchingMouse(mouseManager.getMouseX(),mouseManager.getMouseY()))
		{
			leftArrow.enlarge();
			mouseManager.setPressed(false);
		}
		else
			leftArrow.shrink();
			
	}

	@Override
	public void render(Graphics g) 
	{
		starCycle.render(g);
		
		g.setColor(Color.GRAY);
		g.fillRect(190, 40, 420, pages.get(page).getHeight()+20);
		g.drawImage(pages.get(page),200,50,null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("New Courier",1,18));
		
		for(int i=0; i<text[page].size(); i++)
		{
			g.drawString(text[page].get(i), 100, 600 + i*20);
		}
		
		leftArrow.render(g);
		rightArrow.render(g);
		
		renderButton(g);
	}
	
	private void renderButton(Graphics g) 
	{
		backButton.draw(g);
		buttonSelected = backButton.mouseHoverHighlight(g,mouseManager.getMouseX(),mouseManager.getMouseY());	
	}

}
