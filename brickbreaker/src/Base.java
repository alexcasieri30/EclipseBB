import processing.core.PApplet;


public class Base extends Block {
    public Base() {
    	x = 600; y=500; w=50;h=10;
    }
	@Override
	public void draw(PApplet p) {
		p.fill(0,255,0);
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

}
