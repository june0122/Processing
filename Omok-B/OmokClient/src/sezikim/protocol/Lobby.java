package sezikim.protocol;

import sezikim.client.RoomInfo;

public class Lobby {
    private RoomInfo[] roomInfos;

    public Lobby(RoomInfo[] roomInfos) {
        this.roomInfos = roomInfos;
    }

    public RoomInfo[] getRoomInfos() {
        return roomInfos;
    }
}
