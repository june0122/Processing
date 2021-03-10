package sezikim.protocol;

public class Do {
    private int xpos;
    private int ypos;
    private int color;

    public Do(int xpos, int ypos, int color) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }
}
