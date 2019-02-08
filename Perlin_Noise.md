# 펄린 노이즈(Perlin Noise)

좋은 난수 생성기는 서로 관계가 없고 인지 가능한 패턴이 없는 숫자를 만들어 낸다. 난수성은 유기적이거나 생물적인 특성을 프로그래밍할 때 유용하다.
하지만, 하나의 가이드 원칙으로써의 난수성은 꼭 자연스러운 것은 아니다.
개발자인 Ken Perlin을 따라 명명된 "Perlin 노이즈"라는 알고리즘은 이 개념을 포함하고 있다.
Perlin은 1980년대 초 Tron 영화를 제작하면서 이 노이즈 함수를 개발했다.
컴퓨터 효과를 위한 단계적인 텍스쳐를 만들기 위해 설계되었는데 그는 1997년 이 업적으로 기술 부문 아카데미상을 수상했다.
Perlin 노이즈를 이용해서 구름, 지형, 대리석과 같은 다양한 자연 효과를 만들어낼 수 있다.

<br>
<p align="center">
<img width="460" src="https://chattersley.files.wordpress.com/2010/05/tron-1-1024.jpg">
</p>

<p align="center">
<em>트론 (Tron, 1982)</em>
</p>
</br>

|Noise Dimension|Raw Noise(Grayscale)|Use Case|
|:---:|:---:|:---:|
|1|![noise1-1](https://user-images.githubusercontent.com/39554623/52392023-7b888c80-2ae3-11e9-8fc7-eba6d88e9fb6.png)|![noise1-2](https://user-images.githubusercontent.com/39554623/52392024-7b888c80-2ae3-11e9-8ef1-af1543dfcc51.png)<br>손으로 직접 그린 느낌을 주는 선을 만들기위한 오프셋으로 노이즈를 사용한다.|
|2|![noise2-1](https://user-images.githubusercontent.com/39554623/52392025-7b888c80-2ae3-11e9-835b-14c7c7db2cb5.png)|![noise2-2](https://user-images.githubusercontent.com/39554623/52392026-7b888c80-2ae3-11e9-8c92-d6ebf12a97e4.png)<br>단순한 그레디언트를 적용함으로써 *Procedural fire texture*가 생성 가능하다.|
|3|![noise3-1](https://user-images.githubusercontent.com/39554623/52392027-7c212300-2ae3-11e9-9193-39a9beb6dfc0.png)|![noise3-2](https://user-images.githubusercontent.com/39554623/52392028-7c212300-2ae3-11e9-9186-e8ca6c4a52ce.png)<br>지형은 수정된(향상된) 펄린 노이즈의 구현으로 동굴과 함께 생성될<br> 수 있는데, 아마 오늘날 펄린 노이즈의 본질적인 사용법일 것이다.|                             

> ***Procedural Texture***

> 컴퓨터 그래픽에서 `Procedural`이라는 용어가 나오면 일단 `자동적인`내지는 `컴퓨터에 의해 만들어지는`이라고 해석하면 된다.
그렇다면 Procedural Texture의 뜻은 "컴퓨터에 의해서 자동적으로 만들어지는 Texture"정도가 된다.
Procedural Texture의 핵심은 이와 같이 사용자에 의해 주어지는 변수 값들을 바탕으로 특정 함수(주로 Fractal이나 Noise 함수)를 이용해서
Texture를 컴퓨터가 스스로 만들어 낸다는데 있다. 주위에서 가장 흔하게 볼 수 있는 Procedural Texture의 예로는 포토샵의 Gradient 기능을 생각할 수 있다.



<br>

펄린 노이즈는 자연스럽게(부드럽게) 정렬된 의사 난수 시퀀스를 생성하기 때문에 보다 더 유기적인 모습을 하고 있다. 아래의 그래프는 시간이 흐름에 따른 펄린 노이즈를 보여준다.
x축은 시간을 나타내는 것을 유념하고 곡선의 매끄러움에 주시하자.

<br>

<p align="center">
<img src="https://natureofcode.com/book/imgs/intro/intro_05.png">
<img src="https://natureofcode.com/book/imgs/intro/intro_06.png">
</p>

<p align="center">
<em>노이즈 그래프와 난수 그래프</em>
</p>

<br>

## 노이즈 함수 사용법

Processing을 이용하여 임의의 x 위치에 원을 그린다고 생각하면 아래와 같이 코드를 작성할 것이다.

```java
int x = random(0, width);
ellipse(x, 180, 16, 16);
```

그럼 임의의 x 위치가 아니라 펄린 노이즈를 이용하여 보다 '매끄러운' x 위치를 지정하려면 단순히 `random()`을 `noise()`로 대체하면 될까?

```java
int x = noise(0, width);
ellipse(x, 180, 16, 16);
```

땡! 펄린 노이즈에 따라 0부터 width(너비) 사이의 값으로 x값을 정하는 건 개념적으론 정확하지만 올바른 구현 방법은 아니다.
`random()` 함수의 인수가 최솟값 최댓값을 명시하기는 하지만 noise() 함수는 이런 방식으로 작동하지 않는다.

대신 `noise()`는 **어떤 순간을 나타내는 인수를 기대**하고, **항상 0과 1 사이의 값을 반환**한다.

Processing 내의 노이즈 값에 접근하기 위해선 `noise()` 함수에 **특정 시간값을 전달**해야한다.

```java
float t = 0;

 public void draw() {
        background(255);

        float n = noise(t);
        println(n);

        // 특정 시간값 't'
        t += 0.01;
    }
}
```

<img src="https://user-images.githubusercontent.com/39554623/52389586-7ae9f900-2ad7-11e9-941f-42e4a2b1b33f.gif">

<img src="https://cdn-images-1.medium.com/max/1200/1*tnrhcVJ60kv7yvx-1iO80g.png">

얼마나 빠르게 t 값을 증가시키는지가 노이즈의 부드러움에 영향을 준다.
만약 시간 내에 크게 도약하면 앞으로 건너뛰게 되어 값이 훨씬 더 임의적이게 된다.

<br>

## 노이즈 매핑(Noise Mapping)

노이즈 값을 이용하여 어떤 일을 할 수 있을까? 일단 0과 1 사이의 값을 얻고 나면, 원하는 범위에 그 값을 매핑할 수 있다.
단순히 범위의 최댓값을 곱해줄 수도 있지만 Processing의 유용한 함수인 `map()`을 이용해보자.

<br>

![noisemapping0](https://user-images.githubusercontent.com/39554623/52389736-55a9ba80-2ad8-11e9-9f48-a80bd2a21438.png)

<br>

`map()` 함수는 5개의 인자를 갖는다. 첫 번째부터 순서대로 매핑하려는 값, 현재 범위의 최솟값과 최댓값, 원화는 범위의 최솟값과 최댓값이다.  그렇다면 `map()`을 이용하여 0과 1 사이의 범위가 아닌 다른 범위를 가진 코드를 작성해보자.

<br>
<p align="center">
<img src="https://user-images.githubusercontent.com/39554623/52390335-28124080-2adb-11e9-96ca-8f1a8b4b4ec6.gif">
</p>
</br>

```java
//  0과 현재의 너비 사이의 너비를 갖는 직사각형

import processing.core.PApplet;

public class NoiseBasic extends PApplet {

    float t = 0;

    public static void main(String[] args) {
        PApplet.main("NoiseBasic");
    }

    public void setup() {
        // 화면에 표시된 것을 초기화하고 싶지 않으면 setup()에 background를 지정해줘야 한다.
        background(255);
    }

    public void settings() {
        size(300, 300);
    }

    public void draw() {
        float n = noise(t);

        // map()을 이용하여 펄린 노이즈의 범위를 커스터마이즈하자.
        float y = map(n, 0, 1, 0, height);
        rect(t * 100, height, 1, -y);

        t += 0.01;

    }
}
```

<br>

동일한 논리를 임의의 walker에 적용하고 펄린 노이즈에 따라 x값과 y값을 할당해보면 아래와 같다.

<p align="center">
<img src="https://user-images.githubusercontent.com/39554623/52460693-f7034000-2bae-11e9-82cd-8b6653643f42.gif">
</p>

> **Walker**

```java
import processing.core.PApplet;

public class Prototype extends PApplet {
    Walker w;

    float x, y, tx, ty;

    public static void main(String[] args) {
        PApplet.main("Prototype");
    }

    public void setup() {
        w = new Walker(this);
        background(255);
    }


    public void settings() {
        size(300, 300);
    }

    public void draw() {
        w.walk();
        w.display();
    }
}
```

```java
import processing.core.PApplet;

public class Walker extends Prototype {

    PApplet parent;

    public Walker(PApplet p) {
        parent = p;

        this.x = width / 2;
        this.y = height / 2;
        this.tx = 0;
        this.ty = 10000;
    }

    public void display() {
        parent.stroke(0, 0, 0);
        parent.point(this.x, this.y);
    }

    // 랜덤하게 위, 아래, 왼쪽, 오른쪽으로 움직이거나 제자리에 가만히 있는다.
    public void walk() {
        this.x = map(noise(this.tx), 0, 1, 0, parent.width);
        this.y = map(noise(this.ty), 0, 1, 0, parent.height);

        this.tx += 0.01;
        this.ty += 0.01;
    }
}
```

<br>

위의 예제에서 추가적인 한 쌍의 변수 tx와 ty를 사용하는 것은 Walker 객체의 x, y 좌표를 위해 두 개의 시간 변수를 사용하기 때문이다.
그러나 tx는 0에서부터 시작하고 ty는 10000에서 시작하는 것이 주목해야할 부분이다. 이 숫자들은 임의로 선택한 것이지만, 두 개의 시간 변수를
아주 구체적인 숫자로 초기화한 것은 **노이즈 함수가 결정론적**이기 때문이다.

즉, 노이즈 함수는 특정 시간 t에 대해서 항상 같은 결과를 얻을 수 있다는 말이다. 만약 x와 y의 노이즈 값으로 같은 시간 t를 사용한다면, x와 y의 값이 항상 같아서 Walker 객체는 항상 대각선 상에서만 이동을 한다.
그 대신, x는 0에서 시작하고 y는 10000에서 시작하는 것처럼 노이즈에 간격을 주면, x와 y를 서로 독립적으로 사용할 수 있다.

<br>

![](https://user-images.githubusercontent.com/39554623/52389698-2004d180-2ad8-11e9-9e8e-c7ae74272b50.png)

<br>

위에서 사용된 시간의 개념은 실제로 존재하지 않는다. 노이즈 함수가 어떻게 작동하는지를 이해하기 위한 비유일 뿐, 실제로는 공간으로 존재한다.
위의 그래프는 1차원 공간에서 선형 구조로 노이즈 값을 표현하기 때문에, 언제든 특정 x값을 얻을 수 있다.
실제 예제에서, 종종 시간을 뜻하는 t 변수 대신 xoff라는 이름의 변수를 볼 수 있는데 이는 x-offset을 뜻한다.

## Perlin Noise Ellipse

<br>

<p align="center">
<img src="https://user-images.githubusercontent.com/39554623/52387449-93edac80-2acd-11e9-8570-6efaa751d21b.gif">
</p>

<br>

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

## Perlin Noise Rectangle

<br>

<p align="center">
  <img width="460" height="300" src="https://user-images.githubusercontent.com/39554623/52386969-b1217b80-2acb-11e9-9f03-78c7b7b2274c.gif">
</p>

<br>

```java
import processing.core.PApplet;

public class NoiseRect extends PApplet {

    float noiseScale = (float) 0.02;

    public static void main(String[] args) {
        PApplet.main("NoiseRect");
    }

    public void setup() {

    }

    public void settings() {
        size(300, 600);
    }

    public void draw() {
        background(0);

        for(int x = 0; x < width; x++) {
            float noiseVal = noise((mouseX+x)*noiseScale, mouseY*noiseScale);

            stroke(noiseVal*255);
            line(x, mouseY+noiseVal*80, x, height);
        }
    }
}
```

## Perlin Noise Lava
 
> [Processing Mathematics](https://github.com/june0122/Processing/blob/master/Mathematics.md) 의 Probability(확률) 코드 활용 

<img width="280" src="https://user-images.githubusercontent.com/39554623/52464627-fc688680-2bbe-11e9-8a3d-faef36009c2a.png"> <img width="280" src="https://user-images.githubusercontent.com/39554623/52464630-fd011d00-2bbe-11e9-87db-a8c72522331d.png"> <img width="280" src="https://user-images.githubusercontent.com/39554623/52464631-fd011d00-2bbe-11e9-906d-1c660d46b3c4.png">

<p align="center">
<em>alpha값이 작은 레이어를 하나씩 쌓아가면서 사실적인 용암을 연출한다.</em>
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/39554623/52463546-86622080-2bba-11e9-9c11-2a334597b472.gif">
</p>

```java
import processing.core.PApplet;

public class Lava extends PApplet {
    float noiseScale = (float) 0.005;
    float temp = 0;
    float levelDetailsMap = 450;    // 값이 커질수록 쌓여나가는 점들의 모양이 물결모양이 됨

    public static void main(String[] args) {
        PApplet.main("Lava");
    }

    public void setup() {
        background(255, 255, 255);
    }

    public void settings() {
        size(400, 300);
    }

    public void drawMap() {
        for (int x = 0; x < width; x++) {
            float noiseVal = noise(x * noiseScale, noiseScale * frameCount);

            // noiseVal의 값을 확률적으로 설정하여 용암을 표현하는데 필요한 색상을 랜덤으로 불러온다.
            if (noiseVal < 0.37) {
                // 진회색
                stroke(64, 64, 64);
            } else if (noiseVal < 0.41) {
                // 검정색
                stroke(5, 5, 5);
            } else if (noiseVal < 0.49) {
                // 붉은계열 색상 랜덤
                stroke(random(255), 1, 1);
            } else if (noiseVal < 0.50) {
                // 빨간색
                stroke(250, 8, 8);
            } else if (noiseVal < 0.53) {
                // 주황색
                stroke(250, 110, 10);
            } else if (noiseVal < 0.54) {
                // 다홍색
                stroke(230, 57, 14);
            } else if (noiseVal < 0.56) {
                // 흰색
                stroke(250, 250, 250);
            } else {
                // 빨간색
                stroke(255, 0, 0);
            }

            point(x, (float) (noiseVal * levelDetailsMap + temp - levelDetailsMap / 1.7 - 10));

            // y축 방향으로 모양을 그려나감
            stroke(random(255) * 200, noiseVal * 200, noiseVal * 200 - 100, 25);    // 주황색 계열색
            point(x, (float) (noiseVal * levelDetailsMap + temp - levelDetailsMap / 1.7 - 12));

            // x축 방향으로 모양을 그려나감
            stroke(random(100) * 200, noiseVal * 255, noiseVal * random(0, 100) - 20, 5);
            // ↑ 주황색과 노란색의 혼합. R, G값이 높으면 노란색 계열이고, R값이 높은 상태에서 G값이 낮아지면 주황색 계열이 된다.
            point((float) (noiseVal * levelDetailsMap + temp - levelDetailsMap / 1.7 - 12), x);
        }
    }

    public void draw() {
        strokeWeight(4);    // 3~8 사이의 값이 적절한 용암의 느낌을 낸다.
        drawMap();
        temp += 0.5;
    }

    public void mouseClicked() {
        temp = 0;
        noiseScale += 0.001;
        background(255, 255, 255);
        println("noiseScale: " + noiseScale);
    }
}
```
















* * *

## Reference

[Khan Academy : Perlin Noise](https://ko.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-noise/a/perlin-noise)

[Understanding Perlin Noise](http://flafla2.github.io/2014/08/09/perlinnoise.html)
