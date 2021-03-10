package sezikim.GUI;

import processing.core.PApplet;
import sezikim.client.Window;

public class Button implements GUI {
    public static boolean clientReady = false;

    private int x;
    private int y;
    private boolean rectOver = false;
    private Window window;

    public Button(int x, int y, Window window) {
        this.x = x;
        this.y = y;
        this.window = window;
    }

    public void clickedButton(PApplet p) {
        update(p);
        p.fill(240);
        p.rect(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);

        if (rectOver) {
            p.fill(RECT_HIGHLIGHT);
        } else {
            p.fill(RECT_COLOR);
        }
        p.rect(x + PADDING, y + PADDING,
                BUTTON_WIDTH - PADDING * 2, BUTTON_HEIGHT - PADDING * 2);
        buttonText(p);
    }

    public void update(PApplet p) {
        rectOver = overRect(p, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void buttonText(PApplet p) {
        String readyButtonText = null;
        switch (window.getGameState()) {
            case GAME_LOBBY :
                readyButtonText = "MATCH";
                break;


            case GAME_WAIT :
                if (clientReady) {
                    readyButtonText = "CANCEL";
                } else readyButtonText = "READY";
                break;

            case GAME :
                if (3 - ((p.millis() - window.getSetTime())) / 1000 > 0) {
                    readyButtonText = String.valueOf(3 - ((p.millis() - window.getSetTime())) / 1000);
                } else if (3 - ((p.millis() - window.getSetTime())) / 1000 <= 0) {
                    readyButtonText = "GAME START";
                }
                break;
        }


        p.fill(100);
        p.textSize(40);
        p.textAlign(p.CENTER, p.CENTER);
        p.text(readyButtonText, this.x + BUTTON_WIDTH / 2, this.y + BUTTON_HEIGHT / 2);
    }

    public boolean overRect(PApplet p, int width, int height) {
        return p.mouseX >= x && p.mouseX <= x + width && p.mouseY >= y && p.mouseY <= y + height;
    }

}