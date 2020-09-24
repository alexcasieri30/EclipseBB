import processing.core.PApplet;


public class Barrier extends Block{
    public Barrier (int inrow) 
    {
    	x = inrow*175; w= 50;  h=10;
    	
    	if (inrow % 2 == 1) {
			y = 400;
		} else {
			y = 300;
		}
    }
    
    public void moveRight()
    {
    	x += 5;
    }
    public void moveLeft()
    {
    	x -= 5;
    }
    
    
    @Override
	public void draw(PApplet p) {
		p.fill(0,0,255);
		p.rect(x, y, w, h);
	}

	@Override
	public boolean collide(Missle m) {
		if (m.x- m.w/2 > this.x + this.w) {return false;}  //left
		if (m.x+ m.w/2 < this.x  ) {return false;}  //right
		if (m.y- m.h/2 > this.y + this.h) {return false;}  //bottom
		if (m.y+ m.w/2 < this.y  ) {return false;}  //top
		return true;
	}

	@Override
	public void update() {
		 
		
	}
	public boolean collide(Target t) {
		if (t.x - t.h/2 > this.x + this.r) {
			return false;}  //left
		
		if (t.x + t.w/2 < this.x  ) {
			return false;}  //right
		
		if (t.y - t.h/2 > this.y + this.r) {
			return false;}  //bottom
		
		
		if (t.y+ t.w/2 < this.y) {
			return false;}  //top
		
		return true;
	}
}
