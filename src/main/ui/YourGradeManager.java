package ui;

import model.Assessment;
import model.Course;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// a grade calculator application
public class YourGradeManager {
    private static final String JSON_STORE = "./data/user.json";
    private User user;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the grade calculator application
    public YourGradeManager() {
        runYourGradeCalculator();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    public void runYourGradeCalculator() {
        boolean runApplication = true;
        String command;

        signUp();

        while (runApplication) {
            displayOptions();
            input = new Scanner(System.in);
            command = input.nextLine();

            if (command.equals("c")) {
                createCourse(user);
            } else if (command.equals("a")) {
                addGrades(user);
            } else if (command.equals("q")) {
                runApplication = false;
                System.out.println("\nGoodBye");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: create an account for the user with its username and return the user just created
    public void signUp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        System.out.println("\nSelect from");
        System.out.println("----------------------------------------------------");
        System.out.println("s -> Sign up");
        System.out.println("\nl -> Log in");
        String choice = input.nextLine();

        if (choice.equals("s")) {
            System.out.println("Enter your username to create your account");
            String userName = input.nextLine();
            user = new User(userName);
            System.out.println("Welcome " + userName);
        } else {
            System.out.println("Reloading the existing user");
            loadUser();
        }
    }

    //EFFECTS: displays menu of options to user
    public void displayOptions() {
        System.out.println("\nSelect from");
        System.out.println("----------------------------------------------------");
        System.out.println("c -> Create a course");
        System.out.println("\na -> Add grades to an existing course");
        System.out.println("\nq -> Quit");
        System.out.println("----------------------------------------------------");
    }

    //MODIFIES: this
    //EFFECTS: create a course with its course name and assessment weight set up
    //Then add the course to the user's course list
    public void createCourse(User user) {
        System.out.println("Please enter the course name");
        input = new Scanner(System.in);
        String courseName = input.nextLine();
        Course course = new Course(courseName);
        System.out.println("How many assessment type are there?");
        int num = input.nextInt();

        for (int i = 0; i <= num - 1; i++) {
            System.out.println("Please enter the assessment type");
            input = new Scanner(System.in);
            String assessmentType = input.next();
            System.out.println("Please enter the assessment weight between 0 and 1");
            double weight = input.nextDouble();
            Assessment assessment = new Assessment(assessmentType, weight);
            course.addAssessment(assessment);
        }
        user.addCourse(course);
        System.out.println(course.getCourseName() + " is set up");
    }

    //MODIFIES: this
    //EFFECTS: add grades for an assessment category of a given course.
    public void addGrades(User user) {
        System.out.println("Which course do you want to add grade for\n");
        printCourse(user);
        input = new Scanner(System.in);
        String courseName = input.nextLine();
        if (!courseName.equals("q")) {
            Course course = searchCourse(courseName, user);
            addGradesToAssessment(course);
            System.out.println("Your " + course.getCourseName() + " is " + course.getCourseGrade());
        } else if (courseName.equals("q")) {
            System.exit(0);
        }
    }


    //MODIFIES: this, course
    //EFFECTS: add grade to a given assessment
    public void addGradesToAssessment(Course course) {
        boolean contiune = true;
        while (contiune) {
            System.out.println("which assessment do you want to add grade for\n");
            printAssessment(course);
            input = new Scanner(System.in);
            String assessmentName = input.nextLine();
            if (!assessmentName.equals("q") && !assessmentName.equals("s")) {
                Assessment assessment = searchAssessment(assessmentName, course);
                System.out.println("Please enter your assignment grade in 100 point scale");
                double grade = input.nextDouble();
                assessment.addAssignmentGrade(grade);
                assessment.calculateAssessmentGrade();
                course.calculateCourseGrade();
                System.out.println("Your grades are added to the assessment: " + assessment.getName() + "\n");
                System.out.println("----------------------------------------------------");
                System.out.println("Your course grade is " + course.getCourseGrade() + "%");
                System.out.println("----------------------------------------------------\n");
            } else if (assessmentName.equals("q")) {
                System.exit(0);
            } else if (assessmentName.equals("s")) {
                saveUser();
            }
        }
    }

    //EFFECTS: print all courses in the user's course list and a line that allows user to type q to quit.
    public void printCourse(User user) {
        System.out.println("Select from");
        System.out.println("----------------------------------------------------");
        int size = user.getCourses().size();
        for (int i = 0; i <= size - 1; i++) {
            System.out.println(user.getCourses().get(i).getCourseName());
        }
        System.out.println("----------------------------------------------------");
        System.out.println("\nq -> Quit");
    }


    //EFFECTS: print all assessment in the course's assessment list and a line that allows user to type q to quit.
    public void printAssessment(Course course) {
        System.out.println("Select from");
        System.out.println("----------------------------------------------------");
        int size = course.getCourseAssessments().size();
        for (int i = 0; i <= size - 1; i++) {
            System.out.println(course.getCourseAssessments().get(i).getName());
        }
        System.out.println("----------------------------------------------------");
        System.out.println("\nq -> Quit");
        System.out.println("\ns -> save user to file");
    }


    //EFFECTS: Given a course name, find the course in a user's course list.
    public Course searchCourse(String courseName, User user) {
        int size = user.getCourses().size();
        for (int i = 0; i <= size - 1; i++) {
            if (user.getCourses().get(i).getCourseName().equals(courseName)) {
                return user.getCourses().get(i);
            }
        }
        throw new NoSuchElementException("The course doesn't exist");
    }


    //EFFECTS: Given an assessment name, find the assessment in a course list.
    public Assessment searchAssessment(String assessmentName, Course course) {
        int size = course.getCourseAssessments().size();
        for (int i = 0; i <= size - 1; i++) {
            if (course.getCourseAssessments().get(i).getName().equals(assessmentName)) {
                return course.getCourseAssessments().get(i);
            }
        }
        throw new NoSuchElementException("The assessment doesn't exist");
    }

    // EFFECTS: saves the user to file
    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved " + user.getUserName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads user from file
    private void loadUser() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getUserName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
























