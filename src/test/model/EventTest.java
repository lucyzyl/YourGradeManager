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

    //EXPLANATION FOR POSSIBLE FAILING TEST: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.
    @Test
    public void testEvent() {
        assertEquals("Course added: cpsc210", e.getDescription()); //(1)
        assertEquals(d, e.getDate()); //(2)
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


