import processing.core.PApplet;

public class bomb extends Block implements Entity
{
	int x, y, w, h;
	
	public bomb(int radius, int inx, int iny)
	{
		x = inx;
		y = iny;
		w = h = radius;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void draw(PApplet p) {
		p.fill(255, 150, 0);
		p.ellipse(x, y, w, h);
		
	}
	
	public void blowup()
	{
		
		if (w >= 150)
		{
			this.disappear();
		}
		else
		{
			h += 15;
			w += 15;
		}
	}
	
	public void disappear()
	{
		w = 0;
		h = 0;
		x = 0;
		y = 0;
	}
	public int getRadius()
	{
		return w;
	}

	
	public boolean collide(Missle m) 
	{
		if (m.x - m.w/2 > this.x + this.w) {
			return false;}  //left
		
		if (m.x + m.w/2 < this.x  ) {
			return false;}  //right
		
		if (m.y - m.h/2 > this.y + this.h) {
			return false;}  //bottom
		
		
		if (m.y+ m.w/2 < this.y) {
			return false;}  //top
		
		return true;
	}
}
