package graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
/**
 * Loads an image for us
 * 
 * @author Lauryn Jefferson
 * @version 1/13/17
 */
public class ImageLoader
{	
    public static BufferedImage loadImage(String path)
    {
        try
        {
        	return ImageIO.read(ImageLoader.class.getResource(path));
        	//return ImageLoader.class.getResourceAsStream(path);
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
    public static BufferedImage rotateImage( BufferedImage image )
    {
        int width  = image.getWidth();
        int height = image.getHeight();
        BufferedImage rotated = new BufferedImage( height, width, image.getType() );

        for( int i=0 ; i < width ; i++ )
            for( int j=0 ; j < height ; j++ )
                rotated.setRGB( height-1-j, i, image.getRGB(i,j) );

        return rotated;
    }
    
    public static BufferedImage enlargeImage(BufferedImage image,double scale)
    {
    	double width = scale * image.getWidth();
    	double height = scale * image.getHeight();
    	BufferedImage enlarged = new BufferedImage((int)width,(int)height,image.getType());
    	
    	for(int i = 0; i < width; i++)
    		for(int j = 0; j < height; j++)
    			enlarged.setRGB(i, j, image.getRGB((int)(i/scale),(int)(j/scale)));
    	
    	return enlarged;
    }
    
    public static BufferedImage shrinkImage(BufferedImage image, double scale)
    {
    	double width = scale / image.getWidth();
    	double height = scale / image.getHeight();
    	BufferedImage shrunk = new BufferedImage((int)width,(int)height,image.getType());
    	
    	for(int i = 0; i < width; i++)
    		for(int j = 0; j < height; j++)
    			shrunk.setRGB(i, j, image.getRGB((int)(i*scale),(int)(j*scale)));
    	
    	return shrunk;
    }

}
