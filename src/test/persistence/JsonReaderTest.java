package persistence;

import model.Assessment;
import model.Course;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUser() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals("testEmptyUser", user.getUserName());
            assertEquals(0, user.getCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("testGeneralUser", user.getUserName());

            ArrayList<Course> testCourses = user.getCourses();
            Course testCourse = testCourses.get(0);
            assertEquals(1, testCourses.size());
            assertEquals("MATH200", testCourse.getCourseName());

            ArrayList<Assessment> testAssessments = testCourse.getCourseAssessments();
            Assessment testAssessment = testAssessments.get(0);
            assertEquals("final", testAssessment.getName());
            assertEquals(1, testAssessment.getAssignmentScores().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}








