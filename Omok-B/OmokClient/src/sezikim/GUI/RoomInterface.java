package sezikim.GUI;

import processing.core.PApplet;
import sezikim.client.Window;

public class RoomInterface {
    private static final int ROOM_WIDTH = 380;
    private static final int ROOM_HEIGHT = 60;
    private static final int ROOM_BORDER_HEIGHT = 5;
    private static final int ROOM_COMP_DISTANCE = 15;

    private static final int BORDER_COLOR = 240;
    private static final int BASIC_COLOR = 255;
    private static final int HIGHLIGHT_COLOR = 240;

    private int x;
    private int y;
    private int roomNumber;
    private int addCompY;
    private int roomCompColor = 255;
    private int roomAddCompColor = 255;
    private boolean roomCompOver = false;
    private boolean roomAddCompOver = false;
    private Window window;

    public RoomInterface(int x, int y, int roomNumber, Window window) {
        this.x = x;
        this.y = y;
        this.roomNumber = roomNumber;
        this.window = window;
    }

    public void drawRoomComp(PApplet p) {
        drawRoomFrame(p, x, y + (ROOM_HEIGHT + ROOM_COMP_DISTANCE) * roomNumber, roomNumber);
        update(p);
    }

    public void drawRoomAddComp(PApplet p) {
        addCompY = y + (ROOM_HEIGHT + ROOM_COMP_DISTANCE) * window.getRoomCount();

        if (window.getRoomCount() == 0) {
            drawRoomAddCompFrame(p, x, y);
            update(p);
        } else if (window.getRoomCount() >= 1 && window.getRoomCount() < 7) {
            drawRoomAddCompFrame(p, x, addCompY);
            update(p);
        }
    }

    private void drawRoomFrame(PApplet p, int x, int y, int roomCount) {
        setRoomCompColor();
        p.fill(roomCompColor);
        p.rect(x, y, ROOM_WIDTH, ROOM_HEIGHT);
        p.fill(BORDER_COLOR);
        p.rect(x, y, ROOM_WIDTH, ROOM_BORDER_HEIGHT);
        p.rect(x, y + ROOM_HEIGHT - ROOM_BORDER_HEIGHT, ROOM_WIDTH, ROOM_BORDER_HEIGHT);
        p.fill(160);
        p.textAlign(p.CENTER, p.CENTER);
        p.text("ROOM " + (roomCount + 1), x + (ROOM_WIDTH / 2), y + (ROOM_HEIGHT / 2) - 4);
    }

    private void drawRoomAddCompFrame(PApplet p, int roomPosX, int roomAddCompPosY) {
        setRoomCompColor();
        p.fill(roomAddCompColor);
        p.rect(roomPosX, roomAddCompPosY, ROOM_WIDTH, ROOM_HEIGHT);
        p.fill(BORDER_COLOR);
        p.rect(roomPosX, roomAddCompPosY, ROOM_WIDTH, ROOM_BORDER_HEIGHT);
        p.rect(roomPosX, roomAddCompPosY + ROOM_HEIGHT - ROOM_BORDER_HEIGHT, ROOM_WIDTH, ROOM_BORDER_HEIGHT);
        p.fill(160);
        p.textAlign(p.CENTER, p.CENTER);
        p.text("+", roomPosX + (ROOM_WIDTH / 2), roomAddCompPosY + (ROOM_HEIGHT / 2) - 5);
    }

    private void update(PApplet p) {
        roomCompOver = overRoomComp(p);
        roomAddCompOver = overRoomAddComp(p);
    }

    private void setRoomCompColor() {
        if (roomCompOver) {
            roomCompColor = HIGHLIGHT_COLOR;
        } else {
            roomCompColor = BASIC_COLOR;
        }

        if (roomAddCompOver) {
            roomAddCompColor = HIGHLIGHT_COLOR;
        } else {
            roomAddCompColor = BASIC_COLOR;
        }
    }


    public boolean overRoomComp(PApplet p) {
        int compY = y + (ROOM_HEIGHT + ROOM_COMP_DISTANCE) * roomNumber;

        return p.mouseX >= x && p.mouseX <= x + ROOM_WIDTH && p.mouseY >= compY && p.mouseY <= compY + ROOM_HEIGHT;
    }

    public boolean overRoomAddComp(PApplet p) {
        addCompY = y + (ROOM_HEIGHT + ROOM_COMP_DISTANCE) * window.getRoomCount();

        if (window.getRoomCount() == 0) {
            return p.mouseX >= x && p.mouseX <= x + ROOM_WIDTH && p.mouseY >= y && p.mouseY <= y + ROOM_HEIGHT;
        } else if (window.getRoomCount() > 0 && window.getRoomCount() <= 6) {
            return p.mouseX >= x && p.mouseX <= x + ROOM_WIDTH && p.mouseY >= addCompY && p.mouseY <= addCompY + ROOM_HEIGHT;
        } else
            return false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
