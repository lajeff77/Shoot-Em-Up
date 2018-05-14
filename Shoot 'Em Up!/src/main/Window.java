package main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

import graphics.Assets;

public class Window 
{
	private static JFrame frame;
	private static Canvas canvas;
	
	private String title;
	private static int width;
	private static int height;
	
	//private BufferedImage icon;
	
	public Window(String title, int width, int height)
	{
		this.title = title;
		Window.width = width;
		Window.height = height;
		
		init();
	}
	
	private void init()
	{
		
		
		frame = new JFrame(title);
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		 frame.setIconImage(Assets.shipIcon);
		 
		canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
	}

	public static JFrame getFrame() 
	{
		return frame;
	}

	public static Canvas getCanvas() 
	{
		return canvas;
	}
	
	public static int getWidth()
	{
		return width;
	}
	
	public static int getHeight()
	{
		return height;
	}
}
