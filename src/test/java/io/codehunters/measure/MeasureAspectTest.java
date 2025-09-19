package io.codehunters.measure;

import static org.junit.jupiter.api.Assertions.*;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.search.Search;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class MeasureAspectTest {

    @Autowired
    private TestService service;

    @Autowired
    private MeterRegistry registry;

    @Test
    void recordsTimerForAnnotatedMethod() {
        String res = service.doWork();
        assertEquals("ok", res);

        Timer timer = Search.in(registry)
                .name("service.test")
                .tags("endpoint", "doWork", "kind", "test")
                .timer();
        assertNotNull(timer, "Expected timer 'service.test' to be registered with tags");
        assertEquals(1, timer.count(), "Timer should have recorded exactly one event");
    }

    @Test
    void recordsDynamicTagsFromExpressions() {
        service.processUser("123");

        Timer timer =
                Search.in(registry).name("service.expr").tag("user", "123").timer();
        assertNotNull(timer, "Expected timer 'service.expr' with tag user=123");
        assertEquals(1, timer.count(), "Timer should have recorded one event for expression-based tags");
    }

    @Test
    void recordsExceptionCounterWhenMethodThrows() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> service.willFail());
        assertEquals("boom", thrown.getMessage());

        var counter = Search.in(registry)
                .name("service.error.errors")
                .tag("exception", "RuntimeException")
                .counter();
        assertNotNull(counter, "Expected error counter to be present");
        assertEquals(1.0, counter.count(), 0.0001, "Error counter should be incremented to 1");
    }

    @Test
    void createsLongTaskTimerWhenConfigured() {
        service.longOp();
        var ltt = Search.in(registry).name("service.long.long-task").longTaskTimer();
        assertNotNull(ltt, "Expected long task timer to be created");
        assertEquals(0, ltt.activeTasks(), "No active tasks should remain after method completion");
    }
}
