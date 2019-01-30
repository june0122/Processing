import processing.core.PApplet;

public class RainCatcherGame extends PApplet {
    Drop[] drops;
    Catcher catcher;
    Timer timer;

    int totalDrops = 0;


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {
        drops = new Drop[1000];
        catcher = new Catcher(32, this);
        timer = new Timer(500, this);
        timer.start();
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);

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
            if (catcher.intersect(drops[i])) {
                drops[i].caught();
            }
        }
    }
}

