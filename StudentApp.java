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

    // Method to calculate average marks safely
    double calculateAverage() {
        int subjects = 3;
        if (subjects == 0) return 0.0; // Avoid division by zero (defensive)
        return (marks1 + marks2 + marks3) / (double) subjects;
    }

    // toString method for cleaner display
    @Override
    public String toString() {
        return String.format("Name: %-10s | ID: %-5d | Avg: %.2f", name, id, average);
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

    // Utility method to safely read a positive integer
    static int readPositiveInt(Scanner input, String prompt) {
        int num;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                num = input.nextInt();
                if (num > 0) break;
                else System.out.println("Please enter a positive number.");
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                input.next(); // discard invalid input
            }
        }
        return num;
    }

    // Utility method to read valid marks (0–100)
    static int readMarks(Scanner input, String prompt) {
        int marks;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                marks = input.nextInt();
                if (marks >= 0 && marks <= 100) break;
                else System.out.println("Marks must be between 0 and 100.");
            } else {
                System.out.println("Invalid input. Please enter an integer between 0 and 100.");
                input.next();
            }
        }
        return marks;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int totalStudents = readPositiveInt(input, "Enter number of students: ");

        HashSet<Integer> usedIds = new HashSet<>(); // to ensure unique IDs

        // Input student details
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1));

            System.out.print("Name: ");
            String name = input.next();

            int id;
            while (true) {
                id = readPositiveInt(input, "ID: ");
                if (!usedIds.contains(id)) {
                    usedIds.add(id);
                    break;
                } else {
                    System.out.println("This ID is already used. Please enter a unique ID.");
                }
            }

            System.out.println("Enter marks for 3 subjects:");
            int marks1 = readMarks(input, "Subject 1: ");
            int marks2 = readMarks(input, "Subject 2: ");
            int marks3 = readMarks(input, "Subject 3: ");

            students.add(new Student(name, id, marks1, marks2, marks3));
        }

        // Display all students (using toString)
        System.out.println("\nAll Students:");
        for (Student student : students) {
            System.out.println(student);
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
                System.out.println(student);
            }
        }

        // Search by ID if user chooses
        System.out.print("\nSearch student by ID? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            int searchId = readPositiveInt(input, "Enter ID to search: ");
            Student found = searchById(students, searchId);
            if (found != null)
                System.out.printf("Found: %s%n", found);
            else
                System.out.println("Student with ID " + searchId + " not found.");
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
