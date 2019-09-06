package boring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
    private Game g;

    @BeforeEach
    void setUp() {
        g = new Game();
    }

    @Test
    void testTwoThrowsNoMark() {
        g.add(5);
        g.add(4);
        assertEquals(9, g.score());
        assertEquals(2, g.getCurrentFrame());
    }

    @Test
    void testFourThrowsNoMark() {
        g.add(5);
        g.add(4);
        g.add(7);
        g.add(2);
        assertEquals(18, g.score());
        assertEquals(9, g.scoreForFrame(1));
        assertEquals(18, g.scoreForFrame(2));
        assertEquals(3, g.getCurrentFrame());

    }

    @Test
    void testSimpleSpare() {
        g.add(3);
        g.add(7);
        g.add(3);
        assertEquals(13, g.scoreForFrame(1));
        assertEquals(2, g.getCurrentFrame());
    }

    @Test
    void testSimpleFrameAfterSpare() {
        g.add(3);
        g.add(7);
        g.add(3);
        g.add(2);
        assertEquals(13, g.scoreForFrame(1));
        assertEquals(18, g.scoreForFrame(2));
        assertEquals(18, g.score());
        assertEquals(3, g.getCurrentFrame());
    }

    @Test
    void testSimpleStrike() {
        g.add(10);
        g.add(3);
        g.add(6);
        assertEquals(19, g.scoreForFrame(1));
        assertEquals(28, g.score());
        assertEquals(3, g.getCurrentFrame());
    }
}
