/**
 * <h1>CommandBar Class</h1>
 * 
 * Created:6/23/17
 * Last Updated:9/19/17
 * 
 * <p>The Command Bar class manages the display at the bottom of the screen
 * when the game is running. These elements include the score, the amount of lives
 * and the gun overheat indicator.</p>
 * 
 * @author Lauryn Jefferson
 *
 */

package elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import audio.AudioManager;

public class CommandBar
{
	//variables
	private long score;
	private Ship ship;
	private OverheatBar overheatBar;
	
	private int width, height;
	
	private ArrayList<Heart> hearts;

	/**
	 * <h2> CommandBar constructor </h2>
	 * 
	 * @param ship the ship in which we want to display its stats
	 * @param width the width of the screen
	 * @param height the height of the screen
	 */
	public CommandBar(Ship ship, int width, int height)
	{
		this.ship = ship;
		this.width = width;
		this.height = height;
		
	
		overheatBar = new OverheatBar(10,height -30, Color.RED);
		hearts = new ArrayList<Heart>();
		
		//the hearts that represent the health of the ship
		hearts.add(new Heart(width/2 - 95,height-90));
		hearts.add(new Heart(width/2- 65,height-90));
		hearts.add(new Heart(width/2 - 35, height-90));
				
	}
	
	/**
	 * <h2>update() method</h2>
	 */
	public void update() 
	{
		//make sure the score is updated
		score = ship.getScore();
		
		
		if(ship.hasShot())
		{
			//update whether the overheat bar should be overheated
			overheatBar.setOverheat();
			AudioManager.playAudio("/audio/pew.wav");//play shooting audio
		}
		
		overheatBar.update();
		
		//if its overheated
		ship.overheat(overheatBar.isOverheated());

		//update how many hearts are displayed
		if(!hearts.isEmpty() && ship.getLives() < hearts.size())
			hearts.remove(hearts.size()-1);
	}


	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method draws everything to the
	 * 
	 * @param g The graphics object of the place we want to render to
	 */
	public void render(Graphics g) 
	{
		//big box
		g.setColor(Color.BLACK);
		g.fillRect(0, height - height/7, width, height/7);
		g.setFont(new Font("Courier New",1,45));
		
		//label box
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(5, height - height/7 + 5, width-10, height/18);
		
		//the label itself
		g.setColor(Color.BLACK);
		g.drawString("Command Bar",width/2 - g.getFontMetrics().stringWidth("Command Bar")/2,height - 70);
		g.setFont(new Font("Courier New",1,20));
		
		//score
		g.setColor(Color.WHITE);
		g.drawString("Score",width - g.getFontMetrics().stringWidth("Score") - 10,height -45);
		g.setFont(new Font("Courier New",1,45));
		g.drawString(((Long)score).toString(), width - g.getFontMetrics().stringWidth(((Long)score).toString()) - 10, height-10);
		
		//overheat bar
		g.setFont(new Font("Courier New",1,20));
		g.setColor(Color.ORANGE);
		g.drawString("Overheat",10,height -45);
		overheatBar.render(g);
		
		//hearts
		g.setColor(Color.RED);
		g.drawString("Lives",width/2 - g.getFontMetrics().stringWidth("Lives")/2,height -45);
		for(Heart h: hearts)
		{
			h.render(g);
		}
		
	}
	
	/**
	 * <h2>isOverheated() method</h2>
	 * 
	 * @return if the overheatBar object is in the state of being overheated
	 */
	public boolean isOverheated()
	{
		return overheatBar.isOverheated();
	}

}
