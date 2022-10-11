package model;

import java.util.ArrayList;

import static java.lang.Math.round;

//Represents a course having a course name, a collections of assessment and the current course grade.
public class Course {

    private String courseName;                        //the name of the course
    private ArrayList<Assessment> courseAssessments;  //the collection of assessments
    private double courseGrade;                       //the current grade of a course

    //EFFECTS: construct a course with its course name, a list of course assessments and course grade.
    //The course assessment list is initialized as an empty list and the course grade is initialized as 0.
    public Course(String courseName) {
        this.courseName = courseName;
        courseAssessments = new ArrayList<>();
        this.courseGrade = 0;
    }

    // MODIFIES: this
    // EFFECTS: add an assessment into the course assessments list.
    public void addAssessment(Assessment assessment) {
        courseAssessments.add(assessment);
    }

    //MODIFIES: this
    //EFFECTS: calculate the current course grade and update the course grade. The course grade will be rounded.
    public void calculateCourseGrade() {
        int size = courseAssessments.size();
        double sum = 0;
        double weight = 0;
        for (int i = 0; i <= size - 1; i++) {
            if (courseAssessments.get(i).ifNonEmptyAssignmentScores()) {
                sum = sum + courseAssessments.get(i).getAssessmentGrade();
                weight = weight + courseAssessments.get(i).getWeight();
            }
        }
        if (weight == 0) {
            courseGrade = 0;
        }
        courseGrade = round(sum / weight);
    }


    //getters:
    public String getCourseName() {
        return courseName;
    }

    public double getCourseGrade() {
        return courseGrade;
    }

    public ArrayList<Assessment> getCourseAssessments() {
        return courseAssessments;
    }
}