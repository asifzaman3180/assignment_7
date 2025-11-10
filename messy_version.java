import java.util.*;

// Represents a single student with ID, name, marks, and average.
 
class Student {
    String name;
    int id;
    int marksSubject1;
    int marksSubject2;
    int marksSubject3;
    double average;

    // Constructor to initialize student details
    public Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.marksSubject1 = m1;
        this.marksSubject2 = m2;
        this.marksSubject3 = m3;
        this.average = (m1 + m2 + m3) / 3.0;
    }

    // Method to compute and return grade
    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    // Display-friendly format for student details
    @Override
    public String toString() {
        return "Name: " + name + " | ID: " + id + " | Average: " + String.format("%.2f", average);
    }
}

/**
 * Main class for handling student input, processing, and output.
 */
public class StudentApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        // ===== Step 1: Input Student Data =====
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

            // Create Student object and add to list
            studentList.add(new Student(name, id, marks1, marks2, marks3));
        }

        // ===== Step 2: Display All Students =====
        System.out.println("\n--- All Students ---");
        for (Student student : studentList) {
            System.out.println(student);
        }

        // ===== Step 3: Find Topper =====
        Student topper = studentList.get(0);
        for (Student student : studentList) {
            if (student.average > topper.average) {
                topper = student;
            }
        }
        System.out.println("\nTopper: " + topper.name + " | Average: " + topper.average);

        // ===== Step 4: Sort by Average =====
        System.out.print("\nDo you want to sort students by average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            studentList.sort((a, b) -> Double.compare(b.average, a.average));
            System.out.println("\n--- Students Sorted by Average (High to Low) ---");
            for (Student student : studentList) {
                System.out.println(student.name + " | Average: " + String.format("%.2f", student.average));
            }
        }

        // ===== Step 5: Search Student by ID =====
        System.out.print("\nDo you want to search a student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter student ID: ");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (Student student : studentList) {
                if (student.id == searchId) {
                    System.out.println("Found: " + student);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found!");
            }
        }

        // ===== Step 6: Display Grades =====
        System.out.print("\nDo you want to calculate and display grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("\n--- Grades ---");
            for (Student student : studentList) {
                System.out.println(student.name + " | Grade: " + student.getGrade());
            }
        }

        // ===== Step 7: Exit =====
        System.out.println("\nProgram finished. Goodbye!");
        scanner.close();
    }
}

