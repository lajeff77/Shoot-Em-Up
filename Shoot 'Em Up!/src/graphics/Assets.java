package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets 
{
	public static final int WIDTH = 128, HEIGHT = 128;
	
	public static BufferedImage shipIcon;
	public static BufferedImage stillShip;
	public static BufferedImage[] ship;
	public static BufferedImage rock;
	public static BufferedImage bullet;
	public static BufferedImage[] explosion;
	public static BufferedImage heart;
	public static ArrayList<BufferedImage> pages;
	
	public static void init()
	{
		SpriteSheet sheet  = new SpriteSheet(ImageLoader.loadImage("/images/Shoot 'Em Up Sheet.png"));
		ship = new BufferedImage[3];
		explosion = new BufferedImage[2];
		pages = new ArrayList<BufferedImage>();
		
		stillShip = sheet.crop(0,0,WIDTH,HEIGHT);
		
		ship[0] = sheet.crop(WIDTH,0,WIDTH,HEIGHT);
		ship[1] = sheet.crop(WIDTH*2,0,WIDTH,HEIGHT);
		ship[2] = sheet.crop(0,HEIGHT,WIDTH,HEIGHT);
		
		rock = sheet.crop(WIDTH,HEIGHT,WIDTH,HEIGHT);
		
		bullet = sheet.crop(WIDTH*2,HEIGHT,WIDTH,HEIGHT);
		
		explosion[0] = sheet.crop(0,HEIGHT*2,WIDTH,HEIGHT);
		explosion[1] = sheet.crop(WIDTH,HEIGHT*2,WIDTH,HEIGHT);
		
		heart = sheet.crop(WIDTH*2, HEIGHT*2, WIDTH, HEIGHT);
		
		pages.add(ImageLoader.loadImage("/images/screenshots/Instructions1.png"));
		pages.add(ImageLoader.loadImage("/images/screenshots/Instructions2.png"));
		pages.add(ImageLoader.loadImage("/images/screenshots/Instructions3.png"));
		pages.add(ImageLoader.loadImage("/images/screenshots/Instructions4.png"));
		pages.add(ImageLoader.loadImage("/images/screenshots/Instructions5.png"));
		pages.add(ImageLoader.loadImage("/images/screenshots/Instructions6.png"));
		
		shipIcon = ImageLoader.loadImage("/images/Ship Logo.png");
	
	}
	
}