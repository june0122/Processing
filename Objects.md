# Single Object

[**[ Processing in Eclipse ]**](https://processing.org/tutorials/eclipse/)

- Only one class can extend PApplet, which is the main class, creating the sketch's frame.
Other classes must be either inner classes (like in the PDE), thus having direct access to Processing functions, or get the PApplet instance (usually in the constructor) and use it to access Processing functions as methods.

<br>

> Main class

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {
    Car myCar;

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {
        myCar = new Car(this);
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        background(255);

        myCar.move();
        myCar.display();
    }
}
```

> Oher class

```java
import processing.core.PApplet;

public class Car {

    // Variables.
    int c;
    float xpos;
    float ypos;
    float xspeed;
    PApplet parent;


    public Car(PApplet p) {
        parent = p;
        c = parent.color(175);
        xpos = parent.width / 2;
        ypos = parent.height / 2;
        xspeed = 1;
    }


    public void display() {
        parent.rectMode(parent.CENTER);
        parent.stroke(0);
        parent.fill(c);
        parent.rect(xpos, ypos, 20, 10);
    }

    public void move() {
        xpos = xpos + xspeed;
        if (xpos > parent.width) {
            xpos = 0;
        }
    }
}

```

<br>

# Two Objects

> Main class

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {
    Car myCar1;
    Car myCar2;

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {
        myCar1 = new Car(color(51), 0, 100, 2, this);
        myCar2 = new Car(color(151), 0, 200, 1, this);
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        background(255);

        myCar1.move();
        myCar1.display();
        myCar2.move();
        myCar2.display();
    }
}
```


<br>

> Other class

```java
import processing.core.PApplet;

public class Car {

    // Variables.
    int c;
    float xpos;
    float ypos;
    float xspeed;
    PApplet parent;


    public Car(int tempC, float tempXpos, float tempYpos, float tempXspeed, PApplet p) {
        parent = p;
        c = tempC;
        xpos = tempXpos;
        ypos = tempYpos;
        xspeed = tempXspeed;
    }


    public void display() {
        parent.rectMode(parent.CENTER);
        parent.stroke(0);
        parent.fill(c);
        parent.rect(xpos, ypos, 20, 10);
    }

    public void move() {
        xpos = xpos + xspeed;
        if (xpos > parent.width) {
            xpos = 0;
        }
    }
}
```

<br>

# Arrays

> Mouse History

```java
import processing.core.PApplet;

public class MouseHistory extends PApplet {

    int[] xpos = new int[50];
    int[] ypos = new int[50];

    public static void main(String[] args) {
        PApplet.main("MouseHistory");
    }

    // 배열의 모든 원소를 0으로 초기화
    public void setup() {
        for (int i = 0; i < xpos.length; i++) {
            xpos[i] = 0;
            ypos[i] = 0;
        }
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);

        // 배열의 값을 변경
        for (int i = 0; i < xpos.length - 1; i++) {
            // 모든 원소들을 하나의 지점(spot) 밑에 있도록 이동
            xpos[i] = xpos[i + 1];
            ypos[i] = ypos[i + 1];
        }

        // 배열의 마지막 지점을 마우스 위치로 업데이트
        // .lenth 는 마지막 원소의 인덱스 값보다 1 큰 수를 반환
        // '배열 길이 - 1'이 마지막 인덱스
        xpos[xpos.length - 1] = mouseX;
        ypos[ypos.length - 1] = mouseY;

        // 모든 것을 그리기
        for (int i = 0; i < xpos.length; i++) {
            // 배열의 각 원소에 대해 타원을 그린다
            // 색상과 크기는 루프의 변수인 i에 의해 결정된다.
            noStroke();
            fill(255 - i * 5);
            ellipse(xpos[i], ypos[i], i, i);
        }
    }
}
```

<br>

> Array Objects

```java
import processing.core.PApplet;

public class ArrayCar extends PApplet {
    Car[] cars = new Car[180];

    public static void main(String[] args) {
        PApplet.main("ArrayCar");
    }

    public void setup() {
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(color(i * 2), 0, i * 2, (float) (i / 20.0), this);
        }
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        background(255);

        for (int i = 0; i < cars.length; i++) {
            cars[i].move();
            cars[i].display();
        }
    }
}
```

```java
import processing.core.PApplet;

public class Car {

    // Variables.
    int c;
    float xpos;
    float ypos;
    float xspeed;
    PApplet parent;


    public Car(int tempC, float tempXpos, float tempYpos, float tempXspeed, PApplet p) {
        parent = p;
        c = tempC;
        xpos = tempXpos;
        ypos = tempYpos;
        xspeed = tempXspeed;
    }


    public void display() {
        parent.rectMode(parent.CENTER);
        parent.stroke(0);
        parent.fill(c);
        parent.rect(xpos, ypos, 20, 10);
    }

    public void move() {
        xpos = xpos + xspeed;
        if (xpos > parent.width) {
            xpos = 0;
        }
    }
}

```

<br>

> Array Interactive Objects

```java
import processing.core.PApplet;

public class ArrayInteractive extends PApplet {
    Stripe[] stripes = new Stripe[10];

    public static void main(String[] args) {
        PApplet.main("ArrayInteractive");
    }

    public void setup() {

        // 모든 Stripe의 객체들을 초기화
        for (int i = 0; i < stripes.length; i++) {
            stripes[i] = new Stripe(this);
        }
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        background(100);

        for (int i = 0; i < stripes.length; i++) {
            stripes[i].rollover(mouseX, mouseY);
            stripes[i].move();
            stripes[i].display();
        }
    }
}
```

```java
import processing.core.PApplet;

public class Stripe {

    float x;        // horizontal location of stripe
    float speed;    // speed of stripe
    float w;        // width of stripe

    boolean mouse;  // state of stripe (마우스 중첩 검사)

    PApplet parent;

    // 생성자 : Stripe 클래스 필드의 초기값을 지정
    public Stripe(PApplet p) {

        parent = p;

        // 모든 줄무늬들은 0에서 시작
        x = 0;
        // 모든 줄무늬들은 랜덤한 양의 속도를 가진다
        speed = parent.random(1);
        // 줄무늬의 너비 랜덤 설정
        w = parent.random(10, 30);
        // 마우스의 기본 불리언 값은 false
        mouse = false;
    }

    // Draw Stripe
    public void display() {

        // 불리언 값에 따라 Stripe의 색상을 결정
        if (mouse) {
            parent.fill(255);
        } else {
            parent.fill(255, 100);
        }

        parent.noStroke();
        parent.rect(x, 0, w, parent.height);
    }

    public void move() {
        x += speed;
        if (x > parent.width + 20) {
            x = -20;
        }
    }

    // 포인트(mx, my)가 줄무늬 내부에 있는지 체크
    public void rollover(int mx, int my) {
        if (mx > x && mx < x + w) {
            mouse = true;
        } else {
            mouse = false;
        }
    }
}
```

<br>

> Append To Array

```java
import processing.core.PApplet;

public class AppendToArray extends PApplet {
    Ball[] balls = new Ball[1];  // We start with an array with just one element.

    public static void main(String[] args) {
        PApplet.main("AppendToArray");
    }

    public void setup() {
        // Initialize ball index 0
        balls[0] = new Ball(50, 0, 24, this);
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(50);

        for(int i = 0; i < balls.length; i++) {
            balls[i].gravity();
            balls[i].move();
            balls[i].display();
        }
    }

    public void mousePressed() {
        // A new ball object
        Ball b = new Ball(mouseX, mouseY, 24,this);
        balls = (Ball[]) append(balls, b);
        // append(덧붙이다) : Expands an array by one element and adds data to the new position.

        // Here, the function, append() adds an element to the end of the array.
        // append() takes two arguments. The first is the array you want to append to, and the second is the thing you want to append.
        // You have to reassign the result of the append() function to the original array.
        // In addition, the append() function requires that you explicitly state the type of data in the array again by putting the
        // array data type in parentheses: (Ball[]) This is known as casting.
    }
}
```

```java
import processing.core.PApplet;

public class Ball {
    PApplet parent;
    float gravity = (float) 0.1;

    float x;
    float y;
    float speed;
    float w;

    public Ball(float tempX, float tempY, float tempW, PApplet p){
        parent = p;

        x = tempX;
        y = tempY;
        w = tempW;
        speed = 0;
    }

    public void gravity() {
        speed = speed + gravity;
    }

    public void move() {
        y = y + speed;

        if (y > parent.height) {
            speed = (float) (speed * -0.95);
            y = parent.height;
        }

    }

    public void display() {
        parent.fill(181, 100);
        parent.stroke(0);
        parent.ellipse(x, y, w, w);

    }
}
```
