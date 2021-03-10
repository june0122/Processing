package sezikim.server;

import sezikim.protocol.ProtocolOuterClass;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private static final int MAX_GAME_NUMBER = 7;

    public static void main(String[] args) {
        List<ServerThread> clientList = new ArrayList<>();
        Game[] games = new Game[MAX_GAME_NUMBER];
        ProtocolOuterClass.RoomInfo[] roomInfos = new ProtocolOuterClass.RoomInfo[MAX_GAME_NUMBER];

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.configureBlocking(true);
            serverSocketChannel.bind(new InetSocketAddress(5300));

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                ServerThread serverThread = new ServerThread(
                        socketChannel, clientList, games, roomInfos);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}