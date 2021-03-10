package sezikim.server;

import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import sezikim.protocol.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

public class ServerThread extends Thread {
    private static List<ServerThread> clientList;
    private static Game[] games;
    private static ProtocolOuterClass.RoomInfo[] roomInfos;
    private List<ServerThread> gameMembers;
    private int clientNum;
    private SocketChannel socketChannel;
    private int roomIndex;
    private Gson gson;

    ServerThread(SocketChannel socketChannel, List<ServerThread> clientList, Game[] games, ProtocolOuterClass.RoomInfo[] roomInfos) {
        ServerThread.clientList = clientList;
        ServerThread.clientList.add(this);
        ServerThread.games = games;
        ServerThread.roomInfos = roomInfos;
        this.socketChannel = socketChannel;
        gson = new Gson();
    }

    private void broadcast(ByteBuffer byteBuffer) {
        for (ServerThread serverThread : clientList) {
            try {
                serverThread.socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteBuffer.flip();
        }
    }

    private void broadcastInGame(ByteBuffer byteBuffer) {
        for (ServerThread serverThread : gameMembers) {
            try {
                serverThread.socketChannel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteBuffer.flip();
        }
    }

    public void run() {
        ProtocolOuterClass.Protocol sendMsg;
        ProtocolOuterClass.Protocol receiveMsg;
        int len;

        try {
            sendMsg = new ProtocolOuterClass.Protocol("Lobby", gson.toJson(new ProtocolOuterClass.Lobby(roomInfos)));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        sendJson(sendMsg);

        try {
            ByteBuffer byteBuffer;
            byte[] data = new byte[1024];
            while (true) {
                byteBuffer = ByteBuffer.allocate(1024);
                len = socketChannel.read(byteBuffer);
                if (len == -1) {
                    games[roomIndex].clientDisconnected();
                    break;
                }
                byteBuffer.flip();

                receiveMsg = receiveJson(data, byteBuffer, len);

                switch (receiveMsg.getMessageName()) {
                    case "Room":
                        ProtocolOuterClass.Room room = gson.fromJson(receiveMsg.getJson(), ProtocolOuterClass.Room.class);
                        roomIndex = room.getRoomNum();
                        String ipAddress = socketChannel.getRemoteAddress().toString().substring(9);
                        int profileNum;
                        if (games[roomIndex] == null) {
                            games[roomIndex] = new Game();
                            gameMembers = games[roomIndex].member;
                            gameMembers.add(this);
                            clientNum = games[roomIndex].getClient();
                            roomInfos[roomIndex] = new ProtocolOuterClass.RoomInfo(roomIndex, games[roomIndex].getClientCount());
                            profileNum = (int) (Math.random() * 6);
                            games[roomIndex].setProfile0(profileNum);
                            games[roomIndex].setIpAddress0(ipAddress);

                            sendMsg = new ProtocolOuterClass.Protocol("User"
                                    , gson.toJson(new ProtocolOuterClass.User(clientNum, profileNum, ipAddress)));
                            System.out.println(sendMsg);
                            sendJsonToGame(sendMsg);
                            sendMsg = new ProtocolOuterClass.Protocol("Lobby", gson.toJson(new ProtocolOuterClass.Lobby(roomInfos)));
                            sendJson(sendMsg);
                        } else if (games[roomIndex].getClientCount() == 1) {
                            gameMembers = games[roomIndex].member;
                            gameMembers.add(this);
                            clientNum = games[roomIndex].getClient();
                            roomInfos[roomIndex].clientIn();
                            profileNum = (int) (Math.random() * 6);
                            games[roomIndex].setProfile1(profileNum);
                            games[roomIndex].setIpAddress1(ipAddress);

                            sendMsg = new ProtocolOuterClass.Protocol("User"
                                    , gson.toJson(new ProtocolOuterClass.User(clientNum, profileNum, ipAddress)));
                            System.out.println(sendMsg);
                            sendJsonToGame(sendMsg);

                            sendMsg = new ProtocolOuterClass.Protocol("User"
                                    , gson.toJson(new User(0, games[roomIndex].getProfile0(), games[roomIndex].getIpAddress0())));
                            sendJsonToGame(sendMsg);
                            if (games[roomIndex].getClient0Ready()) {
                                sendMsg = new Protocol("Ready"
                                        , gson.toJson(new Ready(0)));
                                sendJsonToGame(sendMsg);
                            }
                            sendMsg = new Protocol("Lobby", gson.toJson(new Lobby(roomInfos)));
                            sendJson(sendMsg);
                        }
                        break;
                    case "Ready":
                        Ready ready = gson.fromJson(receiveMsg.getJson(), Ready.class);
                        switch (ready.getClientNum()) {
                            case 0:
                                games[roomIndex].setClient0Ready(true);
                                sendMsg = new Protocol("Ready"
                                        , gson.toJson(new Ready(clientNum)));
                                sendJsonToGame(sendMsg);
                                break;
                            case 1:
                                games[roomIndex].setClient1Ready(true);
                                sendMsg = new Protocol("Ready"
                                        , gson.toJson(new Ready(clientNum)));
                                sendJsonToGame(sendMsg);
                                break;
                        }

                        if (games[roomIndex].allReady()) {
                            while (games[roomIndex].getDice0() == games[roomIndex].getDice1()) {
                                games[roomIndex].setDice();
                            }
                            sendMsg = new Protocol("Dice"
                                    , gson.toJson(new Dice(games[roomIndex].getDice0(), games[roomIndex].getDice1())));
                            sendJsonToGame(sendMsg);
                            games[roomIndex].setClient0Ready(false);
                            games[roomIndex].setClient1Ready(false);
                        }
                        break;

                    case "Unready":
                        Unready unready = gson.fromJson(receiveMsg.getJson(), Unready.class);
                        switch (unready.getClientNum()) {
                            case 0:
                                games[roomIndex].setClient0Ready(false);
                                sendMsg = new Protocol("Unready"
                                        , gson.toJson(new Unready(clientNum)));
                                sendJsonToGame(sendMsg);
                                break;
                            case 1:
                                games[roomIndex].setClient1Ready(false);
                                sendMsg = new Protocol("Unready"
                                        , gson.toJson(new Unready(clientNum)));
                                sendJsonToGame(sendMsg);
                                break;
                        }
                        break;

                    case "Check":
                        Check check = gson.fromJson(receiveMsg.getJson(), Check.class);
                        int x = check.getXpos();
                        int y = check.getYpos();
                        int color = Omok.NONE;

                        switch (check.getColor()) {
                            case Omok.BLACK:
                                color = Omok.BLACK;
                                break;
                            case Omok.WHITE:
                                color = Omok.WHITE;
                                break;
                        }

                        if (games[roomIndex].getOrder() == color
                                && !Omok.isForbidden(x, y, games[roomIndex])) {
                            games[roomIndex].setBoard(x, y, color);
                            sendMsg = new Protocol("Do"
                                    , gson.toJson(new Do(x, y, color)));
                            sendJsonToGame(sendMsg);
                            games[roomIndex].nextOrder();

                            if (Omok.win(x, y, color, games[roomIndex])) {
                                sendMsg = new ProtocolOuterClass.Protocol("Win"
                                        , gson.toJson(new Win(color)));
                                sendJsonToGame(sendMsg);
                                games[roomIndex].setClient1Ready(false);
                                games[roomIndex].setClient0Ready(false);
                                for (int i = 0; i < 15; ++i) {
                                    for (int j = 0; j < 15; ++j) {
                                        games[roomIndex].setBoard(i, j, Omok.NONE);
                                    }
                                }
                                games[roomIndex].setDice();
                                games[roomIndex].initOrder();
                            }
                        }
                        break;
                }
            }
        } catch (IOException e1) {
            gameMembers.remove(this);
        }

        sendMsg = new ProtocolOuterClass.Protocol("Out"
                , gson.toJson(new ProtocolOuterClass.Out(clientNum)));
        sendJsonToGame(sendMsg);

        switch (clientNum) {
            case 0:
                games[roomIndex].setIpAddress0(games[roomIndex].getIpAddress1());
                games[roomIndex].setProfile0(games[roomIndex].getProfile1());

            case 1:
                games[roomIndex].setIpAddress1(null);
                games[roomIndex].setProfile1(0);
                games[roomIndex].setDice();
                games[roomIndex].setClient1Ready(false);
                games[roomIndex].setClient0Ready(false);
                for (ServerThread serverThread : gameMembers) {
                    serverThread.clientNum = 0;
                }
                games[roomIndex].initBoard();
                break;
        }
        games[roomIndex].clientDisconnected();
        roomInfos[roomIndex].clientOut();
        games[roomIndex].initOrder();
        gameMembers.remove(this);
        clientList.remove(this);

        if (games[roomIndex].getClientCount() == 0) {
            games[roomIndex] = null;
            roomInfos[roomIndex] = null;
            for (int i = roomIndex; i < roomInfos.length - 1; ++i) {
                roomInfos[i] = roomInfos[i + 1];
                if(roomInfos[i] != null) {
                    roomInfos[i].setRoomNumber();
                }
                if (clientList.get(i).roomIndex > 0) clientList.get(i).roomIndex--;
                games[i] = games[i+1];
            }
        }
        try {
            sendMsg = new ProtocolOuterClass.Protocol("Lobby", gson.toJson(new ProtocolOuterClass.Lobby(roomInfos)));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        sendJson(sendMsg);

        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendJson(ProtocolOuterClass.Protocol sendMsg) {
        String json = gson.toJson(sendMsg);
        System.out.println("send : " + json);
        byte[] data = json.getBytes();
        int len = data.length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(len);
        byteBuffer.flip();
        broadcast(byteBuffer);
        byteBuffer = ByteBuffer.wrap(data);
        broadcast(byteBuffer);
    }

    private void sendJsonToGame(ProtocolOuterClass.Protocol sendMsg) {
        String json = gson.toJson(sendMsg);
        System.out.println("send : " + json);
        byte[] data = json.getBytes();
        int len = data.length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(len);
        byteBuffer.flip();
        broadcastInGame(byteBuffer);
        byteBuffer = ByteBuffer.wrap(data);
        broadcastInGame(byteBuffer);
    }

    private ProtocolOuterClass.Protocol receiveJson(byte[] data, ByteBuffer byteBuffer, int len) {
        byteBuffer.get(data, 0, len);
        String json = new String(data, 0, len);
        ProtocolOuterClass.Protocol receiveMsg = gson.fromJson(json, ProtocolOuterClass.Protocol.class);
        System.out.println("receive : " + json);
        return receiveMsg;
    }
}
