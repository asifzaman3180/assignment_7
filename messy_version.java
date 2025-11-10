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
        this.marksSubject1 = m1;
        this.marksSubject2 = m2;
        this.marksSubject3 = m3;
        this.average = calculateAverage();
    }

    // Independent method to calculate the student's average marks
    public double calculateAverage() {
        return (marksSubject1 + marksSubject2 + marksSubject3) / 3.0;
    }

    // Determine the grade based on average
    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return "Name: " + name + " | ID: " + id + " | Average: " + String.format("%.2f", average);
    }
}

/**
 * Main application class for managing students.
 */
public class StudentApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        // ===== Step 1: Input Students =====
        System.out.print("Enter number of students: ");
        int numberOfStudents = scanner.nextInt();

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));

            System.out.print("Enter name: ");
            String name = scanner.next();

            System.out.print("Enter ID: ");
            int id = scanner.nextInt();

            System.out.print("Enter marks of 3 subjects: ");
            int marks1 = scanner.nextInt();
            int marks2 = scanner.nextInt();
            int marks3 = scanner.nextInt();

            studentList.add(new Student(name, id, marks1, marks2, marks3));
        }

        // Display all students
        System.out.println("\n--- All Students ---");
        for (Student student : studentList) {
            System.out.println(student);
        }

        // ===== Step 2: Find Topper =====
        Student topper = findTopper(studentList);
        System.out.println("\nTopper: " + topper.name + " | Average: " + topper.average);

        // ===== Step 3: Sort by Average =====
        System.out.print("\nDo you want to sort students by average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(studentList);
            System.out.println("\n--- Students Sorted by Average (High to Low) ---");
            for (Student s : studentList) {
                System.out.println(s.name + " | Average: " + String.format("%.2f", s.average));
            }
        }

        // ===== Step 4: Search by ID =====
        System.out.print("\nDo you want to search a student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter student ID: ");
            int searchId = scanner.nextInt();
            Student found = searchById(studentList, searchId);
            if (found != null) {
                System.out.println("Found: " + found);
            } else {
                System.out.println("Student not found!");
            }
        }

        // ===== Step 5: Print Grades =====
        System.out.print("\nDo you want to print grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            printGrades(studentList);
        }

        System.out.println("\nProgram finished. Goodbye!");
        scanner.close();
    }

    // ===================== Extracted Methods =====================

    /** Finds and returns the student with the highest average. */
    public static Student findTopper(ArrayList<Student> students) {
        if (students.isEmpty()) return null;
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
        students.sort((a, b) -> Double.compare(b.average, a.average));
    }

    /** Searches for a student by ID and returns the object if found. */
    public static Student searchById(ArrayList<Student> students, int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    /** Prints each student's grade. */
    public static void printGrades(ArrayList<Student> students) {
        System.out.println("\n--- Student Grades ---");
        for (Student student : students) {
            System.out.println(student.name + " | Grade: " + student.getGrade());
        }
    }
}

