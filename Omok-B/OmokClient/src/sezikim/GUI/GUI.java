package sezikim.GUI;

public interface GUI {
    int WINDOW_WIDTH = 450;
    int WINDOW_HEIGHT = 800;

    int EMPTY = 0;
    int BLACK = 1;
    int WHITE = 2;
    int STONE_SIZE = 20;
    int CELL = 28;
    int INDEX = 15;
    int STAR_POINT = 5;
    int TOLERANCE = 5;
    int BOARD_SIZE = 420;
    int BOARD_PADDING = 14;
    int BOARD_MARGIN_HORIZON = 15;
    int BOARD_MARGIN_VERTICAL = 190;
    int GRID_MARGIN_HORIZON = BOARD_PADDING + BOARD_MARGIN_HORIZON;
    int GRID_MARGIN_VERTICAL = BOARD_PADDING + BOARD_MARGIN_VERTICAL;

    int BUTTON_WIDTH = 380;
    int BUTTON_HEIGHT = 130;
    int PADDING = 4;
    int RECT_HIGHLIGHT = 240;
    int RECT_COLOR = 255;

    int MATCHING_WIDTH = 180;
    int MATCHING_HEIGHT = 540;
    int TRANS_X1 = -20;
    int TRANS_X2 = -220;
    int TRANS_Y1 = 0;
    int TRANS_Y2 = 580;
    int IN_GAME_WIDTH = 420;
    int IN_GAME_HEIGHT = 120;
    int BORDER_HEIGHT = 5;

    int INIT_CLIENTNUM = 9999;
    int GAME_LOBBY = 0;
    int GAME_WAIT = 1;
    int GAME = 2;
    int RESULT = 3;
}
