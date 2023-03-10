# My Personal Project: Your Grade Manager

## Introduction:

### What will the application do?
**"Your Grade Manager"**, as its name suggested, 
is an application dedicated to calculating your
grades. It allows you to:
- Create your own course and add assessments with their weights to the course.
- Record the assessment grades of a course.
- Calculate the final grade of a course based on the assessment grades and
weights.

### Who will use it?
The target users of "Your Grade Manager" are **HIGH SCHOOL 
STUDENTS** and **UNDERGRADUATE STUDENTS**. For ambitious and 
academic-able high school students, GPA is a vital
component of university application and undoubtedly affects
the possibility of being admitted to their dream school.
Therefore, "Your Grade Manager" allows them to keep track of their
grades and be clear about their current state. It is the same idea
for undergraduate students who might need to apply for
the co-op or post-graduate program.

### Why is this project of interest to you?
Optimization is an important part of my life and using optimization to maximize my GPA is a tedious calculation I have 
to do almost every single time when there is an assignment grade released.
The current overall mark of one course tells me whether I need to spend more time on that
course. It allows me to better allocate my time. However, It is tedious to type grades and their
associated weights every single time you want to know your current grade for a course. Therefore,
my project idea comes up. What if there is an application that allows me to create my own courses
with every assessment categories and their associated weights established. Then when there 
is a grade published, I can add the grade to the assessment and the application will automatically
tells me my current mark. This application will definitely save me from my tedious and
time-consuming daily basis.

## User Stories:
- As a user, I want to be able to create a user with the username I provide.
- As a user, I want to be able to add courses to a user.
- As a user, I want to be able to create a course with a course name and a collection of assessments in a course.
- As a user, I want to be able to add assignments grades inside one assessment category.
- As a user, I want to be able to calculate my course grades automatically every time I add a new grade.
- As a user, I want to be able to save my grade to file after I add a grade if I select save option.
- As a user, I want to be able to load the user I created last time if I select load option.
- This one is newly added: As a user, I want to be able to see all courses and their associated grades.

## Instruction for Grading:
- You can generate the event of creating and adding a course to a user by clicking the 'Create a Course' button in the menu window.
- You can generate the event of adding a grade to an assessment inside the course by clicking the 'Add grades to an existing course' button in the menu window.
- You can generate the event of displaying all courses and their associated grades in the user's course list when you click the 'Display Courses' button in the menu window.
- You can locate my visual component by running my application and checking the main window.
- You can save the state of my application by clicking the 'Save' button located in the main window.
- You can reload the state of my application by clicking the 'Log In' button located in the main window.


## Phase 4: Task 2:
Mon Nov 28 17:17:30 PST 2022
Data loaded


Mon Nov 28 17:17:30 PST 2022
Course added: math


Mon Nov 28 17:17:30 PST 2022
assessment added: hw


Mon Nov 28 17:17:42 PST 2022
assignment grade 100.0 added to: hw


Mon Nov 28 17:17:47 PST 2022
Data saved

## Phase 4: Task 3:
1. The hierarchical of my design can be improved by constructing a new interface called **GradableComponent** implemented 
by both the Course class and Assessment class.
Notice that the 'addAssignmentGrade' method in the Assessment class and the 'addAssessment' method in the Course class have
very similar behaviour in terms of adding Xs to Y's field (ListofX).
Similarly, the 'calculateAssessmentGrade' method in the Assessment class and the 
'calculateCourseGrade' in the Course class share commonalities in terms of calculating average, be it assignment grade 
or assessment grade both represented as the primitive type double.
With an interface GradebleComponent, I can specify the generic methods called 'addGradableComponent' and 
'calculatingGradableComponent', with the implementations provided in the Course and Assessment class respectively
to reduce code duplication.
2. My design can be more sustainable by establishing an bidirectional relationship between Course and Assessment.
Notice the events printed to the console that when the user create a new course and add an assessment to a course, the
description is in the form of "assessment added: (assessmentName)". However, it does not show which course the assessment
is being added to. This is because the assessment object doesn't have a course field. Once the bidirectional relationship
is established between Course and Assessment, the user can get feedback on which assessment is added to which course.
3. The cohesion of YourGradeManagerGui Class can be improved by moving some methods to the 
Assessment, Course and User class in the model package. For example, the 'printCourse', 'printCourseAndGrades' and 'searchCourse' method
in the YourGradeManagerGui class should be rearranged to the User class because these three methods all access the 
User object's field and perform the operations similar to 'ToString' on it. I will also rename the 'printCourse' 
and 'printCourseAndGrades' to 'coursesToString' and 'CoursesAndGradesToString' to better inform another developer 
who read my code about what these methods are handling.
Besides, the 'printAssessment' and 'searchAssessment' in the YourGradeManagerGui class should be rearranged to the 
Course class because these two methods both access the Course object's field and perform operations similar to 
'ToString' on it. I will also rename the 'printAssessment' method to 'AssessmentsToString' to better inform another 
developer who read my code about what these methods are handling.
Furthermore, my application currently has three methods that are responsible for the ToString behaviour and two methods 
that are responsible for the searching behaviour. In the future, if my application needs more methods to perform the 
ToString and seraching behaviour, I will create two classes called ToStringManager and SearchingManager and then 
refactor all of methods that centered these two concepts.
4. By the single responsibility principle, each class should be centered around one cohesive concept. Following this
principle, I can refactor my YourGradeManagerGui class into two classes: YourGradeManagerMainWindow
and YourGradeManagerMenuWindow. The YourGradeManagerMainWindow class would be centered around all data and behaviour associated with
the main window of my application. While the YourGradeManagerMenuWindow class would be centered around all data and behaviour
associated with the menu window of my application.




