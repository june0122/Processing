import processing.core.PApplet;

public class Particle extends Arraylist {
    PApplet parent;

    float x;
    float y;
    float xspeed;
    float yspeed;
    float life;

    // 파티클 생성
    Particle(PApplet p) {
        parent = p;

        x = parent.mouseX;
        y = parent.mouseY;
        xspeed = random(-1, 1);
        yspeed = random(-2, 0);
        life = 255;
    }

    // 움직임
    public void run() {
        x = x + xspeed;
        y = y + yspeed;
    }

    public void gravity() {
        yspeed += 0.1;
    }

  /*  public void stop() {
        xspeed = 0;
        yspeed = 0;
    }

    // Ready for deletion
    public boolean finished() {
        // The Particle has a "life" variable which decreases.
        // When it reaches 0 the Particle can be removed from the ArrayList.
        life -= 2.0;
        if (life < 0) return true;
        else return false;
    }*/

    public void display() {
        parent.stroke(0);
        parent.fill(0, 75);
        parent.ellipse(x, y, 10, 10);
    }

}
