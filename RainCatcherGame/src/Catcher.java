import processing.core.PApplet;

import static processing.core.PApplet.dist;

public class Catcher {
    PApplet parent;
    float r;
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
        parent.stroke(0);
        parent.fill(175);
        parent.ellipse(x, y, r*2, r*2);
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
