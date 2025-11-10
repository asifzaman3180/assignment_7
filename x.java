import java.util.*;

public class StudentManagementApp {

    /*
       CLASS 1: Student (Data Model)
     */
    public static class Student {
        private String name;
        private int id;
        private int mark1, mark2, mark3;
        private double average;
        private String grade;

        /* Constructor */
        public Student(String name, int id, int mark1, int mark2, int mark3) {
            this.name = name;
            this.id = id;
            this.mark1 = mark1;
            this.mark2 = mark2;
            this.mark3 = mark3;
            calculateAverage();
        }

        /* Calculates average marks safely. */
        public void calculateAverage() {
            int totalSubjects = 3;
            this.average = (mark1 + mark2 + mark3) / (double) totalSubjects;
        }

        /* Determines grade based on average. */
        public void calculateGrade() {
            if (average >= 80) grade = "A+";
            else if (average >= 70) grade = "A";
            else if (average >= 60) grade = "B";
            else if (average >= 50) grade = "C";
            else grade = "F";
        }

        // Getters
        public String getName() { return name; }
        public int getId() { return id; }
        public double getAverage() { return average; }
        public String getGrade() { return grade; }

        /* Returns a readable representation of the student. */
        @Override
        public String toString() {
            return String.format("Name: %-10s | ID: %-5d | Average: %-6.2f | Grade: %s",
                    name, id, average, (grade != null ? grade : "N/A"));
        }
    }

    /*
       CLASS 2: StudentService (Logic)
     */
    public static class StudentService {

        
        public Student findTopper(List<Student> students) {
            if (students == null || students.isEmpty()) return null;
            return Collections.max(students, Comparator.comparingDouble(Student::getAverage));
        }

        
        public void sortByAverage(List<Student> students) {
            if (students != null) {
                students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
            }
        }

        /* Searches for a student by ID. Returns null if not found. */
        public Student searchById(List<Student> students, int id) {
            if (students == null) return null;
            for (Student s : students) {
                if (s.getId() == id) return s;
            }
            return null;
        }

        /* Calculates grades for all students. */
        public void calculateGrades(List<Student> students) {
            if (students == null) return;
            for (Student s : students) {
                s.calculateGrade();
            }
        }

        /** Displays all students neatly. */
        public void displayAllStudents(List<Student> students) {
            if (students == null || students.isEmpty()) {
                System.out.println("No students available.");
                return;
            }
            students.forEach(System.out::println);
        }
    }

    /*
      CLASS 3: MainApp (User Interaction)
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int studentCount = getValidIntegerInput(scanner);

        // Input student data
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\n--- Enter details for Student " + (i + 1) + " ---");
            students.add(readStudentDetails(scanner));
        }

        System.out.println("\nAll Students:");
        service.displayAllStudents(students);

        // Display topper
        Student topper = service.findTopper(students);
        if (topper != null) {
            System.out.printf("\nTopper: %s | Average: %.2f%n", topper.getName(), topper.getAverage());
        }

        // Sort by average
        if (askYesNo(scanner, "\nSort by average? (y/n): ")) {
            service.sortByAverage(students);
            System.out.println("\nSorted Students (by average descending):");
            service.displayAllStudents(students);
        }

        // Search by ID
        if (askYesNo(scanner, "\nSearch student by ID? (y/n): ")) {
            System.out.print("Enter ID: ");
            int searchId = getValidIntegerInput(scanner);
            Student found = service.searchById(students, searchId);
            if (found != null) System.out.println("Found: " + found);
            else System.out.println("Student not found.");
        }

        // Calculate grades
        if (askYesNo(scanner, "\nCalculate grades? (y/n): ")) {
            service.calculateGrades(students);
            System.out.println("\nGrades Assigned:");
            service.displayAllStudents(students);
        }

        System.out.println("\nProgram ended. Goodbye!");
        scanner.close();
    }

    /*
      HELPER METHODS
    */

    /* Safely reads integer input. */
    private static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    /** Reads a student’s details safely. */
    private static Student readStudentDetails(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("ID: ");
        int id = getValidIntegerInput(scanner);

        System.out.print("Enter marks for 3 subjects: ");
        int mark1 = getValidIntegerInput(scanner);
        int mark2 = getValidIntegerInput(scanner);
        int mark3 = getValidIntegerInput(scanner);

        return new Student(name, id, mark1, mark2, mark3);
    }

    /* Asks the user a yes/no question. */
    private static boolean askYesNo(Scanner scanner, String message) {
        System.out.print(message);
        String response = scanner.next().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}
