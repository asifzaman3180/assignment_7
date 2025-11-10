import java.util.*;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int count = readInt(scanner);

        // Input student data
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter name: ");
            String name = scanner.next();

            System.out.print("Enter ID: ");
            int id = readInt(scanner);

            System.out.print("Enter marks for 3 subjects: ");
            int m1 = readInt(scanner);
            int m2 = readInt(scanner);
            int m3 = readInt(scanner);

            service.addStudent(new Student(id, name, m1, m2, m3));
        }

        // Display all students
        System.out.println("\nAll Students:");
        service.printAllStudents();

        // Find and show topper
        Student topper = service.findTopper();
        if (topper != null) {
            System.out.println("\nTopper → " + topper);
        }

        // Sorting
        System.out.print("\nSort by average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage();
            System.out.println("\nSorted List:");
            service.printAllStudents();
        }

        // Searching
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = readInt(scanner);
            Student found = service.searchById(searchId);
            if (found != null)
                System.out.println("Found → " + found);
            else
                System.out.println("No student found with ID " + searchId);
        }

        // Grades
        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades();
        }

        System.out.println("\nBye!");
        scanner.close();
    }

    /** Safely reads integer input */
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.next(); // clear invalid input
            }
        }
    }
}

/**
 * Represents a student with ID, name, and marks.
 */
class Student {
    private int id;
    private String name;
    private int mark1, mark2, mark3;
    private double average;

    public Student(int id, String name, int mark1, int mark2, int mark3) {
        this.id = id;
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
    }

    /** Calculates average safely */
    public void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getAverage() { return average; }

    /** Returns grade according to average */
    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return String.format("Name: %s | ID: %d | Average: %.2f", name, id, average);
    }
}

/**
 * Provides services for managing Student objects.
 */
class StudentService {
    private List<Student> students = new ArrayList<>();

    /** Add a new student */
    public void addStudent(Student s) {
        students.add(s);
    }

    /** Print all students */
    public void printAllStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /** Find the topper student */
    public Student findTopper() {
        if (students.isEmpty()) return null;
        return Collections.max(students, Comparator.comparingDouble(Student::getAverage));
    }

    /** Sort students by average descending */
    public void sortByAverage() {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    /** Search student by ID */
    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    /** Print grades of all students */
    public void printGrades() {
        for (Student s : students) {
            System.out.println(s.getName() + " → Grade: " + s.getGrade());
        }
    }
}
