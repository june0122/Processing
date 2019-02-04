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

```java

```

<br>

```java

```

<br>

```java

```

<br>

```java

```

<br>

```java

```
