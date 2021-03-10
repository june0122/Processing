package sezikim.server;

public class Omok {
    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int BOARD_INDEX = 15;

    public static boolean win(int x, int y, int COLOR, Game game) {
        int len1 = 0;
        int len2 = 0;
        int len3 = 0;
        int len4 = 0;

        for (int i = 1; i < 5; ++i) {
            for (Direction direction : Direction.values()) {
                if (direction.inBoard(x, y, i)
                        && direction.checkColor(x, y, i, game) == COLOR) {
                    switch (direction) {
                        case N:
                        case S:
                            len1++;
                            break;
                        case NE:
                        case SW:
                            len2++;
                            break;
                        case E:
                        case W:
                            len3++;
                            break;
                        case SE:
                        case NW:
                            len4++;
                    }
                }
            }
        }

        return len1 > 3 || len2 > 3 || len3 > 3 || len4 > 3;
    }

    public static boolean isForbidden(int x, int y, Game game) {
        int sam1 = 0;
        int sam2 = 0;
        int sam3 = 0;
        int sam4 = 0;

        for (int i = 1; i < 5; ++i) {
            for (Direction direction : Direction.values()) {
                if (direction.inBoard(x, y, i)
                        && direction.checkColor(x, y, i, game) == BLACK
                        && direction.inBoard(x, y, i + 1)
                        && direction.checkColor(x, y, i, game) != WHITE) {
                    switch (direction) {
                        case N:
                        case S:
                            sam1++;
                            break;
                        case NE:
                        case SW:
                            sam2++;
                            break;
                        case E:
                        case W:
                            sam3++;
                            break;
                        case SE:
                        case NW:
                            sam4++;
                    }
                }
            }
        }

        return (sam1 + sam2 > 3 || sam1 + sam3 > 3
                || sam1 + sam4 > 3 || sam2 + sam3 > 3
                || sam2 + sam4 > 3 || sam3 + sam4 > 3) && sam1 <= 3
                && sam2 <= 3 && sam3 <= 3 && sam4 <= 3;
    }
}

