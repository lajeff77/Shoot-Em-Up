package graphics;

import java.awt.image.BufferedImage;

/**
 * <h1>Animation Class</h1>
 *
 *@author Lauryn 
 *@version 5/19/17
 *
 *
 *The animation class takes a list of Buffered Images in order to create and animation for the Players at
 *a specified speed. The use of the ArrayList provides flexibility in this realm to easily add and remove
 *frames in the animation when needed.
 *

 *
 */
public class Animation 
{
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	/**
	 * <h2>Animation Constructor</h2>
	 * 
	 * When animation object is made it's speed and frames are set.
	 * 
	 * @param speed Has the speed at which the frames will be animated in terms of milliseconds it should take to change frames.
	 * @param standB Holds each individual Buffered Image in an ArrayList for the action being animated.
	 */
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();//what time the new Animation was made
	}
	
	/**
	 * 
	 * <h2>Animation: update()</h2>
	 * 
	 * Keeps track of what frame the animation needs to be on. Must be updated when the animation is playing within the game.
	 */
	public void update()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed)
		{
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	
	
	/**
	 * <h2>Animation: getCurrentFrame()</h2>
	 * 
	 * @return Returns a Buffered Image that is the correct frame that needs to be displayed based on the timer in the update() method.
	 */
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}

}