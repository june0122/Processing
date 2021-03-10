package sezikim.protocol;

public class Protocol {

    private String messageName;
    private String json;

    public Protocol(String messageName, String json) {
        this.messageName = messageName;
        this.json = json;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getJson() {
        return json;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
