import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    void init() {
        Play play = new Play();
    }

    @Test
    void initSentences_infinteLoop_returnFalse() {
        assertTimeout(Duration.ofMillis(3000), () -> {

        });
    }
}