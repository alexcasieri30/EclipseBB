import processing.core.PApplet;

public class Ball {
	public int xpos, ypos, dx,dy, width,height, rcolor,gcolor,bcolor;
	public Ball(int ixpos,int iypos, int idx, int idy, int iwidth,
			int iheight, int ircolor, int igcolor, int ibcolor) {
		xpos=ixpos; ypos=iypos;  dx=idx;
		dy=idy; width= iwidth; height = iheight;
		rcolor=ircolor; gcolor=igcolor; bcolor=ibcolor;
	} 
	public boolean collides (Ball b1) {
		double dist = Math.sqrt(Math.pow(this.xpos - b1.xpos, 2.0) 
				+ Math.pow(this.ypos - b1.ypos, 2.0));
		if (dist < this.width/2*2) { 
			return true;
		} else {
			return false;
		}
	}
	public void update(int nCols, int nRows) {
		if (xpos+width/2 >= nCols){// accx=ounts for size of ball
			dx-=-dx; // travels in other direction	
		}		
		if ( xpos-width/2  <= 0){// accounts for size of ball
			dx=-dx; // travels in other direction	
		}
		xpos +=  dx;	
		if ( ypos+ height/2 >= nRows){// accounts for size of ball
			dy=-dy; // travels in other direction	
		}
		if (ypos-height/2 <= 0){// accounts for size of ball
			dy=-dy; // travels in other direction	
		}
		ypos += dy;
	}
	public void draw(PApplet p ) {
		p.fill(rcolor,gcolor,bcolor);
		p.ellipse(xpos,ypos,width,height);
	}
}
