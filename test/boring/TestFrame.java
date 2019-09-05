package boring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFrame {
    @Test
    void testScoreNoThrows() {
        Frame f = new Frame();
        assertEquals(0, f.getScore());
    }

    @Test
    void testAddOneThrows() {
        Frame f = new Frame();
        f.add(5);
        assertEquals(5, f.getScore());
    }
}
