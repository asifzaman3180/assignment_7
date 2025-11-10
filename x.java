import java.util.*;

/**
 * Represents a student with name, ID, marks, average, and grade.
 */
class Student {
    String name;
    int id;
    int mark1, mark2, mark3;
    double average;
    String grade;

    /**
     * Constructor to initialize student details.
     */
    Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
    }

    /**
     * Calculates and updates the average marks for the student.
     */
    void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }

    /**
     * Returns a formatted string representation of the student.
     */
    @Override
    public String toString() {
        return String.format("Name: %s | ID: %d | Average: %.2f | Grade: %s", 
                              name, id, average, (grade == null ? "-" : grade));
    }
}

/**
 * Handles all processing and logic related to students.
 */
class StudentService {

    /**
     * Finds the topper (highest average) in the list.
     * @param students list of Student objects
     * @return the topper Student or null if list is empty
     */
    Student findTopper(List<Student> students) {
        if (students.isEmpty()) return null;
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.average > topper.average) topper = s;
        }
        return topper;
    }

    /**
     * Sorts students in descending order of average marks.
     */
    void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.average, a.average));
    }

    /**
     * Searches a student by their ID.
     * @return the found Student, or null if not found
     */
    Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.id == id) return s;
        }
        return null;
    }

    /**
     * Calculates and assigns grades to all students.
     */
    void printGrades(List<Student> students) {
        for (Student s : students) {
            if (s.average >= 80) s.grade = "A+";
            else if (s.average >= 70) s.grade = "A";
            else if (s.average >= 60) s.grade = "B";
            else if (s.average >= 50) s.grade = "C";
            else s.grade = "F";
            System.out.println(s.name + " -> Grade: " + s.grade);
        }
    }
}

/**
 * Main class for handling user input/output and interaction.
 */
public class x {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = safeIntInput(scanner);

        // --- Input Section ---
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\n--- Enter details for Student " + (i + 1) + " ---");
            System.out.print("Enter name: ");
            String name = scanner.next();

            System.out.print("Enter ID: ");
            int id = safeIntInput(scanner);

            System.out.print("Enter marks for 3 subjects: ");
            int mark1 = safeIntInput(scanner);
            int mark2 = safeIntInput(scanner);
            int mark3 = safeIntInput(scanner);

            students.add(new Student(name, id, mark1, mark2, mark3));
        }

        // --- Display All Students ---
        System.out.println("\nAll Students:");
        for (Student s : students) System.out.println(s);

        // --- Topper ---
        Student topper = service.findTopper(students);
        if (topper != null)
            System.out.println("\nTopper: " + topper.name + " | Average: " + topper.average);
        else
            System.out.println("\nNo students to evaluate.");

        // --- Sorting ---
        System.out.print("\nSort by average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List (by Average):");
            for (Student s : students) System.out.println(s);
        }

        // --- Search ---
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID to search: ");
            int searchId = safeIntInput(scanner);
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found -> " + found);
            else
                System.out.println("Student with ID " + searchId + " not found.");
        }

        // --- Grades ---
        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nBye!");
    }

    /**
     * Handles safe integer input and prevents invalid input errors.
     */
    static int safeIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Enter a valid integer: ");
                scanner.next(); // clear invalid token
            }
        }
    }
}
