package persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads user from JSON data stored in file
public class JsonReader {
    private String source;

    // Method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Data loaded"));
        return parseUser(jsonObject);
    }

    // Method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String username = jsonObject.getString("Username");
        User u = new User(username);
        addCourses(u, jsonObject);

        return u;
    }

    // MODIFIES: u
    // EFFECTS: parses courses from JSON object and adds them to workroom
    private void addCourses(User u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(u, nextCourse);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses course from JSON object and adds it to the user
    private void addCourse(User user, JSONObject jsonObject) {
        String courseName = jsonObject.getString("Course Name");
        double courseGrade = jsonObject.getDouble("Course Grade");
        Course c = new Course(courseName);
        c.setCourseGrade(courseGrade);
        user.addCourse(c);
        addAssessments(c, jsonObject);
    }

    // MODIFIES: c
    // EFFECTS: parses assessments from JSON object
    private void addAssessments(Course c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Course Assessments");
        for (Object json : jsonArray) {
            JSONObject nextAssessment = (JSONObject) json;
            addAssessment(c, nextAssessment);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses an assessment from JSON object and adds it to the course
    private void addAssessment(Course c, JSONObject jsonObject) {
        String assessmentName = jsonObject.getString("Assessment Name");
        double weight = jsonObject.getDouble("Assessment Weight");
        double assessmentGrade = jsonObject.getDouble("Assessment Grade");
        JSONArray jsonArray = jsonObject.getJSONArray("Mark Breakdown");
        Assessment a = new Assessment(assessmentName, weight);
        a.setAssessmentGrade(assessmentGrade);
        c.addAssessment(a);
        for (Object json : jsonArray) {
            double nextAssignmentScore = Double.parseDouble(json.toString());
            a.addAssignmentGrade(nextAssignmentScore);
        }
    }

}




