import processing.core.PApplet;

public class Heart extends RainCatcherGame {
    PApplet parent;
    int lifePoint = 5;

    Heart(PApplet p) {
        parent = p;
    }

    public void draw() {
        for(int i = 0; i <= lifePoint; i++) {
            parent.textSize(25);
            // parent.fill((x +parent.frameCount) % 360,100,100);
            parent.fill(255, 50, 10);
            parent.text("♥", parent.width - i*30, 40);
        }
    }
}
