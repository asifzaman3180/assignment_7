import java.util.*;

/**
 * Main application for managing student records using OOP principles.
 * <p>
 * This program allows you to:
 * <ul>
 *   <li>Input student details (name, ID, marks)</li>
 *   <li>Calculate average marks</li>
 *   <li>Find the topper</li>
 *   <li>Sort students by average</li>
 *   <li>Search for a student by ID</li>
 *   <li>Assign and display grades</li>
 * </ul>
 * 
 * Classes included in this file:
 * <ul>
 *   <li>{@link Student} - Represents a student entity</li>
 *   <li>{@link StudentService} - Handles student processing logic</li>
 *   <li>{@link X} - The main driver class containing the main() method</li>
 * </ul>
 * 
 * @author  
 * @version 1.0  
 */
public class X {

    /**
     * The entry point of the program.
     * Handles user interaction and delegates tasks to {@link StudentService}.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        // ----------------- INPUT STUDENTS -----------------
        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter marks for 3 subjects: ");
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            int m3 = scanner.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        // ----------------- PROCESS DATA -----------------
        service.calculateAverage(students);

        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println(s);
        }

        // ----------------- FIND TOPPER -----------------
        Student topper = service.findTopper(students);
        System.out.println("\nTopper: " + topper.getName() + " | Avg: " + topper.getAverage());

        // ----------------- SORT BY AVERAGE -----------------
        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted by Average (Descending):");
            for (Student s : students) {
                System.out.println(s);
            }
        }

        // ----------------- SEARCH BY ID -----------------
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found: " + found);
            else
                System.out.println("Student not found.");
        }

        // ----------------- PRINT GRADES -----------------
        System.out.print("\nPrint grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}

/**
 * Represents a single student and their academic details.
 * Contains fields for name, ID, subject marks, average, and grade.
 */
class Student {
    private String name;
    private int id;
    private int subject1;
    private int subject2;
    private int subject3;
    private double average;
    private String grade;

    /**
     * Constructs a new Student.
     *
     * @param name     student's name
     * @param id       student's unique ID
     * @param subject1 marks in subject 1
     * @param subject2 marks in subject 2
     * @param subject3 marks in subject 3
     */
    public Student(String name, int id, int subject1, int subject2, int subject3) {
        this.name = name;
        this.id = id;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    // ----------- Getters and Setters -----------
    public String getName() { return name; }
    public int getId() { return id; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }
    public void setAverage(double average) { this.average = average; }
    public void setGrade(String grade) { this.grade = grade; }

    /**
     * Calculates and stores the student's average marks.
     */
    public void calculateAverage() {
        this.average = (subject1 + subject2 + subject3) / 3.0;
    }

    /**
     * Returns a formatted string representing the student.
     *
     * @return a formatted string with name, ID, average, and grade
     */
    @Override
    public String toString() {
        return String.format("Name: %-10s | ID: %-5d | Avg: %.2f | Grade: %s",
                name, id, average, (grade == null ? "-" : grade));
    }
}

/**
 * Provides operations for managing a list of students.
 * Includes methods for calculating averages, sorting, finding topper, searching, and grading.
 */
class StudentService {

    /**
     * Calculates averages for all students in the list.
     *
     * @param students list of students
     */
    public void calculateAverage(List<Student> students) {
        for (Student s : students) {
            s.calculateAverage();
        }
    }

    /**
     * Finds the student with the highest average.
     *
     * @param students list of students
     * @return the topper student
     */
    public Student findTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    /**
     * Sorts students in descending order by their average marks.
     *
     * @param students list of students
     */
    public void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    /**
     * Searches for a student by their ID.
     *
     * @param students list of students
     * @param id       the ID to search for
     * @return the found student or null if not found
     */
    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    /**
     * Assigns and prints grades for each student based on their average.
     * <p>
     * Grading scale:
     * <ul>
     *   <li>80–100 → A+</li>
     *   <li>70–79 → A</li>
     *   <li>60–69 → B</li>
     *   <li>50–59 → C</li>
     *   <li>Below 50 → F</li>
     * </ul>
     *
     * @param students list of students
     */
    public void printGrades(List<Student> students) {
        System.out.println("\nGrades:");
        for (Student s : students) {
            double avg = s.getAverage();
            String grade;
            if (avg >= 80) grade = "A+";
            else if (avg >= 70) grade = "A";
            else if (avg >= 60) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "F";

            s.setGrade(grade);
            System.out.println(s.getName() + " | Grade: " + grade);
        }
    }
}
