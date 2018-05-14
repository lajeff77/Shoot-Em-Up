package main;


/**
 * The KeyManager class implements KeyListener and is set to notice whenever a player jumps, walks, ducks, kicks, or punches.
 * We also have the enter button that can be used as well.
 * This site helped me make the class http://stackoverflow.com/questions/21711096/java-event-listeners-mouse-and-keyboard
 * @author Catherine Dennis 
 * @version 5/22/17
 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class KeyManager implements KeyListener
{
    //booleans that hold the players actions
    public boolean up,down,left,right,shoot, enter, backspace;
    public boolean a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
   
    /**
     * Constructor for objects of class KeyManager sets every instance variable boolean to false.
     */
    public KeyManager()
    {
       up = down = left = right = shoot = enter = backspace = false;
       a = b = c = d = e = f = g = h = i = j = k = l = m = n = o = p = q = r = s = t = u = v = w = x = y = z = false;
    }

    /**
     * when the key is pressed we find the key that is pressed and set it to be true
    */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode(); 

        
        if(key == KeyEvent.VK_UP)
        {
        	shoot=true;
        	up = true;
        }
            
        if(key == KeyEvent.VK_DOWN)
        	down = true;

        if(key == KeyEvent.VK_LEFT)
            left=true;
    
        if(key == KeyEvent.VK_RIGHT)
            right=true;
        
        if(key == KeyEvent.VK_ENTER)
        	enter = true;
        
        if(key == KeyEvent.VK_BACK_SPACE)
        	backspace = true;
        
        if(key == KeyEvent.VK_A)
        {
        	a = true;
        	left = true;
        }
        
        if(key == KeyEvent.VK_B)
        	b = true;
        
        if(key == KeyEvent.VK_C)
        	c = true;
        
        if(key == KeyEvent.VK_D)
        {
        	d = true;
        	right = true;
        }
        
        if(key == KeyEvent.VK_E)
        	this.e = true;
        
        if(key == KeyEvent.VK_F)
        	f = true;
        
        if(key == KeyEvent.VK_G)
        	g = true;
        
        if(key == KeyEvent.VK_H)
        	h = true;
        
        if(key == KeyEvent.VK_I)
        	i = true;
        
        if(key == KeyEvent.VK_J)
        	j = true;
        
        if(key == KeyEvent.VK_K)
        	k = true;
        
        if(key == KeyEvent.VK_L)
        	l = true;
        
        if(key == KeyEvent.VK_M)
        	m = true;
        
        if(key == KeyEvent.VK_N)
        	n = true;
        
        if(key == KeyEvent.VK_O)
        	o = true;
        
        if(key == KeyEvent.VK_P)
        	p = true;
        
        if(key == KeyEvent.VK_Q)
        	q = true;
        
        if(key == KeyEvent.VK_R)
        	r = true;
        
        if(key == KeyEvent.VK_S)
        {
        	s = true;
        	down = true;
        }
        
        if(key == KeyEvent.VK_T)
        	t = true;
        
        if(key == KeyEvent.VK_U)
        	u = true;
        
        if(key == KeyEvent.VK_V)
        	v = true;
        
        if(key == KeyEvent.VK_W)
        {
        	w = true;
        	up = true;
        	shoot = true;
        }
        
        if(key == KeyEvent.VK_X)
        	x = true;
        
        if(key == KeyEvent.VK_Y)
        	y = true;
        
        if(key == KeyEvent.VK_Z)
        	z = true; 
        
    }

    

    /**
     *when the key is released we set the variable back to false
     * 
    */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
           
        //blue character jumps
        if(key==KeyEvent.VK_UP)
        {
            shoot=false;
            up = false;
        }
        
        if(key == KeyEvent.VK_DOWN)
        	down = false;
       
        //blue character walks left
        if(key==KeyEvent.VK_LEFT)
            left=false;
        
        //blue character walks right     
        if(key==KeyEvent.VK_RIGHT)
            right=false; 
        
        if(key == KeyEvent.VK_ENTER)
        	enter = false;
        
        if(key == KeyEvent.VK_BACK_SPACE)
        	backspace = false;
        
        if(key == KeyEvent.VK_A)
        {
        	a = false;
        	left = false;
        }
        
        if(key == KeyEvent.VK_B)
        	b = false;
        
        if(key == KeyEvent.VK_C)
        	c = false;
        
        if(key == KeyEvent.VK_D)
        {
        	d = false;
        	right = false;
        }
        
        if(key == KeyEvent.VK_E)
        	this.e = false;
        
        if(key == KeyEvent.VK_F)
        	f = false;
        
        if(key == KeyEvent.VK_G)
        	g = false;
        
        if(key == KeyEvent.VK_H)
        	h = false;
        
        if(key == KeyEvent.VK_I)
        	i = false;
        
        if(key == KeyEvent.VK_J)
        	j = false;
        
        if(key == KeyEvent.VK_K)
        	k = false;
        
        if(key == KeyEvent.VK_L)
        	l = false;
        
        if(key == KeyEvent.VK_M)
        	m = false;
        
        if(key == KeyEvent.VK_N)
        	n = false;
        
        if(key == KeyEvent.VK_O)
        	o = false;
        
        if(key == KeyEvent.VK_P)
        	p = false;
        
        if(key == KeyEvent.VK_Q)
        	q = false;
        
        if(key == KeyEvent.VK_R)
        	r = false;
        
        if(key == KeyEvent.VK_S)
        {
        	s = false;
        	down = false;
        }
        
        if(key == KeyEvent.VK_T)
        	t = false;
        
        if(key == KeyEvent.VK_U)
        	u = false;
        
        if(key == KeyEvent.VK_V)
        	v = false;
        
        if(key == KeyEvent.VK_W)
        {
        	w = false;
        	up = false;
        	shoot = false;
        }
        
        if(key == KeyEvent.VK_X)
        	x = false;
        
        if(key == KeyEvent.VK_Y)
        	y = false;
        
        if(key == KeyEvent.VK_Z)
        	z = false; 
    }

    /**
    * when a key is typed we notice it here and set the boolean representing the key to true.
    */
    @Override
    public void keyTyped(KeyEvent e)
    {
     int key = e.getKeyCode(); 

        
        if(key == KeyEvent.VK_UP)
        {
        	shoot=true;
        	up = true;
        }
            
        if(key == KeyEvent.VK_DOWN)
        	down = true;

        if(key == KeyEvent.VK_LEFT)
            left=true;
    
        if(key == KeyEvent.VK_RIGHT)
            right=true;
        
        if(key == KeyEvent.VK_ENTER)
        	enter = true;
        
        if(key == KeyEvent.VK_BACK_SPACE)
        	backspace = true;
        
        if(key == KeyEvent.VK_A)
        {
        	a = true;
        	left = true;
        }
        
        if(key == KeyEvent.VK_B)
        	b = true;
        
        if(key == KeyEvent.VK_C)
        	c = true;
        
        if(key == KeyEvent.VK_D)
        {
        	d = true;
        	right = true;
        }
        
        if(key == KeyEvent.VK_E)
        	this.e = true;
        
        if(key == KeyEvent.VK_F)
        	f = true;
        
        if(key == KeyEvent.VK_G)
        	g = true;
        
        if(key == KeyEvent.VK_H)
        	h = true;
        
        if(key == KeyEvent.VK_I)
        	i = true;
        
        if(key == KeyEvent.VK_J)
        	j = true;
        
        if(key == KeyEvent.VK_K)
        	k = true;
        
        if(key == KeyEvent.VK_L)
        	l = true;
        
        if(key == KeyEvent.VK_M)
        	m = true;
        
        if(key == KeyEvent.VK_N)
        	n = true;
        
        if(key == KeyEvent.VK_O)
        	o = true;
        
        if(key == KeyEvent.VK_P)
        	p = true;
        
        if(key == KeyEvent.VK_Q)
        	q = true;
        
        if(key == KeyEvent.VK_R)
        	r = true;
        
        if(key == KeyEvent.VK_S)
        {
        	s = true;
        	down = true;
        }
        
        if(key == KeyEvent.VK_T)
        	t = true;
        
        if(key == KeyEvent.VK_U)
        	u = true;
        
        if(key == KeyEvent.VK_V)
        	v = true;
        
        if(key == KeyEvent.VK_W)
        {
        	w = true;
        	up = true;
        	shoot = true;
        }
        	
        
        if(key == KeyEvent.VK_X)
        	x = true;
        
        if(key == KeyEvent.VK_Y)
        	y = true;
        
        if(key == KeyEvent.VK_Z)
        	z = true; 
        
    }

}
