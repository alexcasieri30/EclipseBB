import processing.core.PApplet;

public interface Entity {
  public void update();
  public void draw(PApplet p); //to draw each object in the interface
}
