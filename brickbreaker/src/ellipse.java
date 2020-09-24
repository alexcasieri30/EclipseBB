import processing.core.PApplet;

public abstract class ellipse implements Entity{

	int x,y,dx,dy,w,h, nRows, nCols;

	public ellipse()
	{
		this(25, 25);
	}
	public ellipse(int inwidth, int inheight)
	{
		w = inwidth;
		h = inheight;
	}
	public abstract void update();
	public void draw(PApplet p) 
	{
		p.fill(10,10,50);
		p.ellipse(x,y,w,h);
	}
}
