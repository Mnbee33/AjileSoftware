package digitalClock;

public class ClockDriver implements ClockObserver {
    private TimeSink itsSink;

    public ClockDriver(TimeSource source, TimeSink sink) {
        source.registerObserver(this);
        itsSink = sink;
    }

    @Override
    public void update(int hours, int minutes, int seconds) {
        itsSink.setTime(hours, minutes, seconds);
    }
}
