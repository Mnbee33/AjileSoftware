package boring;

public class Frame {
    private int itsScore;
    public int getScore() {
        return itsScore;
    }

    public void add(int pins) {
        itsScore += pins;
    }
}
