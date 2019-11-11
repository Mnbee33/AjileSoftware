package digitalClock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClockDriver {

    private MockTimeSource source;
    private MockTimeSink sink;

    @BeforeEach
    void setUp() {
        source = new MockTimeSource();
        sink = new MockTimeSink();
        source.registerObserver(sink);
    }

    @Test
    void testTimeChange() {
        source.setTime(3, 4, 5);
        assertEquals(3, sink.getHours());
        assertEquals(4, sink.getMinutes());
        assertEquals(5, sink.getSeconds());

        source.setTime(7, 8, 9);
        assertEquals(7, sink.getHours());
        assertEquals(8, sink.getMinutes());
        assertEquals(9, sink.getSeconds());
    }
}
