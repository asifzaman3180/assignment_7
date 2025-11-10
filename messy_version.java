import java.util.*;

/**
 * Represents a single student with ID, name, marks, and average.
 */
class Student {
    String name;
    int id;
    int marksSubject1;
    int marksSubject2;
    int marksSubject3;
    double average;

    // Constructor
    public Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.marksSubject1 = Math.max(0, m1); // Prevent negative marks
        this.marksSubject2 = Math.max(0, m2);
        this.marksSubject3 = Math.max(0, m3);
        this.average = calculateAverage();
    }

    /** Safely calculate average (avoid division by zero). */
    public double calculateAverage() {
        return (marksSubject1 + marksSubject2 + marksSubject3) / 3.0;
    }

    /** Determine grade based on average. */
    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    /** Provide a clean, readable string representation of a student. */
    @Override
    public String toString() {
        return String.format(
            "Name: %-10s | ID: %-5d | Marks: [%3d, %3d, %3d] | Average: %6.2f | Grade: %-2s",
            name, id, marksSubject1, marksSubject2, marksSubject3, average, getGrade()
        );
    }
}

/**
 * Main application class for managing students.
 */
public class StudentApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        int numberOfStudents = getValidInteger(scanner, "Enter number of students: ", 1, 1000);

        // ===== Step 1: Input Students =====
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));

            System.out.print("Enter name: ");
            String name = scanner.next();

            int id = getValidInteger(scanner, "Enter ID (positive integer): ", 1, Integer.MAX_VALUE);
            int marks1 = getValidInteger(scanner, "Enter marks for Subject 1 (0–100): ", 0, 100);
            int marks2 = getValidInteger(scanner, "Enter marks for Subject 2 (0–100): ", 0, 100);
            int marks3 = getValidInteger(scanner, "Enter marks for Subject 3 (0–100): ", 0, 100);

            studentList.add(new Student(name, id, marks1, marks2, marks3));
        }

        if (studentList.isEmpty()) {
            System.out.println("No students to process. Exiting program.");
            scanner.close();
            return;
        }

        // ===== Step 2: Display All Students =====
        System.out.println("\n--- All Students ---");
        for (Student student : studentList) {
            System.out.println(student);
        }

        // ===== Step 3: Find Topper =====
        Student topper = findTopper(studentList);
        if (topper != null) {
            System.out.println("\nTopper: " + topper.name + " | Average: " + String.format("%.2f", topper.average));
        }

        // ===== Step 4: Sort by Average =====
        System.out.print("\nDo you want to sort students by average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(studentList);
            System.out.println("\n--- Students Sorted by Average (High to Low) ---");
            for (Student s : studentList) {
                System.out.println(s);
            }
        }

        // ===== Step 5: Search by ID =====
        System.out.print("\nDo you want to search a student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            int searchId = getValidInteger(scanner, "Enter student ID: ", 1, Integer.MAX_VALUE);
            Student found = searchById(studentList, searchId);
            if (found != null) {
                System.out.println("Found: " + found);
            } else {
                System.out.println("No student found with ID: " + searchId);
            }
        }

        // ===== Step 6: Print Grades =====
        System.out.print("\nDo you want to print grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            printGrades(studentList);
        }

        System.out.println("\nProgram finished. Goodbye!");
        scanner.close();
    }

    // ===================== Independent Methods =====================

    /** Finds and returns the student with the highest average. */
    public static Student findTopper(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No students available to find topper.");
            return null;
        }
        Student topper = students.get(0);
        for (Student student : students) {
            if (student.average > topper.average) {
                topper = student;
            }
        }
        return topper;
    }

    /** Sorts the list of students by their average (descending order). */
    public static void sortByAverage(ArrayList<Student> students) {
        if (students != null && !students.isEmpty()) {
            students.sort((a, b) -> Double.compare(b.average, a.average));
        } else {
            System.out.println("No students to sort.");
        }
    }

    /** Searches for a student by ID and returns the object if found. */
    public static Student searchById(ArrayList<Student> students, int id) {
        if (students == null || students.isEmpty()) return null;

        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    /** Prints each student's grade safely. */
    public static void printGrades(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No students available to print grades.");
            return;
        }

        System.out.println("\n--- Student Grades ---");
        for (Student student : students) {
            System.out.printf("%-10s | Grade: %s%n", student.name, student.getGrade());
        }
    }

    /** Safely gets an integer input within a valid range. */
    public static int getValidInteger(Scanner scanner, String prompt, int min, int max) {
        int value = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    System.out.println("  Please enter a value between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("  Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear invalid input
            }
        }
        return value;
    }
}

