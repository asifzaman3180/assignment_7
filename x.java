import java.util.*;

/**
 * StudentApp.java
 * 
 * This program manages student records using an OOP approach.
 * It allows the user to:
 * 1. Enter student details (name, ID, marks for 3 subjects)
 * 2. Calculate average marks for each student
 * 3. Display all students and their averages
 * 4. Find the topper
 * 5. Sort students by average
 * 6. Search student by ID
 * 7. Calculate and display grades
 * 
 * The program uses:
 * - Student class to store student data
 * - StudentService class to process student operations
 * - Main class for user interaction
 * 
 * Input validation is included to ensure proper IDs and marks.
 * 
 * @author Your Name
 * @version 1.0
 */
public class StudentApp {

    // ---------------- Student class ----------------
    /**
     * Represents a student with name, ID, marks, and average.
     */
    static class Student {
        private String name;
        private int id;
        private int m1, m2, m3;
        private double average;

        /**
         * Constructor to create a Student object
         * @param name Student name
         * @param id Student ID (positive integer)
         * @param m1 Marks in subject 1 (0-100)
         * @param m2 Marks in subject 2 (0-100)
         * @param m3 Marks in subject 3 (0-100)
         */
        public Student(String name, int id, int m1, int m2, int m3) {
            this.name = name;
            this.id = id;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
            calculateAverage();
        }

        /** Calculates the average of the three subject marks */
        public void calculateAverage() {
            this.average = (m1 + m2 + m3) / 3.0;
        }

        // Getters
        public String getName() { return name; }
        public int getId() { return id; }
        public double getAverage() { return average; }

        /** Returns a formatted string representation of the student */
        @Override
        public String toString() {
            return String.format("Name: %s Id: %d Avg: %.2f", name, id, average);
        }
    }

    // ---------------- StudentService class ----------------
    /**
     * Provides operations on a list of Student objects, such as
     * finding the topper, sorting, searching, and printing grades.
     */
    static class StudentService {

        /**
         * Finds the student with the highest average
         * @param students List of students
         * @return Optional containing topper or empty if list is empty
         */
        public Optional<Student> findTopper(List<Student> students) {
            if (students == null || students.isEmpty()) return Optional.empty();
            Student topper = students.get(0);
            for (Student s : students) {
                if (s.getAverage() > topper.getAverage()) {
                    topper = s;
                }
            }
            return Optional.of(topper);
        }

        /**
         * Sorts students by average in descending order
         * @param students List of students
         */
        public void sortByAverage(List<Student> students) {
            if (students == null) return;
            students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
        }

        /**
         * Searches for a student by ID
         * @param students List of students
         * @param id ID to search
         * @return Optional containing the student or empty if not found
         */
        public Optional<Student> searchById(List<Student> students, int id) {
            if (students == null) return Optional.empty();
            for (Student s : students) {
                if (s.getId() == id) return Optional.of(s);
            }
            return Optional.empty();
        }

        /**
         * Prints grades for all students based on average marks
         * A+ >= 80, A >= 70, B >= 60, C >= 50, F < 50
         * @param students List of students
         */
        public void printGrades(List<Student> students) {
            if (students == null || students.isEmpty()) {
                System.out.println("No students to grade.");
                return;
            }

            for (Student s : students) {
                String grade;
                double avg = s.getAverage();
                if (avg >= 80) grade = "A+";
                else if (avg >= 70) grade = "A";
                else if (avg >= 60) grade = "B";
                else if (avg >= 50) grade = "C";
                else grade = "F";

                System.out.println(s.getName() + " Grade: " + grade);
            }
        }
    }

    // ---------------- MainApp code ----------------
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service = new StudentService();

    /**
     * Main method to run the Student Manager application
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Student Manager");

        // Read number of students
        int n = readInt("Enter number of students: ", 0, Integer.MAX_VALUE);
        if (n == 0) {
            System.out.println("No students to process. Exiting.");
            return;
        }

        // Input student details
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("Student #%d%n", i + 1);
            String name = readString("Enter name: ");
            int id = readInt("Enter id (positive integer): ", 1, Integer.MAX_VALUE);
            int m1 = readInt("Enter marks for subject 1 (0-100): ", 0, 100);
            int m2 = readInt("Enter marks for subject 2 (0-100): ", 0, 100);
            int m3 = readInt("Enter marks for subject 3 (0-100): ", 0, 100);
            students.add(new Student(name, id, m1, m2, m3));
        }

        // Display all students
        System.out.println("\nAll Students:");
        students.forEach(System.out::println);

        // Display topper
        service.findTopper(students).ifPresentOrElse(
                t -> System.out.printf("\nTopper: %s Avg: %.2f%n", t.getName(), t.getAverage()),
                () -> System.out.println("No topper found")
        );

        // Sort if requested
        if (yesNo("Sort by Average? (y/n): ")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List:");
            students.forEach(s -> System.out.printf("%s %.2f%n", s.getName(), s.getAverage()));
        }

        // Search by ID if requested
        if (yesNo("\nSearch student by id? (y/n): ")) {
            int searchId = readInt("Enter id to search: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
            service.searchById(students, searchId).ifPresentOrElse(
                    s -> System.out.printf("Found: %s Avg: %.2f%n", s.getName(), s.getAverage()),
                    () -> System.out.println("Not found")
            );
        }

        // Calculate grades if requested
        if (yesNo("\nCalculate grade? (y/n): ")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nBye!");
    }

    // ---------------- Input Helpers ----------------
    /**
     * Reads a non-empty string from user
     * @param prompt Prompt message
     * @return Non-empty string input
     */
    private static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    /**
     * Reads an integer from user with validation
     * @param prompt Prompt message
     * @param min Minimum allowed value
     * @param max Maximum allowed value
     * @return Validated integer input
     */
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.printf("Enter a number between %d and %d.%n", min, max);
                    continue;
