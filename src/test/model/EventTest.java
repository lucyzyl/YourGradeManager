package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("Course added: cpsc210");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Course added: cpsc210", e.getDescription()); //(1)
        assertEquals(d.getDay(), e.getDate().getDay()); //(2)
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Course added: cpsc210", e.toString());
    }

    @Test
    public void testHashCode() {
        assertEquals(Event.HASH_CONSTANT * e.getDate().hashCode() + e.getDescription().hashCode(), e.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(e.equals(null));
        assertFalse(e.equals(d));
    }
}


