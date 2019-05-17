import processing.core.PApplet;

public class OmokGui extends PApplet {

    private Board board;

    public static void main(String[] args) {
        PApplet.main("OmokGui");
    }

    public void setup() {
        board = new Board(15, 190);
    }

    public void settings() {
        size(450, 800);
    }

    public void draw() {
        background(255);
        drawGameScreen();
    }

    // 게임 화면
    private void drawGameScreen() {
        board.drawBoard(this);
    }

}
