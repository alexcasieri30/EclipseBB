import java.util.*;
import processing.core.PApplet;

public class MainClass extends PApplet {
	public int nCols = 1200, nRows = 600;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	Block b; Target t; Missle m; Barrier barr; Base base; bomb bomb1; int colremove; int iterindex = 0;
	bomb b1; int count;

	public static void main(String args[])
	{
		PApplet.main("MainClass");
	}
	public void settings()
	{
		size(nCols,nRows);
	}
	public void setup()
	{
		Random r = new Random(10);
		int mrow = 0;
		int mcol;
		int x, y;
		for (int row  =1; row<=5; row++ ) {
			mrow++;
			mcol = 0;
			for (int col  =1; col<=10; col++ ) {
				t= new Target(row,col);
				mcol++;
				entities.add(t);
				t.setRow(mrow);
				t.setCol(mcol);
			}
		}
		for (int i = 0; i < 8; i++) {
			y = r.nextInt(250) + 50;
			x = r.nextInt(1000);
			//code for making bombs
			bomb1 = new bomb(25, x, y);
			entities.add(bomb1);
		}
		for (int row = 1; row<=6; row++) {
			barr= new Barrier(row);  
			entities.add(barr);
		}
		base = new Base();
		m = new Missle(base,nCols,nRows);
		entities.add(base);
		entities.add(m);
	}
	// end setup
	public void draw()
	{ 
		EdgeCheck();  // blocks change direction when hitting edge
		for (Entity myentity: entities) {
			// for an arraylist returns items in index order
			myentity.update() ;
		}
		
		//code for when missle hits targets
		for (Entity myentity: entities) {
			if (myentity instanceof  Block  )  {
				b = (Block) myentity;
				if (b.collide(m)) {
					if (b instanceof Target) {
						colremove = ((Target)b).getCol();
						entities.remove(myentity);
						if (m.dy <0) {m.setOnBase();} 
						else {m.dy=-m.dy; }
						break; 
					}
					if (b instanceof Barrier) {
						m.dx = -m.dx; 
						m.dy=-m.dy; 
					}
					if (b instanceof Base) { m.setOnBase();}
				}
			}
			if (myentity instanceof bomb) {	//code for exploding bombs
				if (((bomb)myentity).collide(m)) {
					((bomb)myentity).blowup();
				}
			}
		}
		background(200);
		for (Entity myentity: entities) {
			if (myentity instanceof Target) 
			{
				if (((Target) myentity).getRow() == 1) {
					myentity.draw(this);
					((Target) myentity).setColor(this, 255,0,0);
				} else if (((Target) myentity).getRow() == 2) {
					myentity.draw(this);
					((Target)myentity).setColor(this,  255, 255, 0);
				} else if (((Target) myentity).getRow() == 3) {
					myentity.draw(this);
					((Target)myentity).setColor(this,  0, 255, 0);
				} else if (((Target) myentity).getRow() == 4) {
					myentity.draw(this);
					((Target)myentity).setColor(this,  0, 255, 255);
				} else if (((Target) myentity).getRow() == 5) {
					myentity.draw(this);
					((Target)myentity).setColor(this,  0, 0, 255);
				}
			}
			if (myentity instanceof Barrier) {
				if (frameCount % 60 >= 30){
					((Barrier) myentity).moveRight();
					myentity.draw(this);
				} else {
					((Barrier) myentity).moveLeft();
					myentity.draw(this);
				}
			} else {
				myentity.draw(this);
			}
		}
	}
	
	public void mouseClicked()
	{
		if (mouseX > base.x + base.w/2) {
			base.w -= 5;
		} else {
			base.w += 5;
		}
	}
	public void keyPressed(){ 
		if (keyCode == UP) {
			m.fired = true;}			 
		if (keyCode == RIGHT) {
			base.x += 5;
			if (!m.fired) {m.x+=5;}}
		if (keyCode == LEFT) {
			base.x -= 5;
			if (!m.fired) {m.x-=5;}}
		if (keyCode == 81)
		{
			for (Entity myentity: entities)
			{
				if (myentity instanceof Target)
				{
					((Target) myentity).dx = 0;
					((Target) myentity).dy = 0;
				}
				else if (myentity instanceof Missle)
				{
					((Missle) myentity).dy = 0;
				}
			}
		}
	}

	//code for when the group of targets hits the wall
	public void EdgeCheck() {
		boolean edgeflag = false;

		for (Entity myentity: entities) {
			if (myentity instanceof Target  )  {
				b = (Block) myentity;

				//if target hits wall, edgeflag is true
				if ( b.x + b.w > nCols) {
					edgeflag = true;
					break;
				}
				if ( b.x <0) {
					edgeflag = true;
					break;
				}
			}	
		}
		//if at the wall...
		if (edgeflag) {
			base.w -= 1;
			if (base.w <= 0)
			{
				base.w = 50;
			}
			for (Entity myentity: entities) {
				if (myentity instanceof  Target )  {
					b = (Block) myentity;

					//code for making the Targets move faster
					b.dx = -b.dx;  b.y +=5;
					if (b.dx > 0) {
						b.dx++;
					} else {
						b.dx--;
					}
				}	
			}
		}
	}
}