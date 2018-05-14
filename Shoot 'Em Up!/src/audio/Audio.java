/**
 * <h1>Audio Class</h1>
 * 
 * Created:11/8/17
 * Last Updated:11/17/17
 * 
 * <p>The audio class is a helper to the AudioManager.
 * It helps to encapsulate the audio in the audio manager and
 * insure that we can stop and resume audio freely from other classes.</p>
 * 
 * @author Lauryn Jefferson
 *
 */

package audio;

import javax.sound.sampled.Clip;

class Audio
{
	private int id;
	private Clip clip;
	private long pauseTime;

	/**
	 * <h2>Audio() Constructor</h2>
	 * 
	 * <p>The constructor sets up an audio so it can be paused and resumed.
	 * It also holds an id so that we can identify it when we need it later.</p>
	 * 
	 * @param id The significant identifier to which audio clip this is.
	 * @param clip The audio clip that is represented by the id.
	 */
	public Audio(int id, Clip clip)
	{
		this.id = id;
		this.clip = clip;
		pauseTime = 0;
	}
	
	/**
	 * <h2>getID() method</h2>
	 * 
	 * <p>This method returns the id of the audio.</p>
	 * 
	 * @return id of the audio.
	 */
	public int getID()
	{
		return id;
	}
	
	 
	/**
	 *<h2>getClip() method<h2>
	 * 
	 * <p>This method returns the clip associated with the id.</p>
	 * 
	 * @return clip for the id.
	 */
	public Clip getClip()
	{
		return clip;
	}
	
	/**
	 * <h2>pauseClip() method</h2>
	 * 
	 * <p>This method will stop the clip of audio and take 
	 * note where the audio was paused.</p>
	 */
	public void pauseClip()
	{
		pauseTime= clip.getMicrosecondPosition();
		clip.stop();
	}
	
	/**
	 * <h2>resumeClip() method</h2>
	 * 
	 * <p>This method will pick up from the most recent 
	 * time stamp from the pause. Failure to pause before you resume the clip
	 * will either result in the clip restarting or the clip starting from the
	 * most recent pause of the audio.</p>
	 */
	public void resumeClip()
	{
		clip.setMicrosecondPosition(pauseTime);
		clip.start();
	}
}
