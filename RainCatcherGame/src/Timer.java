import processing.core.PApplet;

public class Timer {
    PApplet parent;

    int savedTime;
    int totalTime;

    Timer(int tempTotalTime, PApplet p) {
        parent = p;
        totalTime = tempTotalTime;
    }

    public void start() {
        savedTime = parent.millis();
    }

    public boolean isFinished() {
        int passTime = parent.millis() - savedTime;
        if (passTime > totalTime) {
            return true;
        } else {
            return false;
        }
    }
}
