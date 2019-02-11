# Modulo

```java
import processing.core.PApplet;

public class Modulo extends PApplet {

    float[] randoms = new float[4];
    int index = 0;

    public static void main(String[] args) {
        PApplet.main("Modulo");
    }

    public void setup() {

        for (int i = 0; i < randoms.length; i++) {
            randoms[i] = random(0, 256);
        }

        frameRate(1);

    }

    public void settings() {
        size(200, 200);
    }

    public void draw() {
        background(randoms[index], randoms[index], randoms[index]);

        index = (index + 1) % randoms.length;
        // counter를 순환하면서 0으로 돌아가도록 % 연산자 사용
    }
}
```

<br>

# Random Distribution

```java
import processing.core.PApplet;

public class RandomDistribution extends PApplet {
    float[] randomCounts;
    int index = 0;

    public static void main(String[] args) {
        PApplet.main("RandomDistribution");
    }

    public void setup() {
        randomCounts = new float[width / 20];
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);

        int index = (int) random(randomCounts.length);
        randomCounts[index]++;

        stroke(0);
        fill(175);
        for (int x = 0; x < randomCounts.length; x++) {
            rect(x * 20, 0, 19, randomCounts[x]);
        }
    }
}
```

<br>

# Probability

```java
import processing.core.PApplet;

public class Probability extends PApplet {


    public static void main(String[] args) {
        PApplet.main("Probability");
    }

    public void setup() {
        background(255);
        // draw에서 결과물을 남기고 싶을 땐 background를 setup에 선언
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        noStroke();

        float red_prob = (float) 0.60;
        float green_prob = (float) 0.10;
        float blue_prob = (float) 0.30;

        float num = random(1);

        if (num < red_prob) {
            fill(255, 53, 2, 150);
        } else if (num < green_prob + red_prob) {
            fill(156, 255, 28, 150);
        } else {
            fill(10, 52, 178, 150);
        }

        ellipse(random(width), random(height), 64, 64);
    }
}
```

<br>

# Perlin Noise

```java
import processing.core.PApplet;

public class Noise extends PApplet {

    float time = (float) 0.0;
    float increment = (float) 0.01;

    public static void main(String[] args) {
        PApplet.main("Noise");
    }

    public void setup() {

    }

    public void settings() {
        size(200, 200);
    }

    public void draw() {
        background(255);

        // 노이즈 값을 time으로부터 얻고 윈도우의 넓이에 따라 크기를 변경한다.
        float n = noise(time) * width;

        // 각 사이클마다 "time"값을 증가
        time += increment;

        // 펄린 노이즈에 의해 결정되는 사이즈에 따라 타원을 그린다.
        fill(0);
        ellipse(width/2, height/2, n, n);
    }
}
```

<br>

# Map

```java
import processing.core.PApplet;

public class Map extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Map");
    }

    public void setup() {
    }

    public void settings() {
        size(300, 300);
    }

    public void draw() {
        float r = map(mouseX, 0, width, 0, 255);
        float b = map(mouseY, 0, height, 255, 0);
        background(r, 0, b);
    }
}
```

<br>

# Polar Cartesian

> 극좌표(polar coodinate)를 직교좌표(cartesian coodinate)로 변환 ([극좌표와 직교좌표 설명](https://suhak.tistory.com/entry/%EA%B7%B9%EC%A2%8C%ED%91%9Cpolar-coodinate%EC%99%80-%EC%A7%81%EA%B5%90%EC%A2%8C%ED%91%9Ccartesian-coodinate))

```java
// Polar to Cartesian



```

<br>

```java

```

<br>

```java

```
