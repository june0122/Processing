package sezikim.protocol;

public class Check {
    private int xpos;
    private int ypos;
    private int color;

    public Check(int xpos, int ypos, int color) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.color = color;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public int getColor() {
        return color;
    }
}
