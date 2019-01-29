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

>

```java

```

<br>

>

```java

```

<br>

>

```java

```
