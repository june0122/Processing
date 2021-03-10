package sezikim.GUI;

import processing.core.PApplet;
import processing.core.PImage;
import sezikim.client.Window;

public class ClientInterface implements GUI {

    private int imgNum;
    private int x;
    private int y;
    private int color;
    private String clientName;
    private String ipAddress;
    private boolean ready;
    private Window window;

    public ClientInterface(int x, int y, String clientName, String ipAddress, int profileNum, Window window) {
        this.x = x;
        this.y = y;
        this.clientName = clientName;
        this.ipAddress = ipAddress;
        imgNum = profileNum;
        this.window = window;
    }

    public void matchingProfile(PApplet p) {
        drawProfileBox(p);
        drawProfileImg(p);
    }

    public void inGameProfile(PApplet p, int transX, int transY) {
        PImage profileImg = resizeProfile(p);
        int inGameX = x + transX;
        int inGameY = y + transY;

        drawProfileBox(p, inGameX, inGameY);
        drawProfileInfo(p, profileImg, inGameX, inGameY);

        if (window.getGameState() == window.GAME) {
            drawOrderPointer(p, inGameX, inGameY);
        }
        if (window.getWinner() != EMPTY) {
            drawWinPointer(p, inGameX, inGameY);
        }
    }

    private void drawProfileBox(PApplet p) {
        p.noFill();
        p.rect(x, y, MATCHING_WIDTH, MATCHING_HEIGHT);
        p.fill(120);
        p.rect(x, y, MATCHING_WIDTH, BORDER_HEIGHT);
        p.rect(x, y + MATCHING_HEIGHT, MATCHING_WIDTH, BORDER_HEIGHT);

        p.fill(120);
        p.textSize(20);
        p.textAlign(p.CENTER, p.CENTER);
        p.text(clientName, x + MATCHING_WIDTH / 2, y + MATCHING_HEIGHT * 0.85f);
        p.text(ipAddress, x + MATCHING_WIDTH / 2, y + MATCHING_HEIGHT * 0.92f);

        if (ready) {
            p.fill(100);
            p.textSize(30);
            p.textAlign(p.CENTER, p.CENTER);
            p.text("READY", x + MATCHING_WIDTH / 2, y + MATCHING_HEIGHT * 0.085f);
        }
    }

    private void drawProfileImg(PApplet p) {
        PImage profileImg;
        String imgName = "sezikim/GUI/images/profile" + imgNum + ".png";

        profileImg = p.loadImage(imgName);
        profileImg.resize((int) (profileImg.width / 1.7f), (int) (profileImg.height / 1.7f));
        p.imageMode(p.CENTER);
        p.image(profileImg, x + MATCHING_WIDTH / 2, y + MATCHING_HEIGHT * 0.47f);
    }


    private void drawOrderPointer(PApplet p, int inGameX, int inGameY) {
        if ((p.millis() - window.getSetTime()) > 9000) {
            PImage turnImg;
            String turnImgName = "sezikim/GUI/images/turn" + color + ".png";
            turnImg = p.loadImage(turnImgName);
            turnImg.resize(46, 46);
            p.imageMode(p.CENTER);
            if (color == window.getOrder()) {
                p.image(turnImg, inGameX + 345, inGameY + 60);
            }
        }
    }

    private void drawProfileInfo(PApplet p, PImage profileImg, int inGameX, int inGameY) {
        p.imageMode(p.CENTER);
        p.copy(profileImg, 0, 0, 200, 100, inGameX + 20, inGameY + 23, (int) (200 * 0.7f), (int) (100 * 0.7f));
        p.textSize(20);
        p.fill(120);
        p.textAlign(p.CENTER, p.CENTER);
        p.text(clientName, inGameX + IN_GAME_WIDTH / 2, inGameY + IN_GAME_HEIGHT * 0.3f);
        p.text(ipAddress, inGameX + IN_GAME_WIDTH / 2, inGameY + IN_GAME_HEIGHT * 0.65f);
    }

    private void drawProfileBox(PApplet p, int inGameX, int inGameY) {
        if (color == Board.BLACK && (p.millis() - window.getSetTime()) > 8000) {
            p.fill(100);
        } else {
            p.fill(240);
        }
        p.rect(inGameX, inGameY, IN_GAME_WIDTH, IN_GAME_HEIGHT);
        p.fill(255);
        p.rect(inGameX + PADDING, inGameY + PADDING, IN_GAME_WIDTH - PADDING * 2, IN_GAME_HEIGHT - PADDING * 2);
    }

    private void drawWinPointer(PApplet p, int inGameX, int inGameY) {
            PImage turnImg;
            String turnImgName = "sezikim/GUI/images/win.png";
            turnImg = p.loadImage(turnImgName);
            turnImg.resize(46, 46);
            p.imageMode(p.CENTER);
            if (color == window.getWinner()) {
                p.image(turnImg, inGameX + 345, inGameY + 60);
            }
    }


    private PImage resizeProfile(PApplet p) {
        PImage profileImg;
        String imgName = "sezikim/GUI/images/profile" + imgNum + ".png";
        profileImg = p.loadImage(imgName);
        profileImg.resize((int) (profileImg.width / 1.7f), (int) (profileImg.height / 1.7f));
        return profileImg;
    }

    private void drawResultScreen(PApplet p) {
        drawResultFrame(p);

    }

    private void drawResultFrame(PApplet p) {
        p.noFill();
        p.rect(x, y, MATCHING_WIDTH, MATCHING_HEIGHT);
        p.fill(120);
        p.rect(x, y, MATCHING_WIDTH, BORDER_HEIGHT);
        p.rect(x, y + MATCHING_HEIGHT, MATCHING_WIDTH, BORDER_HEIGHT);

        p.fill(120);
        p.textSize(20);
        p.textAlign(p.CENTER, p.CENTER);
        p.text(clientName, x + MATCHING_WIDTH / 2, y + MATCHING_HEIGHT * 0.85f);
        p.text(ipAddress, x + MATCHING_WIDTH / 2, y + MATCHING_HEIGHT * 0.92f);
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }
}
