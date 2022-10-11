package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {
    User testUser;
    Course course1;
    Course course2;

    @BeforeEach
    public void setup() {
        testUser = new User("Ziyang");
    }

    @Test
    public void testConstructor() {
        assertEquals("Ziyang", testUser.getUserName());
        assertEquals(0, testUser.getCourses().size());
    }

    @Test
    public void testAddCourseOne() {
        course1 = new Course("CPSC 110");
        testUser.addCourse(course1);
        assertEquals(1, testUser.getCourses().size());
        assertEquals(course1, testUser.getCourses().get(0));
    }

    @Test void testAddCourseMultiple() {
        course1 = new Course("CPSC 110");
        course2 = new Course("CPSC 121");
        testUser.addCourse(course1);
        testUser.addCourse(course2);
        assertEquals(2, testUser.getCourses().size());
        assertEquals(course1, testUser.getCourses().get(0));
        assertEquals(course2, testUser.getCourses().get(1));
    }
}
