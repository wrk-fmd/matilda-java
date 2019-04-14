package at.wrk.fmd.matilda.pojo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

    Event event;
    
    @BeforeEach
    void setUp() throws Exception {
        event = new Event();
        event.setTitle("TestEvent");
        event.setStart("1970-01-01");
        event.setEnd("1970-01-01");
    }

    @Test
    void testTitle() {
        assertThat(event.getTitle().equals("TestEvent"));
    }
    
    @Test
    void testStart() {
        assertThat(event.getStart().equals("1970-01-01"));
    }
    
    @Test
    void testEnd() {
        assertThat(event.getEnd().equals("1970-01-01"));
    }
}