import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.*;

public class RockMan extends PApplet {
    PImage img;
    SoundFile titleBgm;
    SoundFile titleVoice;

    float[] alphaArr = new float[4];
    int arrLength = alphaArr.length;

    public static void main(String[] args) {
        PApplet.main("RockMan");
    }

    public void setup() {
        for (int i = 1; i < arrLength; i++) {
            alphaArr[i] = 0;
        }
        titleBgm = new SoundFile(this, "sounds/01 Title.mp3");
        titleVoice = new SoundFile(this, "sounds/rockman voice 2.mp3");

        titleBgm.play(1, 0, (float) 0.3);  // https://processing.org/reference/libraries/sound/SoundFile_play_.html
//        titleVoice.play(1, 0, (float) 0.3);
    }

    public void settings() {
        size(256, 224);

    }

    public void draw() {
        background(0);
        drawTitleMenu();

    }

    private void drawTitleMenu() {
        // Title video : https://www.youtube.com/watch?v=K0meipS1zBw

        tint(255, alphaArr[1]);
        image(loadImage("images/title menu1.png"), 0, 0);
        if (alphaArr[1] < 255) {
            alphaArr[1] += 0.8;
        }

        if (alphaArr[1] >= 150) {
            tint(255, alphaArr[2]);
            image(loadImage("images/title menu2.png"), 0, 0);
                alphaArr[2]++;
        }

        if (alphaArr[2] >= 255) {
            tint(255, alphaArr[3]);
            image(loadImage("images/title menu3.png"), 0, 0);
                alphaArr[3]+= 2;
        }

    }
}
