package sezikim.client;

import com.google.gson.Gson;
import processing.core.PApplet;
import sezikim.GUI.*;
import sezikim.protocol.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Window extends PApplet implements GUI {
    private int clientNum = INIT_CLIENTNUM;

    private RoomInterface[] room;
    private RoomInterface roomAddComp;
    private ClientInterface clientInterface0;
    private ClientInterface clientInterface1;
    private Board board;
    private DiceImage diceImage0;
    private DiceImage diceImage1;
    private RoomInfo[] roomInfos;
    private int roomCount;
    private int color;
    private int order;
    private int winner;
    private int gameState;
    private int setTime;
    private int endTime;
    private Button readyButton;
    private SocketChannel socketChannel;
    private Gson gson;
    private Queue<Protocol> msgQueue;

    @Override
    public void settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
        room = new RoomInterface[7];
        roomAddComp = new RoomInterface(35, 50, 0, this);

        clientInterface0 = new ClientInterface(35, 50,
                "EMPTY", "WAIT", 0, this);
        clientInterface1 = new ClientInterface(235, 50,
                "EMPTY", "WAIT", 0, this);
        readyButton = new Button(35, 610, this);
        board = new Board(15, 190);
        gson = new Gson();
        msgQueue = new ConcurrentLinkedDeque<>();

        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 5300));
            ClientThread clientThread = new ClientThread(socketChannel, msgQueue);
            clientThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {
        endTime = 0;
        setTime = 0;
        color = EMPTY;
        order = BLACK;
        winner = EMPTY;
        Board.initBoard();
    }

    @Override
    public void draw() {
        background(255);

        if (!msgQueue.isEmpty()) {
            Protocol msg = msgQueue.poll();
            executeMessage(msg);
        }

        switch (gameState) {
            case GAME_LOBBY:
                for (int i = 0; i < roomCount; i++) {
                    if (room[i] != null) {
                        room[i].drawRoomComp(this);
                    }
                }
                roomAddComp.drawRoomAddComp(this);
                readyButton.clickedButton(this);
                break;

            case GAME_WAIT:
                clientInterface0.matchingProfile(this);
                clientInterface1.matchingProfile(this);
                readyButton.update(this);
                readyButton.clickedButton(this);
                setTime = millis();

            case GAME:
                if (millis() - setTime < 4000) {
                    clientInterface0.matchingProfile(this);
                    clientInterface1.matchingProfile(this);
                    readyButton.clickedButton(this);
                } else {
                    clientInterface0.inGameProfile(this, TRANS_X1, TRANS_Y1);
                    clientInterface1.inGameProfile(this, TRANS_X2, TRANS_Y2);
                    diceImage0.drawDice(this);
                    diceImage1.drawDice(this);
                    board.drawBoard(this);
                    endTime = millis();
                }
                break;

            case RESULT:
                clientInterface0.inGameProfile(this, TRANS_X1, TRANS_Y1);
                clientInterface1.inGameProfile(this, TRANS_X2, TRANS_Y2);
                diceImage0.drawDice(this);
                diceImage1.drawDice(this);
                board.drawBoard(this);
                fill(0);
                textSize(35);
                textAlign(CENTER, CENTER);

                switch (winner) {
                    case BLACK:
                        text("BLACK WIN", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
                        break;
                    case WHITE:
                        text("WHITE WIN", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
                        break;
                }
                if (millis() - endTime > 3000) {
                    gameState = GAME_WAIT;
                    setup();
                }
                break;
        }
    }

    private void executeMessage(Protocol msg) {
        int receivedClientNum;
        switch (msg.getMessageName()) {
            case "Lobby":
                Lobby lobby = gson.fromJson(msg.getJson(), Lobby.class);
                roomInfos = lobby.getRoomInfos();
                int count = 0;
                for (int i = 0; i < roomInfos.length; ++i) {
                    if (roomInfos[i] != null) {
                        count++;
                        room[i] = new RoomInterface(35, 50, i, this);
                    } else {
                        room[i] = null;
                    }
                }
                roomCount = count;
                break;

            case "User":
                User client = gson.fromJson(msg.getJson(), User.class);
                receivedClientNum = client.getClientNum();
                gameState = GUI.GAME_WAIT;

                if (clientNum == GUI.INIT_CLIENTNUM) {
                    clientNum = receivedClientNum;
                }

                String ipAddress = client.getIpAddress();

                switch (receivedClientNum) {
                    case 0:
                        clientInterface0.setClientName("User " + (receivedClientNum + 1));
                        clientInterface0.setIpAddress(ipAddress);
                        clientInterface0.setImgNum(client.getProfileNum());
                        break;

                    case 1:
                        clientInterface1.setClientName("User " + (receivedClientNum + 1));
                        clientInterface1.setIpAddress(ipAddress);
                        clientInterface1.setImgNum(client.getProfileNum());
                        break;

                }
                break;

            case "Ready":
                Ready ready = gson.fromJson(msg.getJson(), Ready.class);
                receivedClientNum = ready.getClientNum();

                switch (receivedClientNum) {
                    case 0:
                        clientInterface0.setReady(true);
                        break;
                    case 1:
                        clientInterface1.setReady(true);
                        break;
                }
                break;

            case "Unready":
                Unready unready = gson.fromJson(msg.getJson(), Unready.class);
                receivedClientNum = unready.getClientNum();

                switch (receivedClientNum) {
                    case 0:
                        clientInterface0.setReady(false);
                        break;
                    case 1:
                        clientInterface1.setReady(false);
                        break;
                }
                break;

            case "Dice":
                Dice dice = gson.fromJson(msg.getJson(), Dice.class);
                int dice0 = dice.getFirstDice();
                int dice1 = dice.getSecondDice();
                diceImage0 = new DiceImage(360, 110, dice0 - 1, this);
                diceImage1 = new DiceImage(360, 690, dice1 - 1, this);

                switch (clientNum) {
                    case 0:
                        if (dice0 > dice1) {
                            color = Board.BLACK;
                            clientInterface0.setColor(Board.BLACK);
                            clientInterface1.setColor(Board.WHITE);
                        } else {
                            color = Board.WHITE;
                            clientInterface0.setColor(Board.WHITE);
                            clientInterface1.setColor(Board.BLACK);
                        }
                        break;

                    case 1:
                        if (dice0 < dice1) {
                            color = Board.BLACK;
                            clientInterface0.setColor(Board.WHITE);
                            clientInterface1.setColor(Board.BLACK);
                        } else {
                            color = Board.WHITE;
                            clientInterface0.setColor(Board.BLACK);
                            clientInterface1.setColor(Board.WHITE);
                        }
                        break;

                }

                gameState++;
                break;

            case "Do":
                Do doMsg = gson.fromJson(msg.getJson(), Do.class);
                int x = doMsg.getXpos();
                int y = doMsg.getYpos();
                int color = Board.EMPTY;

                switch (doMsg.getColor()) {
                    case Board.BLACK:
                        color = Board.BLACK;
                        break;
                    case Board.WHITE:
                        color = Board.WHITE;
                        break;
                }

                Board.setBoard(x, y, color);
                nextOrder();
                break;

            case "Win":
                Win win = gson.fromJson(msg.getJson(), Win.class);
                winner = win.getColor();
                clientInterface0.setReady(false);
                clientInterface1.setReady(false);
                Button.clientReady = false;
                nextGameState();
                break;

            case "Out":
                Out out = gson.fromJson(msg.getJson(), Out.class);
                gameEnd();
                receivedClientNum = out.getClientNum();

                switch (receivedClientNum) {
                    case 0:
                        clientInterface1.setX(35);
                        clientInterface1.setReady(false);
                        clientInterface1.setClientName("User 1");
                        clientInterface0 = clientInterface1;
                        clientInterface1 = new ClientInterface(235, 50,
                                "EMPTY", "WAIT", 0, this);
                        Button.clientReady = false;
//                        color = Board.EMPTY;
                        break;

                    case 1:
                        clientInterface0.setReady(false);
                        clientInterface1 = new ClientInterface(235, 50,
                                "EMPTY", "WAIT", 0, this);
                        Button.clientReady = false;
//                        color = Board.EMPTY;
                        break;
                }
                clientNum = 0;
                initOrder();
                Board.initBoard();
                break;
        }
    }

    @Override
    public void mouseClicked() {
        Protocol sendMsg;

        switch (gameState) {
            case GAME_LOBBY:
                if (roomAddComp.overRoomAddComp(this)) {
                    sendMsg = new Protocol("Room"
                            , gson.toJson(new Room(roomCount)));
                    try {
                        sendJson(sendMsg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < roomInfos.length; ++i) {
                    if (roomInfos[i] != null && room[i].overRoomComp(this)) {
                        sendMsg = new Protocol("Room"
                                , gson.toJson(new Room(room[i].getRoomNumber())));
                        try {
                            sendJson(sendMsg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case GAME_WAIT:
                if (readyButton.overRect(this, BUTTON_WIDTH, BUTTON_HEIGHT)) {
                    if (!Button.clientReady) {
                        sendMsg = new Protocol("Ready"
                                , gson.toJson(new Ready(clientNum)));
                        Button.clientReady = !Button.clientReady;
                    } else {
                        sendMsg = new Protocol("Unready"
                                , gson.toJson(new Unready(clientNum)));
                        Button.clientReady = !Button.clientReady;
                    }
                    try {
                        sendJson(sendMsg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case GAME:
                if (millis() - setTime > 9000) {
                    int x = (mouseX - BOARD_MARGIN_HORIZON) / CELL;
                    int y = (mouseY - BOARD_MARGIN_VERTICAL) / CELL;

                    if (board.isCursorOnPoint(this)
                            && Board.getBoard(x, y) == EMPTY) {
                        sendMsg = new Protocol("Check"
                                , gson.toJson(new Check(x, y, color)));
                        try {
                            sendJson(sendMsg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
        }
    }

    private void nextGameState() {
        gameState++;
    }

    public int getWinner() {
        return winner;
    }

    private void gameEnd() {
        gameState = GAME_WAIT;
        winner = EMPTY;
    }

    public int getOrder() {
        return order;
    }

    public int getRoomCount() {
        return roomCount;
    }

    private void nextOrder() {
        if (order == BLACK) {
            order = WHITE;
        } else if (order == WHITE) {
            order = BLACK;
        }
    }

    private void initOrder() {
        order = BLACK;
    }

    public int getSetTime() {
        return setTime;
    }

    public int getGameState() {
        return gameState;
    }

    private void sendJson(Protocol sendMsg) throws IOException {
        String json = gson.toJson(sendMsg);
        System.out.println("send : " + json);
        byte[] data = json.getBytes();
        int len = data.length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        byteBuffer.put((byte) len);
        socketChannel.write(byteBuffer);
        byteBuffer = ByteBuffer.wrap(data);
        socketChannel.write(byteBuffer);
    }
}
