package digitalClock;


public class MockTimeSource extends Subject implements TimeSource {
    private int itsHours;
    private int itsMinutes;
    private int itsSeconds;

    public void setTime(int hours, int minutes, int seconds) {
        itsHours = hours;
        itsMinutes = minutes;
        itsSeconds = seconds;
        notifyObservers();
    }

    @Override
    public int getHours() {
        return itsHours;
    }

    @Override
    public int getMinutes() {
        return itsMinutes;
    }

    @Override
    public int getSeconds() {
        return itsSeconds;
    }
}
