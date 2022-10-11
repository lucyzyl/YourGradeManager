package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {

    Assessment testAssessment;

    @BeforeEach
    public void setUp() {
        testAssessment = new Assessment("Lab", 0.2);
    }

    @Test
    public void testConstructor() {
        assertEquals("Lab", testAssessment.getName());
        assertEquals(0.2, testAssessment.getWeight());
        assertEquals(0, testAssessment.getAssignmentScores().size());
    }

    @Test
    public void testAddAssignmentGradeOnce() {
        testAssessment.addAssignmentGrade(100);
        assertEquals(1, testAssessment.getAssignmentScores().size());
        double grade1 = testAssessment.getAssignmentScores().get(0);
        assertEquals(100, grade1);
    }

    @Test
    public void testAddAssignmentGradeMultiple() {
        testAssessment.addAssignmentGrade(100);
        testAssessment.addAssignmentGrade(80);
        assertEquals(2, testAssessment.getAssignmentScores().size());
        double grade1 = testAssessment.getAssignmentScores().get(0);
        assertEquals(100, grade1);
        double grade2 = testAssessment.getAssignmentScores().get(1);
        assertEquals(80, grade2);
    }

    @Test
    public void testAddAssignmentGradeZero() {
        testAssessment.addAssignmentGrade(0);
        assertEquals(1, testAssessment.getAssignmentScores().size());
        double grade1 = testAssessment.getAssignmentScores().get(0);
        assertEquals(0, grade1);
    }

    @Test
    public void testCalculateAssessmentGradeEmpty() {
        assertEquals(0, testAssessment.calculateAssessmentGrade());
    }

    @Test
    public void testCalculateAssessmentGradeOneGradeInList() {
        testAssessment.addAssignmentGrade(100);
        assertEquals(20, testAssessment.calculateAssessmentGrade());
    }

    @Test
    public void testCalculateAssessmentGradeTwoGradesInList() {
        testAssessment.addAssignmentGrade(90);
        testAssessment.addAssignmentGrade(100);
        testAssessment.calculateAssessmentGrade();
        assertEquals(19,testAssessment.getAssessmentGrade());
    }

    @Test
    public void testCalculateAssessmentGradeMultipleGradesInList() {
        testAssessment.addAssignmentGrade(90);
        testAssessment.addAssignmentGrade(100);
        testAssessment.addAssignmentGrade(88);
        testAssessment.addAssignmentGrade(77);
        assertEquals(17.75, testAssessment.calculateAssessmentGrade());
    }

    @Test
    public void testIfNonEmptyAssignmentScoresEmpty() {
        assertFalse(testAssessment.ifNonEmptyAssignmentScores());
    }

    @Test
    public void testIfNonEmptyAssignmentScoresScoreZeroIn() {
        testAssessment.addAssignmentGrade(0);
        assertTrue(testAssessment.ifNonEmptyAssignmentScores());
    }


    @Test
    public void testIfNonEmptyAssignmentScoresScoreNonEmpty() {
        testAssessment.addAssignmentGrade(100);
        assertTrue(testAssessment.ifNonEmptyAssignmentScores());
    }

}