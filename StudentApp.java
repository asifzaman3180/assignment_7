import java.util.*;

// Class to store student data
class Student {
    String name;
    int id;
    int marks1, marks2, marks3;
    double average;

    // Constructor
    Student(String name, int id, int marks1, int marks2, int marks3) {
        this.name = name;
        this.id = id;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.average = calculateAverage();
    }

    // Method to calculate average marks
    double calculateAverage() {
        return (marks1 + marks2 + marks3) / 3.0;
    }
}

public class StudentApp {

    // Method to find the topper student
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

    // Method to sort students by average (descending)
    static void sortByAverage(ArrayList<Student> students) {
        students.sort((a, b) -> Double.compare(b.average, a.average));
    }

    // Method to search a student by ID
    static Student searchById(ArrayList<Student> students, int searchId) {
        for (Student student : students) {
            if (student.id == searchId) {
                return student;
            }
        }
        return null;
    }

    // Method to print grades for all students
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

    // Main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int totalStudents = input.nextInt();

        // Input student details
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1));
            System.out.print("Name: ");
            String name = input.next();
            System.out.print("ID: ");
            int id = input.nextInt();
            System.out.println("Enter marks for 3 subjects:");
            int marks1 = input.nextInt();
            int marks2 = input.nextInt();
            int marks3 = input.nextInt();

            students.add(new Student(name, id, marks1, marks2, marks3));
        }

        // Display all students
        System.out.println("\nAll Students:");
        for (Student student : students) {
            System.out.printf("Name: %-10s ID: %-5d Avg: %.2f%n", student.name, student.id, student.average);
        }

        // Find and display topper
        Student topper = findTopper(students);
        if (topper != null) {
            System.out.printf("\nTopper: %s (Avg: %.2f)%n", topper.name, topper.average);
        }

        // Sort students by average if user chooses
        System.out.print("\nSort by Average? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            sortByAverage(students);
            System.out.println("\nSorted List (by Average Descending):");
            for (Student student : students) {
                System.out.printf("%-10s Avg: %.2f%n", student.name, student.average);
            }
        }

        // Search by ID if user chooses
        System.out.print("\nSearch student by ID? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = input.nextInt();
            Student found = searchById(students, searchId);
            if (found != null)
                System.out.printf("Found: %s (Avg: %.2f)%n", found.name, found.average);
            else
                System.out.println("Student not found.");
        }

        // Print grades if user chooses
        System.out.print("\nCalculate Grades? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            printGrades(students);
        }

        System.out.println("\nBye!");
        input.close();
    }
}
