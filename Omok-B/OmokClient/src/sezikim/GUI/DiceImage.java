package sezikim.GUI;

import processing.core.PApplet;
import processing.core.PImage;
import sezikim.client.Window;

import java.util.Random;

public class DiceImage {
    private int x;
    private int y;
    private int diceNum;
    private int currentFrame = 0;
    private PImage[] initDice;
    private PImage[][] diceImg;
    private Window window;

    public DiceImage(int x, int y, int diceNum, Window window) {
        this.x = x;
        this.y = y;
        this.diceNum = diceNum;
        this.window = window;
    }

    public void drawDice(PApplet p) {
        if ((p.millis() - window.getSetTime()) < 8000) {
            diceAnimation(p);
        } else if ((p.millis() - window.getSetTime()) < 9000) {
            diceIdle(p);
        }
    }

    private void diceAnimation(PApplet p) {
        sliceSprite(p);
        currentFrame++;
        if (currentFrame > 15) {
            currentFrame = 0;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(6) + 1;
        p.imageMode(p.CENTER);
        p.image(diceImg[currentFrame][randomIndex], x, y);
    }

    private void diceIdle(PApplet p) {
        sliceSprite(p);
        p.imageMode(p.CENTER);
        p.image(initDice[diceNum], x, y);
    }

    private void sliceSprite(PApplet p) {
        int spw = 46;
        int sph = 46;
        int imageCount = 0;

        diceImg = new PImage[16][9];
        PImage spriteBlock = p.loadImage("sezikim/GUI/images/dice.png");
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 9; y++) {
                diceImg[x][y] = spriteBlock.get(spw * x, sph * y, spw, sph);
            }
        }

        initDice = new PImage[]{diceImg[0][4], diceImg[4][4], diceImg[0][8], diceImg[0][0], diceImg[12][4], diceImg[8][4]};

        PImage[] rollingDice = new PImage[112];
        for (int y = 1; y < 8; y++) {
            for (int x = 0; x < 16; x++) {
                rollingDice[imageCount] = diceImg[x][y];
                imageCount++;
            }
        }
    }
}