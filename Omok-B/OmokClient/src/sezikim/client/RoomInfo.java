package sezikim.client;

public class RoomInfo {
    int roomNumber;
    int clientCount;

    public RoomInfo(int roomNumber, int clientCount) {
        this.roomNumber = roomNumber;
        this.clientCount = clientCount;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getClientCount() {
        return clientCount;
    }
    public void clientIn() {
        clientCount++;
    }
}
