import java.util.*;

/**
 * Represents a student with their name, ID, marks, average, and grade.
 * Provides methods to calculate averages and assign grades.
 */
class Student {
    private String name;
    private int id;
    private int[] marks;
    private double average;
    private String grade;

    /**
     * Constructs a new Student.
     *
     * @param name  The student's name.
     * @param id    The student's unique ID.
     * @param marks An array of marks for the student's subjects.
     */
    public Student(String name, int id, int[] marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
        calculateAverage();
    }

    /**
     * Calculates the student's average mark.
     * Handles cases where marks are missing to avoid division by zero.
     */
    public void calculateAverage() {
        if (marks == null || marks.length == 0) {
            average = 0;
            return;
        }
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        average = total / (double) marks.length;
    }

    /**
     * Assigns a grade to the student based on their average mark.
     */
    public void assignGrade() {
        if (average >= 80) grade = "A+";
        else if (average >= 70) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 50) grade = "C";
        else grade = "F";
    }

    /** @return The student's name. */
    public String getName() {
        return name;
    }

    /** @return The student's ID. */
    public int getId() {
        return id;
    }

    /** @return The student's average mark. */
    public double getAverage() {
        return average;
    }

    /** @return The student's grade (may be null until assigned). */
    public String getGrade() {
        return grade;
    }

    /**
     * Returns a readable string representation of the student.
     *
     * @return Formatted student information.
     */
    @Override
    public String toString() {
        return String.format("Name: %-10s | ID: %-5d | Average: %.2f", name, id, average);
    }
}

/**
 * Handles business logic related to student operations such as
 * finding the topper, sorting, searching, and grade processing.
 */
class StudentService {

    /**
     * Finds the student with the highest average.
     *
     * @param students List of students.
     * @return The topper student, or null if the list is empty.
     */
    public Student findTopper(List<Student> students) {
        if (students.isEmpty()) return null;
        return Collections.max(students, Comparator.comparingDouble(Student::getAverage));
    }

    /**
     * Sorts students in descending order of average marks.
     *
     * @param students List of students to be sorted.
     */
    public void sortByAverage(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
    }

    /**
     * Searches for a student by ID.
     *
     * @param students List of students.
     * @param id       ID of the student to search for.
     * @return The matching student or null if not found.
     */
    public Student searchById(List<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    /**
     * Assigns and prints grades for all students.
     *
     * @param students List of students.
     */
    public void printGrades(List<Student> students) {
        for (Student student : students) {
            student.assignGrade();
            System.out.println(student.getName() + " | Grade: " + student.getGrade());
        }
    }
}

/**
 * The main entry point for the Student Management System.
 * Handles user interaction, input, and program control flow.
 */
public class x {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = safeReadInt(scanner);

        // Input for each student
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = scanner.next();

            System.out.print("ID: ");
            int id = safeReadInt(scanner);

            int[] marks = new int[3];
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter mark for subject " + (j + 1) + ": ");
                marks[j] = safeReadInt(scanner);
            }

            students.add(new Student(name, id, marks));
        }

        // Display all students
        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println(s);
        }

        // Display topper
        Student topper = service.findTopper(students);
        if (topper != null) {
            System.out.println("\nTopper: " + topper.getName() + " | Average: " + topper.getAverage());
        }

        // Sort students
        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted Students (by Average):");
            for (Student s : students) System.out.println(s);
        }

        // Search by ID
        System.out.print("\nSearch by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID to search: ");
            int searchId = safeReadInt(scanner);
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found: " + found);
            else
                System.out.println("No student found with ID " + searchId);
        }

        // Print grades
        System.out.print("\nPrint Grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nProgram finished successfully!");
    }

    /**
     * Reads an integer safely, handling invalid input using try-catch.
     *
     * @param scanner The Scanner object for input.
     * @return A valid integer entered by the user.
     */
    private static int safeReadInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // clear invalid token
            }
        }
    }
}
