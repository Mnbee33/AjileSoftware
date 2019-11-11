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
        sink = new MockTimeSink(source);
        source.registerObserver(sink);
    }

    @Test
    void testTimeChange() {
        source.setTime(3, 4, 5);
        assertSinkEquals(sink, 3, 4, 5);

        source.setTime(7, 8, 9);
        assertSinkEquals(sink, 7, 8, 9);
    }

    @Test
    void testMultipleSinks() {
        MockTimeSink sink2 = new MockTimeSink(source);
        source.registerObserver(sink2);
        source.setTime(12, 13, 14);

        assertSinkEquals(sink, 12, 13, 14);
        assertSinkEquals(sink2, 12, 13, 14);
    }

    private void assertSinkEquals(MockTimeSink sink, int hours, int minutes, int seconds) {
        assertEquals(hours, this.sink.getHours());
        assertEquals(minutes, this.sink.getMinutes());
        assertEquals(seconds, this.sink.getSeconds());
    }
}
