import processing.core.PApplet;

class Board {
    private static final int EMPTY = 0;
    private static final int BLACK = 1;
    private static final int WHITE = 2;
    private static final int STONE_SIZE = 20;                                               // 오목돌의 크기(지름)

    private static final int CELL = 28;                                                     // 오목판 한 칸(셀)의 크기
    private static final int INDEX = 15;                                                    // 오목판의 줄 수
    private static final int STAR_POINT = 5;                                                // 화점, 성점(星點, star point)
    private static final int TOLERANCE = 5;                                                 // 마우스 클릭 좌표의 '허용 오차(tolerance)'
    private static final int BOARD_SIZE = 420;                                              // 오목판의 크기
    private static final int BOARD_PADDING = 14;                                            // 오목판의 패딩(내부 여백)
    private static final int BOARD_MARGIN_HORIZON = 15;                                     // 오목판의 수평 마진(외부 여백)
    private static final int BOARD_MARGIN_VERTICAL = 190;                                   // 오목판의 수직 마진
    private static final int GRID_MARGIN_HORIZON = BOARD_PADDING + BOARD_MARGIN_HORIZON;    // 오목 격자의 수평 마진
    private static final int GRID_MARGIN_VERTICAL = BOARD_PADDING + BOARD_MARGIN_VERTICAL;  // 오목 격자의 수직 마진

    private int x;
    private int y;
    private int[][] board;
    private boolean stoneFlag = true;

    Board(int x, int y) {
        this.x = x;
        this.y = y;
        board = new int[INDEX][INDEX];  // 오목판의 모든 칸을 공백으로 초기화
    }

    void drawBoard(PApplet p) {
        drawGridFrame(p);
        p.translate(BOARD_PADDING, BOARD_PADDING);
        drawGrid(p);
        drawStarPoint(p);
        drawCursor(p);
        mouseClicked(p);
        drawStone(p);
    }

    private void mouseClicked(PApplet p) {
        int x = (p.mouseX - BOARD_MARGIN_HORIZON) / CELL;
        int y = (p.mouseY - BOARD_MARGIN_VERTICAL) / CELL;

        if (stoneFlag) {
            if (p.mousePressed && isCursorOnPoint(p) && board[y][x] == EMPTY) {
                board[y][x] = BLACK;
                drawStone(p);
                stoneFlag = false;
            }
        } else {
            if (p.mousePressed && isCursorOnPoint(p) && board[y][x] == EMPTY) {
                board[y][x] = WHITE;
                drawStone(p);
                stoneFlag = true;
            }
        }
        checkVertical(p);
        checkHorizon(p);
  /*      checkNegativeDiagonal(p);
        checkPositiveDiagonal(p);*/
    }

    // 오목돌 그리기
    private void drawStone(PApplet p) {
        for (int j = 0; j < 15; ++j) {
            for (int i = 0; i < 15; ++i) {
                if (board[j][i] == BLACK) {
                    p.fill(0);
                    p.circle(GRID_MARGIN_HORIZON + (CELL * i) - (CELL / 2), GRID_MARGIN_VERTICAL + (CELL * j) - (CELL / 2), STONE_SIZE);
                } else if (board[j][i] == WHITE) {
                    p.fill(255);
                    p.circle(GRID_MARGIN_HORIZON + (CELL * i) - (CELL / 2), GRID_MARGIN_VERTICAL + (CELL * j) - (CELL / 2), STONE_SIZE);
                }
            }
        }
    }

    // 오목 격자 외부 틀
    private void drawGridFrame(PApplet p) {
        p.noFill();
        p.square(BOARD_MARGIN_HORIZON, BOARD_MARGIN_VERTICAL, BOARD_SIZE);
    }

    // 오목 격자 그리기
    private void drawGrid(PApplet p) {
        for (int i = 0; i < INDEX; i++) {
            for (int j = 0; j < INDEX; j++) {
                p.line(x + (CELL * i), y, x + (CELL * i), y + (CELL * j));
                p.line(x, y + (CELL * j), x + (CELL * i), y + (CELL * j));
            }
        }
    }

    // 격자 위 4개의 화점(성점) 그리기
    // 화점의 좌표 : (3, 3), (3, 11), (11, 3), (11, 11)
    private void drawStarPoint(PApplet p) {
        p.fill(50);
        p.circle(x + (CELL * 3), y + (CELL * 3), STAR_POINT);
        p.circle(x + (CELL * 3), y + (CELL * 11), STAR_POINT);
        p.circle(x + (CELL * 11), y + (CELL * 3), STAR_POINT);
        p.circle(x + (CELL * 11), y + (CELL * 11), STAR_POINT);
    }

