package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

import static java.lang.Math.round;

//Represents a course having a course name, a collections of assessment and the current course grade
public class Course implements Writable {

    private String courseName;                        //the name of the course
    private ArrayList<Assessment> courseAssessments;  //the collection of assessments
    private double courseGrade;                       //the current grade of a course

    //EFFECTS: construct a course with its course name, a list of course assessments and course grade
    //The course assessment list is initialized as an empty list and the course grade is initialized as 0
    public Course(String courseName) {
        this.courseName = courseName;
        courseAssessments = new ArrayList<>();
        this.courseGrade = 0;
    }

    // MODIFIES: this
    // EFFECTS: add an assessment into the course assessments list.
    public void addAssessment(Assessment assessment) {
        courseAssessments.add(assessment);
        EventLog.getInstance().logEvent(new Event("assessment added: " + assessment.getName()));
    }

    //MODIFIES: this
    //EFFECTS: calculate the current course grade and update the course grade. The course grade will be rounded
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


    //setters:
    //MODIFIES: this
    //EFFECTS: set the course grade to the courseGrade passed in
    public void setCourseGrade(double courseGrade) {
        this.courseGrade = courseGrade;
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

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Course Name", courseName);
        json.put("Course Assessments", assessmentToJson());
        json.put("Course Grade", courseGrade);
        return json;
    }

    // EFFECTS: returns assessments in this course as a JSON array
    private JSONArray assessmentToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Assessment a : courseAssessments) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }
}
