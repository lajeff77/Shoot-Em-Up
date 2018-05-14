package graphics;

import java.awt.image.BufferedImage;

/**
 * This class holds the SpriteSheet as the whole. 
 * The class can also crop the sprite sheet images to their wanted locations
 * 
 * @Author Lauryn Jefferson
 * @Version Mar 19, 2017
 */
public class SpriteSheet
{
    private BufferedImage img;

    /**
     * Constructor for objects of class SpriteSheet. This will save the image recieved from the 
     * Imageloader class in a local variable.
     * 
     * param: Buffered image with SpriteSheet
     */
    public SpriteSheet(BufferedImage image)
    {
        img = image;
    }

    /**
     * This method will return a croped version of the original image
     * This will be used to organize images into actions
     * 
     * @param      int X, Y location of the wanted image
     * @return     BufferedImage with the croped portion
     */
    public BufferedImage crop(int x, int y, int width, int height)
    {
        return img.getSubimage(x,y,width,height);
    }
}
