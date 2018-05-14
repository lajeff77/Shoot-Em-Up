package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
	private int mouseX;
	private int mouseY;
	
	private boolean pressed;
	private boolean clicked;
	
	public MouseManager()
	{
		mouseX = 0;
		mouseY = 0;
		
		pressed = false;
		clicked = false;
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		clicked = true;
		//System.out.println("Click");
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		pressed = true;
		//System.out.println("Press");
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		pressed = false;
		clicked = false;
		//System.out.println("Released");
		
	}
	
	public int getMouseX()
	{
		return mouseX;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();	
	}
	
	public boolean getClicked()
	{
		return clicked;
	}
	
	public boolean getPressed()
	{
		return pressed;
	}
	
	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
	}
	
	public void setPressed(boolean pressed)
	{
		this.pressed = pressed;
	}
}
