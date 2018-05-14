/**
 * <h1>GameAudio Class</h1>
 * 
 * Created:6/25/17
 * Last Updated:11/11/17
 * 
 * <p>The game audio class is meant to handle all of the audio aspects of the game.
 * within the class you can play an audio clip once, start an audio loop, and stop an
 * audio loop. In order to play audio or start an audio loop you must provide a valid
 * path in the form of a String object. If the path provided is not valid, the loop
 * or the clip will not play.</p>
 * 
 * @author Lauryn Jefferson
 *
 */

package audio;

import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager
{
	public static LinkedList<Audio> loops;//to hold the clips we want to loop
	private static int loopID = 0;//to make sure that every loops has a unique form of identification
	
	/**
	 * <h2>init() method</h2>
	 * 
	 * <p>This static method must be called once before the usage of the GameAudio
	 * class in order to properly set up the GameAudio class for proper usage. 
	 * Failure to initialize the class will cause errors further along if you
	 * attempt to play or stop an audio loop. This however will not effect audio that
	 * only plays once or audio that has a set amount of loops.</p>
	 */
	public static void init()
	{
		loops = new LinkedList<Audio>();
	}
	
	/**
	 *<h2>playAudio</h2>
	 *
	 *<p>This static method takes in one parameter which is a file path.
	 *It then takes the path, opens the file, and plays the audio in that
	 *file one time. If the path provided is not valid and the program 
	 *fails to open the file, the audio will not sound at all.</p>
	 *
	 * @param path The path should be a String object of the audio file to be played
	 */
	public static void playAudio(String path)
	{
		try 
	    {
			//try to open the audio file
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(AudioManager.class.getResourceAsStream(path));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        //play the clip once
	        clip.start();
	    } 
	    catch(Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * <h2>playAudioLoop() method</h2>
	 * 
	 * <p>This static method opens the audio file with the specified 
	 * path and plays it continuously in a loop.If the path provided 
	 * is not valid and the program fails to open the file, the loop 
	 * will not sound at all. The method will return an integer value
	 * that signifies the id of the audio loop inside the class so you
	 * can later stop the loop if you so please. If the file fails to
	 * open, you will receive a -1 for the id.</p>
	 * 
	 * @param path The path should be a String object of the audio file to be looped
	 * 
	 * @return Returns the id of the audio loop so it can be stopped later on
	 */
	public static int playAudioLoop(String path)
	{
		return loopClip(path,Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * <h2>playAudioLoop() method()</h2>
	 * 
	 * <p>This static method opens the audio specified by the path and loops it
	 * for the defined amount of times. If the path provide is not valid and the 
	 * program fails to open the file, the audio will not be played. The method 
	 * will return an integer value that represents the an id that you can use
	 * to stop the audio if you need to before its done looping a certain amount of
	 * times. If the file is not opened you will receive a -1 for the id.</p>
	 * 
	 * @param path The path should be a String object of  the audio file to be looped
	 * @param amountOfLoops The desired amount of times you want the audio to be looped
	 * 
	 * @return Returns the id of the audio loop so it can be stopped later on if necessary
	 */
	public static int playAudioLoop(String path,int amountOfLoops)
	{
		return loopClip(path,amountOfLoops);
	}
	
	/**
	 * <h2>loopClip() method</h2>
	 *
	 * <p>This is a helper method to aid the playAudioLoop methods. Since the 
	 * two methods are nearly identical besides the amount of times it should be looped
	 * (or infinity) then this one was created in order to simplify the code. Refer to
	 * the afore mentioned methods to find what the functionality of this method is.</p>
	 * 
	 * @param path The string for the location of the file
	 * @param loopTimes The amount of times it should be looped, whether it should be user defined of looped 

	 * @return Returns the id of The audio loop so it can be stopped later on if necessary
	 */
	private static int loopClip(String path, int loopTimes)
	{
		try
		{
			//try to open the audio file
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(AudioManager.class.getResourceAsStream(path));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        //play it but continuously
	        clip.loop(loopTimes);
	        loops.add(new Audio(loopID, clip));
			
		}
		catch(Exception e)
		{
			//error opening the file
			e.printStackTrace();
			return -1;
		}
		return loopID++;
	}
	
	/**
	 * <h2>stopAudioLoop() method</h2>
	 * 
	 * <p>This static method stops an audio loop using the specified id.
	 * The id will always be specific to an instance of an audio loop. You
	 * can receive this id as a return from the method you created it with.
	 * This method only works specifically with audio that you have looped.
	 * If you stop an audio loop, the audio loop will be thrown out and you
	 * can't use the id ever again. If you wish to stop the loop and resume
	 * progress, you must use the pauseAudioLoop() method.</p>
	 * 
	 * @param id the uniquely generated id from the creation of the loop must be passed in to be able to
	 */
	public static void stopAudioLoop(int id)
	{
		for(int i = 0; i < loops.size(); i++)
		{
			if(loops.get(i).getID() == id)//check to make sure if its the unique clip
			{
				loops.get(i).getClip().stop();//shut down the audio
				loops.remove(i);//take it out of the list
			}
		}
	}
	
	/**
	 * <h2>puaseAudioLoop() method</h2>
	 * 
	 * <p>Given the specific id to the audio loop, the audio can be paused.
	 * If you don't provided the valid audio loop id then the audio will not 
	 * be paused at all. You can then resume the audio loop from when it stopped
	 * using the resumeAudioLoop() method with it's ID as this method saves the 
	 * spot where it ended in order to allow the clip to be resumed.</p>
	 * 
	 * @param id The specific indentifiction associateed with the audio loop you wish to pause
	 */
	public static void pauseAudioLoop(int id)
	{
		for(int i = 0; i < loops.size(); i++)
		{
			if(loops.get(i).getID() == id)
			{
				loops.get(i).pauseClip();
			}
		}
	}
	
	public static void resumeAudioLoop(int id)
	{
		for(int i = 0; i < loops.size(); i++)
		{
			if(loops.get(i).getID() == id)
			{
				loops.get(i).resumeClip();
			}
		}
	}
}