    // 커서의 모양 결정 (Arrow or Hand)
    private void drawCursor(PApplet p) {
        int x = (p.mouseX - BOARD_MARGIN_HORIZON) / CELL;
        int y = (p.mouseY - BOARD_MARGIN_VERTICAL) / CELL;

        // ① 커서가 좌표 위에 있고, ② 해당 좌표가 비어있을 경우에만 커서를 손 모양으로 변경
        if (isCursorOnPoint(p) && board[y][x] == EMPTY) {
            p.cursor(p.HAND);
        } else {
            p.cursor(p.ARROW);
        }
    }

    // 커서가 오목판 좌표 위에 놓여져 있는지 체크
    private boolean isCursorOnPoint(PApplet p) {
        return ((p.mouseX - GRID_MARGIN_HORIZON) % CELL < TOLERANCE || (p.mouseX - GRID_MARGIN_HORIZON) % CELL > CELL - TOLERANCE)
                && ((p.mouseY - GRID_MARGIN_VERTICAL) % CELL < TOLERANCE || (p.mouseY - GRID_MARGIN_VERTICAL) % CELL > CELL - TOLERANCE)
                && isCursorInBoard(p);
    }

    // 커서가 오목판 범위 내에 있는지 체크
    private boolean isCursorInBoard(PApplet p) {
        return p.mouseX > BOARD_MARGIN_HORIZON && p.mouseX < BOARD_MARGIN_HORIZON + BOARD_SIZE
                && p.mouseY > BOARD_MARGIN_VERTICAL && p.mouseY < BOARD_MARGIN_VERTICAL + BOARD_SIZE;
    }

    private void checkVertical(PApplet p) {
        int x = (p.mouseX - BOARD_MARGIN_HORIZON) / CELL;
        int omokCount = 1;

        if (x < 0) {
            x = 0;
        } else if (x > INDEX - 1) {
            x = INDEX - 1;
        }

        for (int column = 0; column < INDEX - 2; column++) {
            if ((board[column][x] == board[column + 1][x]) && board[column][x] != EMPTY) {
                omokCount++;
                if (omokCount == 5) {
                    System.out.println("오목 완성");
                }
            }
        }
    }

    private void checkHorizon(PApplet p) {
        int y = (p.mouseY - BOARD_MARGIN_VERTICAL) / CELL;
        int omokCount = 1;

        if (y < 0) {
            y = 0;
        } else if (y > INDEX - 1) {
            y = INDEX - 1;
        }

        for (int row = 0; row < INDEX - 2; row++) {
            if ((board[y][row] == board[y][row + 1]) && board[y][row] != EMPTY) {
                omokCount++;
                if (omokCount == 5) {
                    System.out.println("오목 완성");
                }
            }
        }
    }

    // 음의 기울기를 가진 대각선 체크
    private void checkNegativeDiagonal(PApplet p) {
        int x = (p.mouseX - BOARD_MARGIN_HORIZON) / CELL;
        int y = (p.mouseY - BOARD_MARGIN_VERTICAL) / CELL;
        int omokCount = 1;

        if (x < 0) {
            x = 0;
        } else if (x > INDEX - 1) {
            x = INDEX - 1;
        }

        if (y < 0) {
            y = 0;
        } else if (y > INDEX - 1) {
            y = INDEX - 1;
        }

        if (x > y) {
                int interval = x - y;
                for (int row = interval; row < INDEX - 2; row++) {
                    int column = y - (x - row);
                    if ((board[column][row] == board[column + 1][row + 1]) && board[column][row] != EMPTY) {
                        omokCount++;
                        if (omokCount == 5) {
                            System.out.println("오목 완성");
                        }
                    }
                }
            } else {
                int interval = y - x;
                for (int row = 0; row < INDEX - 2 - interval; row++) {
                    int column = y - (x - row);
                    if ((board[column][row] == board[column + 1][row + 1]) && board[column][row] != EMPTY) {
                        omokCount++;
                        if (omokCount == 5) {
                            System.out.println("오목 완성");
                        }
                    }
                }
        }
    }

    // 양의 기울기를 가진 대각선 체크
    private void checkPositiveDiagonal(PApplet p) {
        int x = (p.mouseX - BOARD_MARGIN_HORIZON) / CELL;
        int y = (p.mouseY - BOARD_MARGIN_VERTICAL) / CELL;
        int omokCount = 1;

        if (x < 0) {
            x = 0;
        } else if (x > INDEX - 1) {
            x = INDEX - 1;
        }

        if (y < 0) {
            y = 0;
        } else if (y > INDEX - 1) {
            y = INDEX - 1;
        }


    }

    }

