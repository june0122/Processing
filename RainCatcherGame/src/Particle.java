import processing.core.PApplet;

public class Particle extends RainCatcherGame {
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
        xspeed = random(-4, 4);
        yspeed = random(-4, 0);
        life = 255;
    }

    // 움직임
    public void run() {
        x = x + xspeed;
        y = y + yspeed;
    }

    public void gravity() {
        yspeed += 0.2;
    }

    public void stop() {
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
    }

    public boolean reachedBottom() {
        // 바닥보다 더 아래로 갔을때 체크
        if (y > parent.height) {// 바닥에 닿았을 때 빗방울의 y 값을 위로 옮겨주지 않으면 계속 바닥에 닿아있는 판정이 됨.
            return true;
        } else {
            return false;
        }
    }


    public void display() {
        life -= 3.0;
        parent.stroke(255, life);
        parent.fill(255,  life);
        parent.ellipse(x, y, 3, 3);
    }

}
