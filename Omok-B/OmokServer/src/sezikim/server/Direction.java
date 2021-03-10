package sezikim.server;

enum Direction {
    N {
        public boolean inBoard(int x, int y, int i) {
            return (x >= 0 && x < Omok.BOARD_INDEX) && (y - i >= 0 && y - i < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x, y - i);
        }
    },
    NE {
        public boolean inBoard(int x, int y, int i) {
            return (x + i >= 0 && x + i < Omok.BOARD_INDEX) && (y - i >= 0 && y - i < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x + i, y - i);
        }
    },
    E {
        public boolean inBoard(int x, int y, int i) {
            return (x + i >= 0 && x + i < Omok.BOARD_INDEX) && (y >= 0 && y < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x + i, y);
        }
    },
    SE {
        public boolean inBoard(int x, int y, int i) {
            return (x + i >= 0 && x + i < Omok.BOARD_INDEX) && (y + i >= 0 && y + i < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x + i, y + i);
        }
    },
    S {
        public boolean inBoard(int x, int y, int i) {
            return (x >= 0 && x < Omok.BOARD_INDEX) && (y + i >= 0 && y + i < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x, y + i);
        }
    },
    SW {
        public boolean inBoard(int x, int y, int i) {
            return (x - i >= 0 && x - i < Omok.BOARD_INDEX) && (y + i >= 0 && y + i < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x - i, y + i);
        }
    },
    W {
        public boolean inBoard(int x, int y, int i) {
            return (x - i >= 0 && x - i < Omok.BOARD_INDEX) && (y >= 0 && y < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x - i, y);
        }
    },
    NW {
        public boolean inBoard(int x, int y, int i) {
            return (x - i >= 0 && x - i < Omok.BOARD_INDEX) && (y - i >= 0 && y - i < Omok.BOARD_INDEX);
        }

        public int checkColor(int x, int y, int i, Game game) {
            return game.getBoard(x - i, y - i);
        }
    };

    public abstract boolean inBoard(int x, int y, int i);

    public abstract int checkColor(int x, int y, int i, Game game);
}