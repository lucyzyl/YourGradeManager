package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a user having a username and a collection of courses
public class User implements Writable {

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
        EventLog.getInstance().logEvent(new Event("Course added: " + course.getCourseName()));
    }

    //getters:
    public String getUserName() {
        return userName;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Username", userName);
        json.put("Courses", courseToJson());
        return json;
    }

    // EFFECTS: returns courses in this user as a JSON array
    private JSONArray courseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courses) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

}
