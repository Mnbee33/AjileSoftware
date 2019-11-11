package digitalClock;

public class MockTimeSource implements TimeSource {
    private ClockObserver itsObserver;

    @Override
    public void registerObserver(ClockObserver observer) {
        itsObserver = observer;
    }

    public void setTime(int hours, int minutes, int seconds) {
        itsObserver.update(hours, minutes, seconds);
    }
}
