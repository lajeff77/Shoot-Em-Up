package elements;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameElement 
{
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getCollisionBox();
}
