import java.util.*;

/**
 * Represents a student with basic academic information.
 */
class Student {
    private String name;
    private int id;
    private int[] marks;
    private double average;
    private String grade;

    /**
     * Constructs a Student object.
     *
     * @param name  student's name
     * @param id    student's ID
     * @param marks array of marks in 3 subjects
     */
    public Student(String name, int id, int[] marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
        calculateAverage();
    }

    /**
     * Calculates the average marks safely.
     * Handles cases with zero subjects to avoid division by zero.
     */
    public void calculateAverage() {
        if (marks == null || marks.length == 0) {
            average = 0;
            return;
        }
        int sum = 0;
        for (int mark : marks) sum += mark;
        average = sum / (double) marks.length;
    }

    /**
     * Assigns a grade based on average marks.
     */
    public void assignGrade() {
        if (average >= 80) grade = "A+";
        else if (average >= 70) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 50) grade = "C";
        else grade = "F";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getAverage() {
        return average;
    }

    public String getGrade() {
        return grade;
    }

    /**
     * Returns a formatted string representation of a student.
     */
    @Override
    public String toString() {
        return String.format("Name: %-10s | ID: %-5d | Average: %.2f", name, id, average);
    }
}

/**
 * Provides operations for managing and analyzing students.
 */
class StudentService {

    /**
     * Finds the topper (highest average) among the students.
     *
     * @param students list of students
     * @return topper student or null if list is empty
     */
    public Student findTopper(List<Student> students) {
        if (students.isEmpty()) return null;
        return Collections.max(students, Comparator.comparingDouble(Student::getAverage));
    }

    /**
     * Sorts students in descending order of average marks.
     *
     * @param students list of students
     */
    public void sortByAverage(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
    }

    /**
     * Searches for a student by their ID.
     *
     * @param students list of students
     * @param id       student ID to search
     * @return found Student object or null
     */
    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    /**
     * Calculates and assigns grades for all students.
     *
     * @param students list of students
     */
    public void printGrades(List<Student> students) {
        for (Student s : students) {
            s.assignGrade();
            System.out.println(s.getName() + " | Grade: " + s.getGrade());
        }
    }
}

/**
 * Main application class that handles user interaction.
 */
public class x {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = safeReadInt(scanner);

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

        System.out.println("\nAll Students:");
        for (Student s : students) System.out.println(s);

        Student topper = service.findTopper(students);
        if (topper != null)
            System.out.println("\nTopper: " + topper.getName() + " | Average: " + topper.getAverage());

        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted Students (by Average):");
            for (Student s : students) System.out.println(s);
        }

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

        System.out.print("\nPrint Grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nProgram finished successfully!");
    }

    /**
     * Reads an integer safely from the user, handling invalid inputs.
     *
     * @param scanner scanner object
     * @return a valid integer
     */
    private static int safeReadInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
                scanner.next(); // clear invalid input
            }
        }
    }
}
