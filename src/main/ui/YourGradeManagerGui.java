package ui;

import model.Assessment;
import model.Course;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

// a grade calculator application
public class YourGradeManagerGui extends JFrame {

    private static final String JSON_STORE = "./data/user.json";
    private static final String IMAGE_ICON = "./data/calculator.jpg";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private User user;
    private JFrame menuFrame;
    private JPanel panel;
    private JPanel menuPanel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton courseButton;
    private JButton gradesButton;
    private JButton saveButton;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // Constructs the main window
    // EFFECTS: set up window in which Your Grade Manager will be displayed
    public YourGradeManagerGui() {
        // create and set up frame window
        super("Your Grade Manager: Main Window");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        // set the panel to add buttons
        panel = new JPanel();
        add(panel);
        // set up buttons with functionalities and add buttons to the display windows
        setUpButtons();
        addButtons();
        //set up image icon and add it to the display window
        addImageIcon();
    }

    // MODIFIES: this
    // EFFECTS: set up the two buttons: 'Sign Up' and 'Log In'
    // and add functions associated with each button
    public void setUpButtons() {
        signupButton = new JButton("Sign Up");
        signUp();
        loginButton = new JButton("Log In");
        logIn();
    }


    // EFFECTS: add the two buttons 'Sign Up' and 'Log In' to the display window
    public void addButtons() {
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        panel.add(signupButton);
        panel.add(loginButton);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: set up image icon and add it to the display window
    public void addImageIcon() {
        ImageIcon imageIcon = new ImageIcon(IMAGE_ICON);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setIcon(imageIcon);
        panel.add(imageLabel);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: When the Signup button is clicked. Show an input dialog to enter the username
    // create a user with the given username, show a welcome message dialog and open the menu window
    public void signUp() {
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = JOptionPane.showInputDialog("Enter your username to create your account");
                user = new User(userName);
                JOptionPane.showMessageDialog(YourGradeManagerGui.this,
                        "Welcome " + userName + "!");
                setVisible(false);
                setUpMenuFrame();
            }
        });
    }

    // EFFECTS: When the Login button is clicked. Show a reloading message dialog and a welcome message dialog
    public void logIn() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(YourGradeManagerGui.this,
                        "Reloading the existing user");
                loadUser();
                JOptionPane.showMessageDialog(YourGradeManagerGui.this,
                        "Welcome " + user.getUserName() + "!");
                setVisible(false);
                setUpMenuFrame();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: set up window in which the functionalities of Your Grade Manager will be displayed
    public void setUpMenuFrame() {
        // create and set up frame window
        menuFrame = new JFrame("Your Grade Manager: Menu Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
        menuFrame.setSize(WIDTH, HEIGHT);
        // set the panel to add buttons
        menuPanel = new JPanel();
        menuFrame.add(menuPanel);
        // set up buttons with functionalities and add buttons to the display windows
        setUpMenuButtons();
        addMenuButtons();
    }

    // MODIFIES: this
    // EFFECTS: set up the three buttons: 'Create a course', 'Add grades to an existing course', and 'Save'
    // and add functions associated with each button.
    public void setUpMenuButtons() {
        courseButton = new JButton("Create a course");
        createCourse();
        gradesButton = new JButton("Add grades to an existing course");
        addGrades();
        saveButton = new JButton("Save");
        saveUser();
    }

    // MODIFIES: this
    // EFFECTS: add the three buttons 'Create a course', 'Add grades to an existing course', and 'Save'
    // to the display windows
    public void addMenuButtons() {
        BoxLayout boxLayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
        menuPanel.setLayout(boxLayout);
        menuPanel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        menuPanel.add(courseButton);
        menuPanel.add(gradesButton);
        menuPanel.add(saveButton);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: create a course with its course name and assessment weight set up
    // Then add the course to the user's course list
    public void createCourse() {
        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = JOptionPane.showInputDialog("Please enter the course name");
                Course course = new Course(courseName);
                user.addCourse(course);
                String numInput = JOptionPane.showInputDialog("How many assessment type are there?");
                int num = Integer.parseInt(numInput);
                for (int i = 0; i <= num - 1; i++) {
                    String assessmentType = JOptionPane.showInputDialog("Please enter the assessment type");
                    double weight = Double.parseDouble(
                            JOptionPane.showInputDialog("Please enter the assessment weight between 0 and 1"));
                    Assessment assessment = new Assessment(assessmentType, weight);
                    course.addAssessment(assessment);
                }
                JOptionPane.showMessageDialog(YourGradeManagerGui.this,
                        course.getCourseName() + " is set up!");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: add grades for an assessment category of a course.
    public void addGrades() {
        gradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "There are the courses in your account: " + printCourse());
                String courseName = JOptionPane.showInputDialog("Which course do you want to add grade for?");
                Course course = searchCourse(courseName);
                addGradesToAssessment(course);
                saveUser();
            }
        });
    }

    //MODIFIES: this, course
    //EFFECTS: add grade to an assessment
    public void addGradesToAssessment(Course course) {
        JOptionPane.showMessageDialog(null,
                "There are the assessments for " + course.getCourseName() + ": " + printAssessment(course));
        String assessmentName = JOptionPane.showInputDialog("Which assessment do you want to add grade for?");
        Assessment assessment = searchAssessment(assessmentName, course);
        double grade = Double.parseDouble(
                JOptionPane.showInputDialog("Please enter your assignment grade in 100 point scale"));
        assessment.addAssignmentGrade(grade);
        assessment.calculateAssessmentGrade();
        course.calculateCourseGrade();
        JOptionPane.showMessageDialog(
                this, "Your grades are added to the assessment: " + assessment.getName());
        JOptionPane.showMessageDialog(
                this, "Your course grade is " + course.getCourseGrade() + "%");
    }

    //EFFECTS: print all courses in the user's course list
    public String printCourse() {
        int size = user.getCourses().size();
        String allCourses = "";
        for (int i = 0; i <= size - 1; i++) {
            String course = user.getCourses().get(i).getCourseName();
            allCourses = allCourses + " " + course;
        }
        return allCourses;
    }

    //EFFECTS: print all assessment in the course's assessment list
    public String printAssessment(Course course) {
        int size = course.getCourseAssessments().size();
        String allAssessments = "";
        for (int i = 0; i <= size - 1; i++) {
            String assessment = course.getCourseAssessments().get(i).getName();
            allAssessments = allAssessments + " " +  assessment;
        }
        return allAssessments;
    }

    //EFFECTS: find the course given a course name
    public Course searchCourse(String courseName) {
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

    // MODIFIES: this
    // Method was referred from WorkRoomApp in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
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

    // Method was referred from WorkRoomApp in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the user to file
    private void saveUser() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(user);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null,
                            "Saved " + user.getUserName() + " to " + JSON_STORE);
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }
}
