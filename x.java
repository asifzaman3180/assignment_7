import java.util.*;

/**
 * OOP-based Student Management Application
 * (All logic inside one file using inner classes)
 */
public class StudentApp {

    // ---------- Inner Class: Student ----------
    static class Student {
        private String name;
        private int id;
        private int mark1, mark2, mark3;
        private double average;

        public Student(String name, int id, int mark1, int mark2, int mark3) {
            this.name = name;
            this.id = id;
            this.mark1 = mark1;
            this.mark2 = mark2;
            this.mark3 = mark3;
            calculateAverage();
        }

        public void calculateAverage() {
            this.average = (mark1 + mark2 + mark3) / 3.0;
        }

        public double getAverage() {
            return average;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Name: " + name + " | ID: " + id + " | Avg: " + String.format("%.2f", average);
        }
    }

    // ---------- Inner Class: StudentService ----------
    static class StudentService {

        // Find topper
        public Student findTopper(List<Student> students) {
            return Collections.max(students, Comparator.comparingDouble(Student::getAverage));
        }

        // Sort by average (descending)
        public void sortByAverage(List<Student> students) {
            students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
        }

        // Search by ID
        public Student searchById(List<Student> students, int id) {
            for (Student s : students) {
                if (s.getId() == id) return s;
            }
            return null;
        }

        // Print grades
        public void printGrades(List<Student> students) {
            for (Student s : students) {
                double avg = s.getAverage();
                String grade;
                if (avg >= 80) grade = "A+";
                else if (avg >= 70) grade = "A";
                else if (avg >= 60) grade = "B";
                else if (avg >= 50) grade = "C";
                else grade = "F";
                System.out.println(s.getName() + " → Grade: " + grade);
            }
        }
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int totalStudents = input.nextInt();

        // Input data
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = input.next();
            System.out.print("Enter ID: ");
            int id = input.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            int m1 = input.nextInt(), m2 = input.nextInt(), m3 = input.nextInt();
            students.add(new Student(name, id, m1, m2, m3));
        }

        // Display all
        System.out.println("\nAll Students:");
        students.forEach(System.out::println);

        // Topper
        Student topper = service.findTopper(students);
        System.out.println("\nTopper: " + topper.getName() + " | Avg: " + String.format("%.2f", topper.getAverage()));

        // Sort
        System.out.print("\nSort by Average? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List:");
            students.forEach(System.out::println);
        }

        // Search
        System.out.print("\nSearch student by ID? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int id = input.nextInt();
            Student found = service.searchById(students, id);
            if (found != null) System.out.println("Found: " + found);
            else System.out.println("Not found!");
        }

        // Grades
        System.out.print("\nCalculate grades? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        input.close();
    }
}
