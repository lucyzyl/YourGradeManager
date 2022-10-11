package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    Course testCourse;
    Assessment testAssessment1;
    Assessment testAssessment2;
    Assessment testAssessment3;

    @BeforeEach
    public void setUp() {
        testCourse = new Course("CPSC 110");
    }

    @Test
    public void testConstructor() {
        assertEquals("CPSC 110", testCourse.getCourseName());
        assertEquals(0, testCourse.getCourseAssessments().size());
        assertEquals(0, testCourse.getCourseGrade());
    }

    @Test
    public void testAddAssessmentOne() {
        testAssessment1 = new Assessment("Midterm",0.3);
        testCourse.addAssessment(testAssessment1);
        assertEquals(1, testCourse.getCourseAssessments().size());
        assertEquals(testAssessment1, testCourse.getCourseAssessments().get(0));
    }

    @Test
    public void testAddAssessmentMultiple() {
        testAssessment1 = new Assessment("Midterm",0.3);
        testAssessment2 = new Assessment("Final",0.6);
        testCourse.addAssessment(testAssessment1);
        testCourse.addAssessment(testAssessment2);
        assertEquals(2, testCourse.getCourseAssessments().size());
        assertEquals(testAssessment1, testCourse.getCourseAssessments().get(0));
        assertEquals(testAssessment2, testCourse.getCourseAssessments().get(1));
    }

    @Test
    public void testCalculateCourseGradeAllAssessmentHasValidGrade() {
        testAssessment1 = new Assessment("Midterm",0.3);
        testAssessment2 = new Assessment("Final",0.6);
        testAssessment3 = new Assessment("Homework", 0.1);
        testAssessment1.addAssignmentGrade(90);
        testAssessment2.addAssignmentGrade(95);
        testAssessment3.addAssignmentGrade(100);
        testAssessment1.calculateAssessmentGrade();
        testAssessment2.calculateAssessmentGrade();
        testAssessment3.calculateAssessmentGrade();
        testCourse.addAssessment(testAssessment1);
        testCourse.addAssessment(testAssessment2);
        testCourse.addAssessment(testAssessment3);
        testCourse.calculateCourseGrade();
        assertEquals(94, testCourse.getCourseGrade());
    }

    @Test
    public void testCalculateCourseGradeOneEmptyAssessment() {
        testAssessment1 = new Assessment("Midterm",0.3);
        testAssessment2 = new Assessment("Final",0.6);
        testAssessment3 = new Assessment("Homework", 0.1);
        testAssessment1.addAssignmentGrade(90);
        testAssessment3.addAssignmentGrade(100);
        testAssessment1.calculateAssessmentGrade();
        testAssessment2.calculateAssessmentGrade();
        testAssessment3.calculateAssessmentGrade();
        testCourse.addAssessment(testAssessment1);
        testCourse.addAssessment(testAssessment2);
        testCourse.addAssessment(testAssessment3);
        testCourse.calculateCourseGrade();
        assertEquals(93, testCourse.getCourseGrade());
    }

    @Test
    public void testCalculateCourseGradeAllEmptyAssessment() {
        testAssessment1 = new Assessment("Midterm",0.3);
        testAssessment2 = new Assessment("Final",0.6);
        testAssessment3 = new Assessment("Homework", 0.1);
        testAssessment1.calculateAssessmentGrade();
        testAssessment2.calculateAssessmentGrade();
        testAssessment3.calculateAssessmentGrade();
        testCourse.addAssessment(testAssessment1);
        testCourse.addAssessment(testAssessment2);
        testCourse.addAssessment(testAssessment3);
        testCourse.calculateCourseGrade();
        assertEquals(0, testCourse.getCourseGrade());
    }

    @Test
    public void testCalculateCourseGradeOneAssessmentGradeIsZero() {
        testAssessment1 = new Assessment("Midterm",0.3);
        testAssessment2 = new Assessment("Final",0.6);
        testAssessment3 = new Assessment("Homework", 0.1);
        testAssessment1.addAssignmentGrade(90);
        testAssessment2.addAssignmentGrade(95);
        testAssessment3.addAssignmentGrade(0);
        testAssessment1.calculateAssessmentGrade();
        testAssessment2.calculateAssessmentGrade();
        testAssessment3.calculateAssessmentGrade();
        testCourse.addAssessment(testAssessment1);
        testCourse.addAssessment(testAssessment2);
        testCourse.addAssessment(testAssessment3);
        testCourse.calculateCourseGrade();
        assertEquals(84, testCourse.getCourseGrade());
    }




}
