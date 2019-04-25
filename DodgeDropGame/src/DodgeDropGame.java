import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

public class DodgeDropGame extends PApplet {
    private PImage bgImg;
    private PImage[][] sprites;

    // 이동 방향
    private static final int LEFT_MOTION = -1;
    private static final int IDLE_MOTION = 0;
    private static final int RIGHT_MOTION = 1;
    private int playerDirection = 0;

    private boolean buttonIsPressed = false;
    private int WINDOW_WIDTH = 256;
    private int WINDOW_HEIGHT = 223;
    private int playerPosX = 25;
    private int playerPosY = 140;
    private int currentSprite = 0;


    public static void main(String[] args) {
        PApplet.main("DodgeDropGame");
    }

    public void setup() {
        bgImg = loadImage("images/snow map.png");
        surface.setResizable(true);
        frameRate(24);
    }


    public void settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
        smooth(4);
    }

    public void draw() {
        image(bgImg, 0, 0);
        keyPressed();
        loadSprites();
        displaySprites();

    }

    public void keyPressed() {
        buttonIsPressed = true;

        if (keyCode == RIGHT) {
            playerDirection = RIGHT_MOTION;
            playerPosX += 5;
            currentSprite++;
            currentSprite %= 8;
        } else if (keyCode == LEFT) {
            playerDirection = LEFT_MOTION;
            playerPosX -= 5;
            currentSprite++;
            currentSprite %= 8;
        }
        else {
            playerDirection = IDLE_MOTION;
            currentSprite++;
            currentSprite %= 8;
        }
    }

    public void keyReleased() {

    }

    private void loadSprites() {
        int spw = 50;
        int sph = 50;
        System.gc();

        sprites = new PImage[8][7];
        PImage spriteBlock = loadImage("images/sprite.png");

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 7; y++) {
                sprites[x][y] = spriteBlock.get(spw * x, sph * y, spw, sph);
            }
        System.gc();
    }

    private void displaySprites() {
        switch (playerDirection) {
            case LEFT_MOTION:
                scale(playerDirection, 1);
                image(sprites[currentSprite][4], (playerDirection * (playerPosX + 40)), playerPosY);
                break;
            case RIGHT_MOTION:
                scale(playerDirection, 1);
                image(sprites[currentSprite][4], (playerDirection * playerPosX), playerPosY);
                break;
            case IDLE_MOTION:
                scale(1, 1);
                image(sprites[currentSprite][1], playerPosX, playerPosY);
        }

    }


}
