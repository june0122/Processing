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

> Conditionals fading colors

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    float r = 150;
    float g = 0;
    float b = 0;

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {

    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(r, g, b);
        stroke(255);
        line(width / 2, 0, width / 2, height);

        if (mouseX > width / 2) {
            r = r + 1;
        } else {
            r = r - 1;
        }

        if (r > 255) {

        } else if (r < 0) {
            r = 0;
        }

    }
}
```

<br>

> Rollovers

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {

    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);
        stroke(0);
        line(width / 2, 0, width / 2, height);
        line(0, height /2, width, height/2 );

        noStroke();
        fill(0);
        
        if (mouseX < width /2 && mouseY < height/2) {
            rect(0, 0, width/2, height/2);
        } else if(mouseX < width /2 && mouseY > height/2) {
            rect(0, height/2, width/2, height);
        } else if(mouseX > width /2 && mouseY < height/2) {
            rect(width/2, 0, width, height/2);
        } else if(mouseX > width /2 && mouseY > height/2) {
            rect(width/2, height/2, width, height);
        }
    }
}
```

<br>

> Hold Button

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {
    boolean button = false;

    int x = 50;
    int y = 50;
    int w = 100;
    int h = 75;

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {

    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(0);

        if(mouseX > x && mouseY > y && mouseX < x+w && mouseY < y+h && mousePressed) {
            button = true;
        } else {
            button = false;
        }


        if(button) {
            background(255);
            stroke(0);
        } else {
            background(0);
            stroke(255);
        }



        fill(175);
        rect(x, y, w, h);

    }
}
```

<br>

> Switch Button

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {
    boolean button = false;

    int x = 50;
    int y = 50;
    int w = 100;
    int h = 75;

    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {

    }

    public void settings() {
        size(480, 270);
    }

    public void mousePressed() {
        if (mouseX > x && mouseY > y && mouseX < x + w && mouseY < y + h) {
            button = !button;
        }
    }

    public void draw() {
        background(0);

        if (button) {
            background(255);
            stroke(0);
        } else {
            background(0);
            stroke(255);
        }

        fill(175);
        rect(x, y, w, h);

    }
}
```

<br>

> Bouncing Ball

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    int x = 0;
    int y = 100;

    int speed = 2;


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {


    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        background(255);
        x = x + speed;

        if((x > width) || (x < 0) ) {
            speed = speed * -1;
        }

        stroke(0);
        fill(175);
        ellipse(x, y, 30, 30);

    }
}
```

<br>

> Bouncing Color (My code)

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    float c1 = 0;
    float c2 = 255;

    int changeVar = -1;


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {


    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {

        c1 = c1 - changeVar;
        c2 = c2 + changeVar;

        if (c1 > 255 || c1 < 0 ) {
            changeVar = changeVar * -1;
        }
        
        noStroke();

        fill(c1, 0, c2);
        rect(0,0, 240, 270);

        fill(c2, 0, c1);
        rect(240, 0 , 240, 270);
    }
}
```

> Bouncing Color (Reference code)

```java
// Two variables for color.
float c1 = 0;      
float c2 = 255;    

// Start by incrementing c1.
float c1Change = 1;  
// Start by decrementing c2.
float c2Change = -1; 

void setup() {
  size(480, 270);
}

void draw() {
  noStroke();

  // Draw rectangle on left
  fill(c1, 0, c2);
  rect(0, 0, 240, 270);

  // Draw rectangle of right
  fill(c2, 0, c1);
  rect(240, 0, 240, 270);

  // Adjust color values
  c1 = c1 + c1Change;
  c2 = c2 + c2Change;

  // Instead of reaching the edge of a window, these variables reach the "edge" of color:  
  // 0 for no color and 255 for full color.  
  // When this happens, just like with the bouncing ball, the change is reversed.

  // Reverse direction of color change 
  if (c1 < 0 || c1 > 255) {
    c1Change *= -1;
  }

  if (c2 < 0 || c2 > 255) {
    c2Change *= -1;
  }
}
```

<br>

> Path Along Edges

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    int x = 0;
    int y = 0;

    int speed = 5;

    int state = 0;


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {


    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);

        noStroke();
        fill(175);
        rect(x, y, 30, 30);

        switch (state) {
            case 0:
                x = x + speed;

                if (x + 30 > width) {
                    x = width - 30;
                    state = 1;
                }

                break;

            case 1:
                y = y + speed;

                if (y + 30 > height) {
                    y = height - 30;

                    state = 2;
                }

                break;

            case 2:
                x = x - speed;

                if (x < 0) {
                    x = 0;
                    state = 3;
                }

                break;

            case 3:
                y = y - speed;

                if (y < 0) {
                    y = 0;
                    state = 0;
                }
        }
    }
}
```

<br>

> Gravity

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {

    float x = 240;
    float y = 0;

    float speed = 0;

    float gravity = (float) 0.1;


    public static void main(String[] args) {
        PApplet.main("RainCatcherGame");
    }

    public void setup() {


    }

    public void settings() {
        size(480, 270);
    }

    public void draw() {
        background(255);

        fill(175);
        stroke(0);
        rectMode(CENTER);
        rect(x,y,30, 30);

        y = y + speed;

        speed = speed + gravity;

        if(y > height) {
            speed = (float) (speed * -0.7);
            y = height-20;
        }
    }
}
```

<br>

> While Loop

```java
import processing.core.PApplet;

public class RainCatcherGame extends PApplet {
    int x = 50;
    int y = 80;
    int len = 20;
    int spacing = 10;

    int endLegs = 150;

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

        while( x <= endLegs) {
            line(x, y, x, y + len);
            x = x + spacing;
        }

    }
}
```

<br>

>

```java

```
