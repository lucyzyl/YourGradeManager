package model;

import java.util.ArrayList;

// Represents an assessment having an assessment name, an assessment weight (0 < weight <= 1)
// and grade of the assessment. The grade of the assessment is the weighted grade.
// An assessment with grade 100 and weight 0.2 have an assessment grade of 20)
public class Assessment {

    private ArrayList<Double> assignmentScores;         //the collection of assignment grades within an assessment
    private String assessmentName;                      //the name of an assessment
    private double weight;                              //the weight of an assessment
    private double assessmentGrade;                     //the weighted garde of an assessment

    //REQUIRES: 0 < weight <= 1
    //EFFECTS: construct an assessment with its name, weight, a list of assignment score and assessment weighted grade.
    // The assignmentScores list is initialized as an empty list and the assessment weighted grade is 0 initially.
    public Assessment(String assessmentName, double weight) {
        this.assessmentName = assessmentName;
        this.weight = weight;
        this.assignmentScores = new ArrayList<>();
        this.assessmentGrade = 0;
    }

    //REQUIRES: Grade is a percentage grade and grade >= 0
    //MODIFIES: this
    //EFFECTS: add an assignment grade to the assignmentScores list.
    public void addAssignmentGrade(double grade) {
        assignmentScores.add(grade);
    }

    //MODIFIES: this
    //EFFECTS: calculate the weighted average of the assessment categories and update the assessment grade.
    // Then return the weighted assessment grade.
    public double calculateAssessmentGrade() {
        int size = assignmentScores.size();
        double sum = 0;
        for (int i = 0; i <= size - 1; i++) {
            sum = sum + assignmentScores.get(i);
        }
        if (sum == 0) {
            return assessmentGrade;
        }
        assessmentGrade = sum / size * weight;
        return assessmentGrade;
    }

    //EFFECTS: return true if assignmentScores is not empty. Otherwise, return false.
    public boolean ifNonEmptyAssignmentScores() {
        if (assignmentScores.size() == 0) {
            return false;
        }
        return true;
    }

    //getters:
    public String getName() {
        return assessmentName;
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Double> getAssignmentScores() {
        return assignmentScores;
    }

    public double getAssessmentGrade() {
        return assessmentGrade;
    }

}
