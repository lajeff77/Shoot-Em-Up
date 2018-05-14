package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * <h1>Button Class</h1>
 * 
 * Created://
 * Last Updated:9/1/17
 * 
 * <p>The button class is a class that sets up a square at a certain position that can be highlighted.</p>
 * 
 * @author Lauryn Jefferson
 *
 */
public class Button 
{
	//private Window window;
	private Color shade,body;
    private String label;
    
    private int width, originalWidth;
    private int height, originalHeight;
    private int x,y;
    private int originalX, originalY;
    private int id; //the id we gave the button to tell if it's selected  
    
    private double xScale, yScale;
    //private double originalScreenWidth, originalScreenHeight;
    /**
    *   Takes a string which is the label on the button, x coordinate, y coordinate, and in identifying id number
    */
    public Button(Color shade, Color body, String label, int x, int y,int width, int height,int id)
    {          
       	this.shade = shade;
       	this.body = body;
    	this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        
        originalX = x;
        originalY = y;
        
        originalWidth = width;
        originalHeight = height;
        
       //originalScreenWidth = window.getWidth();
       //originalScreenHeight = window.getHeight();
    }

    public void update()
    {
    	//xScale = window.getWidth()/originalScreenWidth;
    	//yScale = window.getHeight()/originalScreenHeight;
    	
    	x = (int)(originalX*xScale);
    	y = (int)(originalY*yScale);
    	
    	width = (int)(originalWidth*xScale);
    	height = (int)(originalHeight*yScale);
    }
    
    
    /**
    * Draws the image of the button onto the canvas. 
    * It gets passed a graphics object 
    */
    public void draw(Graphics g)
    {
        Font myFont = new Font ("Courier New", 1, 17);
        g.setFont(myFont);
        g.setColor(shade);     
        g.fillRect(x,y,width,height);
        g.setColor(body);
        g.fillRect(x+3, y+3, width-6, height-6);
        g.setColor(Color.BLACK);
        g.drawString(label,(x+(width/2))-(int)(g.getFontMetrics().stringWidth(label)/2),y+height/2);            
    }
    
    /**
     * returns true if the item selected in MenuState is equal to 
     * the id given to the button
     */  
    public boolean isHighlighted(int id)
    {        
        return this.id == id;
    }
    

    /**
     *  higlights the button that has an id equal to the selected id 
    */
    public void highlight(Graphics g)
    {
    	 Font myFont = new Font ("Courier New", 1, 17);
         g.setFont(myFont);
         g.setColor(shade);     
         g.fillRect(x,y,width,height);
         g.setColor(Color.WHITE);
         g.fillRect(x+3, y+3, width-6, height-6);
         g.setColor(Color.BLACK);
         g.drawString(label,(x+(width/2))-(int)(g.getFontMetrics().stringWidth(label)/2),y+height/2);
    }
    
    public boolean mouseHoverHighlight(Graphics g,int mouseX, int mouseY)
    {
    	if(mouseX > x && mouseY > y && mouseX < x+width && mouseY < y+height)
    	{
    		if(g!=null)
    			highlight(g);
    		return true;
    	}
    	return false;
    }
    
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    
    public int getWidth()
    {
    	return width;
    }
    
    public int getHeight()
    {
    	return height;
    }
}
