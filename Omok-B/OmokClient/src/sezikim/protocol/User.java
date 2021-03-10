package sezikim.protocol;

public class User {
    private int clientNum;
    private int profileNum;
    private String ipAddress;

    public User(int clientNum, int profileNum, String ipAddress) {
        this.clientNum = clientNum;
        this.profileNum = profileNum;
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getProfileNum() {
        return profileNum;
    }

    public int getClientNum() {
        return clientNum;
    }
}
