package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Arrow 
{
	private final double SCALE = 2.0;
	private int x, y;
	private BufferedImage image;
	private BufferedImage enlarged;
	private BufferedImage shrunk;
	
	
	public Arrow(BufferedImage image, int x, int y)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		shrunk = image;
		enlarged = ImageLoader.enlargeImage(image, SCALE);
		
	}

	public void enlarge()
	{
		image = enlarged;
		resetX();
		resetY();
	}
	
	public void shrink()
	{
		image = shrunk;
		resetX();
		resetY();
	}
	
	private void resetX()
	{
		if(x > image.getWidth())
			x = 790 - image.getWidth();	
	}
	
	private void resetY()
	{
		y = 726-image.getHeight()/2;
	}
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(image,x,y,null);
		/*g.setColor(Color.RED);
		g.drawRect(x, y+ image.getHeight()/3, image.getWidth(), image.getHeight()/3);*/
	}
	
	public boolean isTouchingMouse(int mouseX, int mouseY)
	{
		return mouseX > x && mouseX < x + image.getWidth()&& mouseY > y + image.getHeight()/3 && mouseY < y+(image.getHeight()*2)/3;
	}
}
