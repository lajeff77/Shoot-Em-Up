package main;


import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import audio.AudioManager;
import elements.Score;
import graphics.Assets;
import graphics.StarCycle;
import states.MenuState;
import states.StateManager;

public class Game implements Runnable
{
	private int width, height;
    public String title;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    
    public StateManager stateManager;
    public KeyManager keyManager;  
    public MouseManager mouseManager;
    
    private StarCycle starCycle;
    
    private static ArrayList<Score> highScores;
    
    
    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager(); 
        starCycle = new StarCycle(height, height);
        mouseManager = new MouseManager();
        
        highScores = new ArrayList<Score>(10);
        readHighScores();
    }
    
    private void init()
    {
    	Assets.init();  
    	
        new Window(title, width, height);
        Window.getFrame().addKeyListener(keyManager);
        Window.getCanvas().addMouseListener(mouseManager);
        Window.getCanvas().addMouseMotionListener(mouseManager);
        
            
        AudioManager.init();
        stateManager = new StateManager();
        stateManager.setState(new MenuState(stateManager,starCycle,keyManager,mouseManager,width,height));
    }
    
    @Override
    /***
     * Holds the game loop.
     */
    public void run() 
    {
        //GameLoop
        init();
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            
            if(delta >= 1)
            {
                update();
                render();
                delta--;
            }

        }
    }
        
    public void update()
    {
        if(stateManager.getState() != null)
            stateManager.getState().update();
    }
        
    public void render()
    {
        bs = Window.getCanvas().getBufferStrategy();

        if(bs == null)
        {
            Window.getCanvas().createBufferStrategy(3);
            return;
        }
        
        g = bs.getDrawGraphics();
    

        //Clear screen
        g.clearRect(0, 0, width, height);

        //Draw here
        if(stateManager.getState() != null)
            stateManager.getState().render(g);
        
        bs.show();
        g.dispose();
    }

   /* public KeyManager getKeyManager()
    {
        return keyManager;
    }*/

    /***
     * Starts the thread for the game.
     */
    public synchronized void start()
    {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
        
    }
    
    /***
     * Shuts down the thread for the game.
     */
    public synchronized void stop()
    {
        if(!running)
            return;
        running = false;
        
        saveHighScores();
    }
    
    private void readHighScores()
    {
    	String filename = "C:/Users/lajef/Desktop/Shoot 'Em Up!/Shoot 'Em Up!/resources/files/SEUHighScores.txt";
    	Scanner scanner = null;
    	
    	try {
			scanner = new Scanner(new File(filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	while(scanner.hasNext())
    	{
        	String name = scanner.next();
        	String score = scanner.next();
        	
        	highScores.add(new Score(name,Long.valueOf(score)));
    	}
    	
    	scanner.close();
    	saveHighScores();
    }
    
    public static void saveHighScores()
    {
    	Formatter formatter = null;
    	String filename = "C:/Users/lajef/Desktop/Shoot 'Em Up!/Shoot 'Em Up!/resources/files/SEUHighScores.txt";
    	
    	try{
    		formatter = new Formatter(filename);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	for(Score s: highScores)
    		formatter.format("%s %s ", s.getName(),s.getScore());
    	
    	formatter.close();
    		
    }
    
    public static ArrayList<Score> getHighScores()
    {
    	return highScores;
    }
    
    public static void newHighScore(Score newScore)
    {
    	highScores.add(newScore);
    	insertionSort(highScores);
    	highScores.remove(highScores.size()-1);
    	saveHighScores();
    }
    
    private static void insertionSort(ArrayList<Score> list)
    {
        for (int outer = 1; outer < list.size(); outer++)
        {
            int position = outer;
            Score key = list.get(outer);

            while (position > 0 && list.get(position -1).compareTo(key) < 0)
            {
                list.set(position,list.get(position - 1));
                position--;
            }
            
            list.set(position, key);
            
        } 
    }

}
