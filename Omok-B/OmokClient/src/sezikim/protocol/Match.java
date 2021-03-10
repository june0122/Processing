package sezikim.protocol;

public class Match {
    private int clientNum;
    private int profileNum;
    private String ipAddress;

    public Match(int clientNum, int profileNum, String ipAddress) {
        this.clientNum = clientNum;
        this.profileNum = profileNum;
        this.ipAddress = ipAddress;
    }

    public int getClientNum() {
        return clientNum;
    }

    public int getProfileNum() {
        return profileNum;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
