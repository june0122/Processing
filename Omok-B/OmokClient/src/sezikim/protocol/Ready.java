package sezikim.protocol;

public class Ready {
    private int clientNum;

    public Ready(int clientNum) {
        this.clientNum = clientNum;
    }

    public int getClientNum() {
        return clientNum;
    }
}
