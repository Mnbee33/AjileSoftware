package digitalClock;

public interface TimeSource {
    void registerObserver(ClockObserver observer);
}
