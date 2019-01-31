import processing.core.PApplet;

import java.util.ArrayList;

public class Arraylist extends PApplet {


    ArrayList<Particle> particles = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("Arraylist");
    }

    public void setup() {
        particles = new ArrayList();

    }

    public void settings() {
        size(480, 270);

    }

    public void draw() {

        particles.add(new Particle(this));

        background(255);

        for (Particle p : particles) {
            p.run();
            p.gravity();
            p.display();
        }

        if (particles.size() > 100) {
            particles.remove(0);
        }
    }
}
