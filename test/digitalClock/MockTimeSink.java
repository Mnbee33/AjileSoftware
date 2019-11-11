package digitalClock;

public class MockTimeSink implements Observer {
    private int itsHours;
    private int itsMinutes;
    private int itsSeconds;
    private TimeSource itsSource;

    public MockTimeSink(TimeSource source) {
        itsSource = source;
    }

    public int getHours() {
        return itsHours;
    }

    public int getMinutes() {
        return itsMinutes;
    }

    public int getSeconds() {
        return itsSeconds;
    }

    public void update() {
        itsHours = itsSource.getHours();
        itsMinutes = itsSource.getMinutes();
        itsSeconds = itsSource.getSeconds();
    }
}
