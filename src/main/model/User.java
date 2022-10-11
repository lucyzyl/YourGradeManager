package model;

import java.util.ArrayList;

//Represents a user having a username and a collection of courses
public class User {

    private String userName;              //the name of the user
    private ArrayList<Course> courses;    //the collection of courses

    //EFFECTS: construct a user with a userName. The student's course list is empty initially
    public User(String userName) {
        this.userName = userName;
        courses = new ArrayList<Course>();
    }

    //MODIFIES: this
    //EFFECTS: add a course to a user's course list
    public void addCourse(Course course) {
        courses.add(course);
    }


    //getters:
    public String getUserName() {
        return userName;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }

}
