import java.util.*;

/**
 * The Student class represents a student with an ID, name, and marks in three subjects.
 * It provides functionality to calculate average marks and a formatted string output.
 */
class Student {
    String name;
    int id;
    int marks1, marks2, marks3;
    double average;

    /**
     * Constructor to initialize a student's details.
     *
     * @param name   the name of the student
     * @param id     the unique student ID
     * @param marks1 marks in subject 1
     * @param marks2 marks in subject 2
     * @param marks3 marks in subject 3
     */
    Student(String name, int id, int marks1, int marks2, int marks3) {
        this.name = name;
        this.id = id;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.average = calculateAverage();
    }

    /**
     * Calculates the average marks for the student.
     * Includes a defensive check to prevent division by zero.
     *
     * @return the calculated average marks
     */
    double calculateAverage() {
        int subjects = 3;
        if (subjects == 0) return 0.0;
        return (marks1 + marks2 + marks3) / (double) subjects;
    }

    /**
     * Returns a formatted string containing student details.
     *
     * @return formatted string representation of student details
     */
    @Override
    public String toString() {
        return String.format("Name: %-10s | ID: %-5d | Avg: %.2f", name, id, average);
    }
}

/**
 * The StudentApp class manages a list of students and provides operations such as:
 * - Adding students
 * - Sorting by average marks
 * - Searching by ID
 * - Finding the topper
 * - Printing grades
 * 
 * The class demonstrates clean coding practices, validation, and modular design.
 */
public class StudentApp {

    /**
     * Finds and returns the topper (student with the highest average).
     *
     * @param students the list of students
     * @return the topper student, or null if list is empty
     */
    static Student findTopper(ArrayList<Student> students) {
        if (students.isEmpty()) return null;
        Student topper = students.get(0);
        for (Student student : students) {
            if (student.average > topper.average) {
                topper = student;
            }
        }
        return topper;
    }

    /**
     * Sorts the list of students by average marks in descending order.
     *
     * @param students the list of students to be sorted
     */
    static void sortByAverage(ArrayList<Student> students) {
        students.sort((a, b) -> Double.compare(b.average, a.average));
    }

    /**
     * Searches for a student by ID in the list.
     *
     * @param students the list of students
     * @param searchId the ID to search for
     * @return the Student object if found, otherwise null
     */
    static Student searchById(ArrayList<Student> students, int searchId) {
        for (Student student : students) {
            if (student.id == searchId) {
                return student;
            }
        }
        return null;
    }

    /**
     * Prints the grades for all students based on their average.
     *
     * @param students the list of students
     */
    static void printGrades(ArrayList<Student> students) {
        for (Student student : students) {
            String grade;
            if (student.average >= 80) grade = "A+";
            else if (student.average >= 70) grade = "A";
            else if (student.average >= 60) grade = "B";
            else if (student.average >= 50) grade = "C";
            else grade = "F";

            System.out.printf("%-10s Grade: %s%n", student.name, grade);
        }
    }

    /**
     * Reads a positive integer safely from the user.
     * Re-prompts if the input is invalid or non-positive.
     *
     * @param input  the Scanner object for input
     * @param prompt the message to display to the user
     * @return a valid positive integer
     */
    static int readPositiveInt(Scanner input, String prompt) {
        int num;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                num = input.nextInt();
                if (num > 0) break;
                else System.out.println("Please enter a positive number.");
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                input.next(); // discard invalid input
            }
        }
        return num;
    }

    /**
     * Reads valid marks (0–100) from the user safely.
     *
     * @param input  the Scanner object for input
     * @param prompt the message to display to the user
     * @return a valid marks integer between 0 and 100
     */
    static int readMarks(Scanner input, String prompt) {
        int marks;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                marks = input.nextInt();
                if (marks >= 0 && marks <= 100) break;
                else System.out.println("Marks must be between 0 and 100.");
            } else {
                System.out.println("Invalid input. Please enter an integer between 0 and 100.");
                input.next(); // discard invalid input
            }
        }
        return marks;
    }

    /**
     * Main method that drives the application.
     * Handles input/output, displays menus, and performs operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        HashSet<Integer> usedIds = new HashSet<>(); // to ensure unique IDs

        System.out.println("===== Student Management Application =====");

        int totalStudents = readPositiveInt(input, "Enter number of students: ");

        // Input student details
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1));

            System.out.print("Name: ");
            String name = input.next();

            // Ensure unique ID
            int id;
            while (true) {
                id = readPositiveInt(input, "ID: ");
                if (!usedIds.contains(id)) {
                    usedIds.add(id);
                    break;
                } else {
                    System.out.println("This ID is already used. Please enter a unique ID.");
                }
            }

            System.out.println("Enter marks for 3 subjects:");
            int marks1 = readMarks(input, "Subject 1: ");
            int marks2 = readMarks(input, "Subject 2: ");
            int marks3 = readMarks(input, "Subject 3: ");

            students.add(new Student(name, id, marks1, marks2, marks3));
        }

        // Display all students using toString()
        System.out.println("\n===== All Students =====");
        for (Student student : students) {
            System.out.println(student);
        }

        // Find and display topper
        Student topper = findTopper(students);
        if (topper != null) {
            System.out.printf("\nTopper: %s (Avg: %.2f)%n", topper.name, topper.average);
        }

        // Sort students by average
        System.out.print("\nSort by Average? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            sortByAverage(students);
            System.out.println("\n===== Sorted List (by Average Descending) =====");
            for (Student student : students) {
                System.out.println(student);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by ID? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            int searchId = readPositiveInt(input, "Enter ID to search: ");
            Student found = searchById(students, searchId);
            if (found != null)
                System.out.printf("Found: %s%n", found);
            else
                System.out.println("Student with ID " + searchId + " not found.");
        }

        // Print grades
        System.out.print("\nCalculate Grades? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            System.out.println("\n===== Grades =====");
            printGrades(students);
        }

        System.out.println("\n===== Program Ended Successfully =====");
        input.close();
    }
}
