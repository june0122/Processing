# OmokGame

## OmokServer

### main 클래스

- 서버 소켓 포트 지정
- 서버 스레드 스타트

- 승리 조건 체크 : `checkWin()`
  
  - 승리자 텍스트로 표시 

- 돌 좌표 중복 체크 : `checkOverlap()`


### ReceiveThread 클래스

- 사용자가 돌을 놓은 위치 받아오기 : `getPos()`

### SendThread 클래스

- 받아온 **getPos()** 를 클라이언트에게 재전송 : `sendPos()`

- 중복 경고 : `noticeOverlap`

- 흑/백 지정

-------------------------------------------------------

## OmokClient

### main 클래스

> 상수

```java
private static final int WIDTH = 640;
private static final int HEIGHT = 640;
private static final int SQUARE = 40;
private static final int NONE = 0;
private static final int BLACK = 1;
private static final int WHITE = 2;
```

- 호스트랑 포트 지정
 
- 샌드스레드 스타트

- 리시브스레드 스타트

- `settings()` :

```java
  public void settings() {
        size(WIDTH, HEIGHT);
        stone = new Stone();
        board = new int[16][16];
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                board[i][j] = NONE;
            }
        }
    }
```


- `draw()` :

  - 오목판 그리기

  - 좌표 지정 가능 여부 표시 (마우스 커서)

  - 바둑돌 그리기 : ReceiveThread 클래스에서 돌의 좌표를 받아와 그린다.

```java
background(218, 165, 32);
        int i, j;
        for (i = 0; i < 16; ++i) {
            line(SQUARE / 2, SQUARE / 2 + SQUARE * i, WIDTH - SQUARE / 2, SQUARE / 2 + SQUARE * i);
        }
        for (j = 0; j < 16; ++j) {
            line(SQUARE / 2 + SQUARE * j, SQUARE / 2, SQUARE / 2 + SQUARE * j, HEIGHT - SQUARE / 2);
        }
        fill(0);

        // 좌표 지정 가능 여부 표시 (마우스 커서)
        if (inRange(mouseX, mouseY)) {
            cursor(HAND);
        } else {
            cursor(ARROW);
        }


        black.display(this);
        white.display(this);
```

```java
public void mouseClicked() {
        int x = mouseX / SQUARE;
        int y = mouseY / SQUARE;
        if (flag) {
            if (inRange(mouseX, mouseY)) {
                if (board[y][x] == NONE
                        && !isForbidden(x, y)) {
                    board[y][x] = BLACK;
                    black.setBlack(x, y);
                    flag = false;
                }
            }
        } else {
            if (inRange(mouseX, mouseY)) {
                if (board[y][x] == NONE) {
                    board[y][x] = WHITE;
                    white.setWhite(x, y);
                    flag = true;
                }
            }
        }
    }
```

> 보드 내부에 있는지 체크

```java
public boolean inBoard(int x, int y) {
        return (x >= 0 && x < 16) && (y >= 0 && y < 16);
    }

    public boolean inRange(int x, int y) {
        return ((x - SQUARE / 2) % SQUARE < 5 || (x - SQUARE / 2) % SQUARE > SQUARE - 5)
                && ((y - SQUARE / 2) % SQUARE < 5 || (y - SQUARE / 2) % SQUARE > SQUARE - 5);
    }
```

```java
public class Stone {
    private int[][] stone;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    Stone() {
        stone = new int[16][16];
    }

    public void display(PApplet p) {
        int i, j;
        for (i = 0; i < 16; ++i) {
            for (j = 0; j < 16; ++j) {
                if (stone[i][j] == 1) {
                    p.fill(0);
                    p.ellipse(Board.getSQUARE() / 2 + Board.getSQUARE() * j,
                            Board.getSQUARE() / 2 + Board.getSQUARE() * i,
                            WIDTH, HEIGHT);
                }
            }
        }
    }

    public void setStone(int x, int y) {
        stone[y][x] = 1;
    }
}
```


### ReceiveThread

- 자신의 돌 흑/백 여부

- Thread 확장 후

- `run()` : 

  - 오목판 상태

  - 자신의 턴인지 체크


## SendThread

- Thread 확장 후

- `run ()` : 

  - 사용자가 돌을 놓은 위치를 서버에 전송

  - 턴 종료
