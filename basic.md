> Stroke fill

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        background(255);
        stroke(0);
        fill(150);
        rect(50, 50, 75, 100);
    }
}
```

<br>

> No fill

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void settings() {
        size(480, 270);

    }

    public void draw() {
        background(255);
        
        noFill();
        stroke(0);
        ellipse(60, 60, 100, 100);
    }
}
```

<br>

> alpha(opacity)

```java
import processing.core.PApplet;

import java.awt.*;

public class RainCatcherGame extends PApplet {

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(0);
        noStroke();

        fill(0,0,255);
        rect(0,0,240,200);

        fill(255,0,0,190);
        rect(0,50,480,40);

        fill(255,0,0,127);
        rect(0,100,480,40);

        fill(255,0,0,60);
        rect(0,150,480,40);
    }
}
```

<br>

> mouseX, mouseY

```java
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;

public class RainCatcherGame extends PApplet {

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);

        stroke(0);
        fill(185);
        rectMode(CENTER);

        rect(mouseX, mouseY, 50, 50, 10, 10, 20,20);


    }
}
```

<br>

> Continuous line

```java
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;

public class RainCatcherGame extends PApplet {

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {
        background(255);
    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        stroke(0);
        line(pmouseX, pmouseY, mouseX, mouseY);
    }
}
```

<br>

### Differences between `setup()` and `settings()`

The settings() function is new with Processing 3.0. It's not needed in most sketches. It's only useful when it's absolutely necessary to define the parameters to size() with a variable. Alternately, **the settings() function is necessary when using Processing code outside of the Processing Development Environment (PDE).** For example, when using the Eclipse code editor, it's necessary to use settings() to define the size() and smooth() values for a sketch.. 

The settings() method runs before the sketch has been set up, so other Processing functions cannot be used at that point. For instance, do not use loadImage() inside settings(). The settings() method runs "passively" to set a few variables, compared to the setup() command that call commands in the Processing API.

<br>

> Random painting

```java
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;

public class RainCatcherGame extends PApplet {

    float r,g,b,a;

    float diam;
    float x,y;


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {
        background(255);


    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        r = random(255);
        g = random(255);
        b = random(255);
        a = random(255);
        diam = random(20);
        x = random(width);
        y = random(height);

        noStroke();
        fill(r, g, b, a);
        ellipse(x, y, diam, diam);
    }

}


```

<br>

>

```java

```
