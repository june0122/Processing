
import processing.core.PApplet;

import java.util.ArrayList;

public class RainCatcherGame extends PApplet {
    Drop[] drops;
    Catcher catcher;
    Timer timer;
    Heart heart;
    ArrayList particles;


    int totalDrops = 0;
    int catchCounter = 0;
    int missCounter = 0;

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {
        drops = new Drop[1000];
        catcher = new Catcher(28, this);
        timer = new Timer(500, this);
        // particles = new ArrayList();



        timer.start();

        heart = new Heart(this);
    }

    public void settings() {
        size(480, 800);
    }

    public void draw() {
        background(0);

        noCursor();

        ArrayList<Particle> particles = new ArrayList<Particle>();
        particles.add(new Particle(this));


        for(Particle par : particles) {
            par.run();
            par.gravity();
            par.display();
        }

        if (particles.size() > 100) {
            particles.remove(0);
        }

        // Set catcher location
        catcher.setLocation(mouseX, mouseY);
        // Display the catcher
        catcher.display();

        // 타이머 체크 및 빗방울의 배열 관리
        if (timer.isFinished()) {
            // Initialize one drop
            drops[totalDrops] = new Drop(this);
            // Increment totalDrops
            totalDrops++;
            // If we hit the end of the array
            if (totalDrops >= drops.length) {
                totalDrops = 0; //Start over
            }
            timer.start();
        }

        // Move and display drops
        for (int i = 0; i < totalDrops; i++) { // New! We no longer move and display all drops, but rather only the “totalDrops” that are currently present in the game.
            drops[i].move();
            drops[i].display();

            // 빗방울이 바닥에 닿으면 missCounter 증가, lifePoint 감소
            if (drops[i].reachedBottom()) {
                missCounter++;
                heart.lifePoint--;
            }

            // cathcer와 빗방울이 겹친다면 catchCounter 증가 및 caugth 메소드 호출
            if (catcher.intersect(drops[i])) {
                catchCounter++;
                drops[i].caught();
            }
        }

        // HUD setting
        heart.draw();
        fill(255);
        textSize(20);
        text("Score : " + catchCounter, 20, 35);
        text("Miss : " + missCounter, 20, 65);
    }
}

