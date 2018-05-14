package states;

import java.awt.Graphics;
import audio.AudioManager;
import elements.CommandBar;
import elements.PauseMenu;
import elements.RockManager;
import elements.Ship;
import graphics.StarCycle;
import main.Game;
import main.KeyManager;
import main.MouseManager;

public class GameState extends State
{
	private Ship ship;
	
	private RockManager rockManager;
	private CommandBar commandBar;
	
	private PauseMenu pauseMenu;
	
	private int musicID;
	
	private int difficulty;
	
	private boolean paused;
	private boolean musicOn = true;
	
	public GameState(StateManager stateManager, StarCycle starCycle,KeyManager keyManager, MouseManager mouseManager, int width, int height, int difficulty)
	{
		super(stateManager,starCycle,keyManager, mouseManager, width, height);
		ship = new Ship(keyManager,width,height);
		
		this.difficulty = difficulty;
		
		rockManager = new RockManager(ship,difficulty,width,height);
		commandBar = new CommandBar(ship,width,height);
		
		pauseMenu = new PauseMenu(stateManager,starCycle,keyManager,mouseManager,width,height,this);
		
		paused = false;
		
		if(musicOn)
			musicID = AudioManager.playAudioLoop("/audio/ShootEmUpSong.wav");
	}
	
	
	@Override
	public void update() 
	{
		if(keyManager.p && !ship.isGameOver())
		{
			paused = !paused;
			
			keyManager.p = false;
		}
		
		if(paused && pauseMenu.getUnpaused() == true)
		{
			paused = false;
			pauseMenu.setUnpaused(false);
		}
		if(!ship.isGameOver())
			pauseMenu.update();
		
		starCycle.update();
		
		if(!paused)
		{

			ship.overheat(commandBar.isOverheated());

			ship.update();

			rockManager.update();

			commandBar.update();


			if(ship.isGameOver())
			{
				if(musicOn)
					AudioManager.stopAudioLoop(musicID);
				if(ship.getScore() > Game.getHighScores().get(Game.getHighScores().size()-1).getScore())
					stateManager.setState(new NewHighScoreState(stateManager,starCycle,keyManager,mouseManager,width,height,ship.getScore(),difficulty));
				else
					stateManager.setState(new GameOverState(stateManager,starCycle,keyManager,mouseManager,width,height,this,difficulty));
			}
		}
			
	}

	@Override
	public void render(Graphics g)
	{	
		starCycle.render(g);
		
		ship.render(g);
		
		rockManager.render(g);
		
		commandBar.render(g);
		
		if(paused)
		{
			pauseMenu.render(g);
		}
		
	}
	
	public Ship getShip()
	{
		return ship;
	}

	public boolean isMusicOn()
	{
		return musicOn;
	}
	
	public void toggleMusic()
	{
		if(musicOn)
		{
			AudioManager.stopAudioLoop(musicID);
			musicOn = false;
		}	
		else
		{
			musicID = AudioManager.playAudioLoop("/audio/ShootEmUpSong.wav");
			musicOn = true;
		}
	}
}
