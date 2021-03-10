package sezikim.client;

import com.google.gson.Gson;
import org.junit.Test;
import sezikim.protocol.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Queue;

public class ClientThread extends Thread {
    private SocketChannel socketChannel;
    private Gson gson;
    private Queue<Protocol> queue;

    ClientThread(SocketChannel socketChannel, Queue<Protocol> queue) {
        this.socketChannel = socketChannel;
        gson = new Gson();
        this.queue = queue;
    }

    @Override
    public void run() {
        Protocol receiveMsg;
        byte[] data = new byte[512];
        ByteBuffer byteBuffer;

        try {
            while (true) {
                byteBuffer = ByteBuffer.allocate(4);
                int dataLen = socketChannel.read(byteBuffer);
                if (dataLen == -1) {
                    System.out.println("패킷 길이 읽기 실패");
                    break;
                }
                byteBuffer.flip();
                int packetLen = byteBuffer.getInt();

                byteBuffer = ByteBuffer.allocate(packetLen);
                dataLen = socketChannel.read(byteBuffer);
                if (dataLen == -1) {
                    System.out.println("패킷 내용 읽기 실패");
                    break;
                }
                byteBuffer.flip();

                receiveMsg = receiveJson(data, byteBuffer, packetLen);

                queue.offer(receiveMsg);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        try {
            socketChannel.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private Protocol receiveJson(byte[] data, ByteBuffer byteBuffer, int len) {
        byteBuffer.get(data, 0, len);
        String json = new String(data, 0, len);
        System.out.println("receive : " + json);
        return gson.fromJson(json, Protocol.class);
    }
}
