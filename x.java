import java.util.*;

// ------------------- Student Class -------------------
class Student {
    private String name;
    private int id;
    private int subject1;
    private int subject2;
    private int subject3;
    private double average;
    private String grade;

    // Constructor
    public Student(String name, int id, int subject1, int subject2, int subject3) {
        this.name = name;
        this.id = id;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    // Getters
    public String getName() { return name; }
    public int getId() { return id; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }

    // Setters
    public void setAverage(double average) { this.average = average; }
    public void setGrade(String grade) { this.grade = grade; }

    // Calculate average
    public void calculateAverage() {
        this.average = (subject1 + subject2 + subject3) / 3.0;
    }

    @Override
    public String toString() {
        return String.format("Name: %-10s | ID: %-5d | Avg: %.2f | Grade: %s",
                name, id, average, (grade == null ? "-" : grade));
    }
}

// ------------------- StudentService Class -------------------
class StudentService {

    // Calculate averages for all students
    public void calculateAverage(List<Student> students) {
        for (Student s : students) {
            s.calculateAverage();
        }
    }

    // Find topper
    public Student findTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    // Sort students by average (descending)
    public void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    // Search student by ID
    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // Assign and print grades
    public void printGrades(List<Student> students) {
        System.out.println("\nGrades:");
        for (Student s : students) {
            double avg = s.getAverage();
            String grade;
            if (avg >= 80) grade = "A+";
            else if (avg >= 70) grade = "A";
            else if (avg >= 60) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "F";

            s.setGrade(grade);
            System.out.println(s.getName() + " | Grade: " + grade);
        }
    }
}

// ------------------- MainApp (X) Class -------------------
public class X {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        // Input students
        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter marks for 3 subjects: ");
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            int m3 = scanner.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        // Process data
        service.calculateAverage(students);

        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println(s);
        }

        // Find topper
        Student topper = service.findTopper(students);
        System.out.println("\nTopper: " + topper.getName() + " | Avg: " + topper.getAverage());

        // Sort by average
        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted by Average (Descending):");
            for (Student s : students) {
                System.out.println(s);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found: " + found);
            else
                System.out.println("Student not found.");
        }

        // Print grades
        System.out.print("\nPrint grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
