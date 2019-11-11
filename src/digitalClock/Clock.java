package digitalClock;

public class Clock implements TimeSource {
    public int getSeconds() {
        return 0;
    }

    public int getMinutes() {
        return 0;
    }

    public int getHours() {
        return 0;
    }

    @Override
    public void registerObserver(ClockObserver driver) {

    }
}
