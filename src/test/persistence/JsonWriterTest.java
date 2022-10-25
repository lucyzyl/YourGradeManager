package persistence;

import model.Assessment;
import model.Course;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("testUser");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            User user = new User("testEmptyUser");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            user = reader.read();
            assertEquals("testEmptyUser", user.getUserName());
            assertEquals(0, user.getCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("testGeneralUser");
            //set up a course MATH200. There is 1 assessment in this course and 1 grade in this assessment:
            user.addCourse(new Course("MATH200"));
            Course testCourse = user.getCourses().get(0);
            testCourse.addAssessment(new Assessment("final", 1.0));
            Assessment testAssessment = testCourse.getCourseAssessments().get(0);
            testAssessment.addAssignmentGrade(100);
            //write the user into the file:
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();
            //read the user out of the file:
            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.read();

            assertEquals("testGeneralUser", user.getUserName());
            assertEquals(1, user.getCourses().size());
            assertEquals("MATH200", user.getCourses().get(0).getCourseName());
            assertEquals("final", user.getCourses().get(0).getCourseAssessments().get(0).getName());
            assertEquals(100, user.getCourses().get(0).getCourseAssessments().
                    get(0).getAssignmentScores().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

