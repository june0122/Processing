import processing.core.PApplet;

public class Ball {
    PApplet parent;

    float r;
    float x, y;
    float xspeed, yspeed;

    public Ball(float tempR, PApplet p){
        parent = p;

        r = tempR;
        x = parent.random(parent.width);
        y = parent.random(parent.height);
        xspeed = parent.random(-5, 5);
        yspeed = parent.random(-5, 5);
    }

    public void move() {
        x += xspeed;
        y += yspeed;

        if (x > parent.width || x < 0) {
            xspeed *= -1;
        }

        if(y > parent.height || y < 0) {
            yspeed *= -1;
        }
    }

    public void display() {
      parent.stroke(0);
      parent.fill(0, 50);
      parent.ellipse(x, y, r*2, r*2);
    }
}
