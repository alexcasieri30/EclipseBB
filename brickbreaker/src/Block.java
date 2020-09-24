import processing.core.PApplet;


public abstract class Block implements Entity {
	public int row,col,x,y,dx,dy,w,h,r;
    public abstract boolean collide (Missle m); 
   }
 
