import processing.core.PApplet;

import static processing.core.PApplet.dist;
import static processing.core.PConstants.CENTER;

public class Catcher {
    PApplet parent;
    float r;
    float r2;
    float x, y;

    public Catcher(int tempR, PApplet p) {
        parent = p;
        r = tempR;
        x = 0;
        y = 0;
    }

    void setLocation(float tempX, float tempY) {
        x = tempX;
        y = tempY;
    }

    void display() {
        parent.noStroke();

        parent.fill(255,70,10);
        parent.rectMode(CENTER);
        parent.rect(x, y+r/2, r*2, r);

        parent.fill(200,10,10);
        parent.ellipse(x, y, r*2, r);

        parent.fill(255,70,10);
        parent.ellipse(x, y+r, r*2, r);

        parent.fill(255);
        parent.ellipse(x, y+5, r*2-8, r-8);
    }

    public boolean intersect(Drop d) {
        // 거리 계산
        float distance = dist(x, y, d.x, d.y);

        // 거리와 반지름의 합을 비교
        if (distance < r + d.r) {
            return true;
        } else {
            return false;
        }
    }
}
