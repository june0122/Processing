package sezikim.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int[][] board;
    private int clientCount;
    private String ipAddress0;
    private String ipAddress1;
    private int profile0;
    private int profile1;
    private boolean client0Ready;
    private boolean client1Ready;
    private int dice0;
    private int dice1;
    private int order;
    List<ServerThread> member;

    Game() {
        board = new int[Omok.BOARD_INDEX][Omok.BOARD_INDEX];
        order = Omok.BLACK;
        member = new ArrayList<>();
    }

    public void clientDisconnected() {
        clientCount--;
    }

    public int getClient() {
        return clientCount++;
    }

    public boolean getClient0Ready() {
        return client0Ready;
    }
    public void setClient0Ready(boolean ready) {
        client0Ready = ready;
    }

    public void setClient1Ready(boolean ready) {
        client1Ready = ready;
    }

    public void setIpAddress0(String ipAddress0) {
        this.ipAddress0 = ipAddress0;
    }

    public void setIpAddress1(String ipAddress1) {
        this.ipAddress1 = ipAddress1;
    }

    public String getIpAddress0() {
        return ipAddress0;
    }

    public String getIpAddress1() {
        return ipAddress1;
    }

    public boolean allReady() {
        return client0Ready && client1Ready;
    }

    public void setDice() {
        Random random = new Random();
        dice0 = random.nextInt(6) + 1;
        dice1 = random.nextInt(6) + 1;
    }

    public void setProfile0(int profile0) {
        this.profile0 = profile0;
    }

    public void setProfile1(int profile1) {
        this.profile1 = profile1;
    }

    public int getProfile0() {
        return profile0;
    }

    public int getProfile1() {
        return profile1;
    }

    public int getDice0() {
        return dice0;
    }

    public int getDice1() {
        return dice1;
    }

    public int getBoard(int x, int y) {
        return board[y][x];
    }

    public void setBoard(int x, int y, int color) {
        board[y][x] = color;
    }

    public int getOrder() {
        return order;
    }

    public void nextOrder() {
        if (order == Omok.BLACK) {
            order = Omok.WHITE;
        } else if (order == Omok.WHITE) {
            order = Omok.BLACK;
        }
    }

    public int getClientCount() {
        return clientCount;
    }

    public void initBoard() {
        board = new int[15][15];
    }
    public void initOrder() {
        this.order = Omok.BLACK;
    }
}
