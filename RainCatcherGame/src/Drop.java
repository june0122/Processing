import processing.core.PApplet;

public class Drop extends RainCatcherGame{
    PApplet parent;

    float x, y;
    float speed;
    int c;
    float r;        // 빗방울의 반지름

    public Drop(PApplet p) {
        parent = p;

        r = 8;                                   // 모든 빗방울은 같은 사이즈
        x = parent.random(parent.width);         // 랜덤한 x 위치에서 시작
        y = -r * 4;                              // 윈도우보다 조금 위에서 시작
        speed = parent.random(1, 5);  // 랜덤 스피드
        c = parent.color(50, 100, 150);
    }

    public void move() {
        y += speed;
    }

    // 바닥에 닿았는가 확인
    public boolean reachedBottom() {
        // 바닥보다 더 아래로 갔을때 체크
        if (y > parent.height + r * 4) {
            y = -1000;      // 바닥에 닿았을 때 빗방울의 y 값을 위로 옮겨주지 않으면 계속 바닥에 닿아있는 판정이 됨.
            return true;
        } else {
            return false;
        }
    }

    public void display() {
        parent.fill(255);
        parent.noStroke();
        // for문을 이용하여 빗방울 모양 디자인
        for (int i = 2; i < r; i++) {
            parent.ellipse(x, y + i * 4, i * 2, i * 2);
        }
    }

    public void caught() {
        speed = 0;
        // 화면 밖의 위치로 대략적으로 설정
        y = -1000;
    }

}
