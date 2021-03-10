package sezikim.GUI;

import processing.core.PApplet;

public class Board implements GUI {

    private int x;
    private int y;
    private static int[][] board;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        board = new int[INDEX][INDEX];
    }

    public void drawBoard(PApplet p) {
        drawGridFrame(p);
        p.translate(BOARD_PADDING, BOARD_PADDING);
        drawGrid(p);
        drawStarPoint(p);
        drawCursor(p);
        drawStone(p);
    }

    private void drawStone(PApplet p) {
        for (int j = 0; j < INDEX; ++j) {
            for (int i = 0; i < INDEX; ++i) {
                if (board[j][i] == BLACK) {
                    p.fill(0);
                    p.circle(GRID_MARGIN_HORIZON + (CELL * i) - (CELL / 2),
                            GRID_MARGIN_VERTICAL + (CELL * j) - (CELL / 2), STONE_SIZE);
                } else if (board[j][i] == WHITE) {
                    p.fill(255);
                    p.circle(GRID_MARGIN_HORIZON + (CELL * i) - (CELL / 2),
                            GRID_MARGIN_VERTICAL + (CELL * j) - (CELL / 2), STONE_SIZE);
                }
            }
        }
    }

    private void drawGridFrame(PApplet p) {
        p.noFill();
        p.square(BOARD_MARGIN_HORIZON, BOARD_MARGIN_VERTICAL, BOARD_SIZE);
    }

    private void drawGrid(PApplet p) {
        for (int i = 0; i < INDEX; i++) {
            for (int j = 0; j < INDEX; j++) {
                p.line(x + (CELL * i), y, x + (CELL * i), y + (CELL * j));
                p.line(x, y + (CELL * j), x + (CELL * i), y + (CELL * j));
            }
        }
    }

    private void drawStarPoint(PApplet p) {
        p.fill(50);
        p.circle(x + (CELL * 3), y + (CELL * 3), STAR_POINT);
        p.circle(x + (CELL * 3), y + (CELL * 11), STAR_POINT);
        p.circle(x + (CELL * 11), y + (CELL * 3), STAR_POINT);
        p.circle(x + (CELL * 11), y + (CELL * 11), STAR_POINT);
    }

    private void drawCursor(PApplet p) {
        int x = (p.mouseX - BOARD_MARGIN_HORIZON) / CELL;
        int y = (p.mouseY - BOARD_MARGIN_VERTICAL) / CELL;

        if (isCursorOnPoint(p) && board[y][x] == EMPTY) {
            p.cursor(p.HAND);
        } else {
            p.cursor(p.ARROW);
        }
    }

    public boolean isCursorOnPoint(PApplet p) {
        return ((p.mouseX - GRID_MARGIN_HORIZON) % CELL < TOLERANCE
                || (p.mouseX - GRID_MARGIN_HORIZON) % CELL > CELL - TOLERANCE)
                && ((p.mouseY - GRID_MARGIN_VERTICAL) % CELL < TOLERANCE
                || (p.mouseY - GRID_MARGIN_VERTICAL) % CELL > CELL - TOLERANCE)
                && isCursorInBoard(p);
    }

    private boolean isCursorInBoard(PApplet p) {
        return p.mouseX > BOARD_MARGIN_HORIZON
                && p.mouseX < BOARD_MARGIN_HORIZON + BOARD_SIZE
                && p.mouseY > BOARD_MARGIN_VERTICAL
                && p.mouseY < BOARD_MARGIN_VERTICAL + BOARD_SIZE;
    }

    public static void setBoard(int x, int y, int color) {
        board[y][x] = color;
    }

    public static int getBoard(int x, int y) {
        return board[y][x];
    }

    public static void initBoard() {
        board = new int[INDEX][INDEX];
    }
}