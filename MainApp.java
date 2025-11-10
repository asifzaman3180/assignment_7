import java.util.*;

class Student {
    private String name;
    private int id;
    private int[] marks = new int[3];
    private double average;
    private String grade;

    public Student(String name, int id, int[] marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
        calculateAverage();
    }

    public void calculateAverage() {
        this.average = (marks[0] + marks[1] + marks[2]) / 3.0;
    }

    public void assignGrade() {
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
}

class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getTopper() {
        if (students.isEmpty()) return null;
        Student top = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > top.getAverage()) {
                top = s;
            }
        }
        return top;
    }

    public void sortByAverageDescending() {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void assignGrades() {
        for (Student s : students) {
            s.assignGrade();
        }
    }

    public void displayAllStudents() {
        for (Student s : students) {
            System.out.printf("Name: %-10s Id: %-5d Avg: %.2f%n", s.getName(), s.getId(), s.getAverage());
        }
    }

    public void displayGrades() {
        for (Student s : students) {
            System.out.printf("%-10s Grade: %s%n", s.getName(), s.getGrade());
        }
    }
}

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1));
            System.out.print("Name: ");
            String name = sc.next();
            System.out.print("ID: ");
            int id = sc.nextInt();
            int[] marks = new int[3];
            System.out.println("Enter marks for 3 subjects:");
            for (int j = 0; j < 3; j++) {
                marks[j] = sc.nextInt();
            }

            service.addStudent(new Student(name, id, marks));
        }

        System.out.println("\nAll Students:");
        service.displayAllStudents();

        Student topper = service.getTopper();
        if (topper != null) {
            System.out.printf("Topper: %s (Avg: %.2f)%n", topper.getName(), topper.getAverage());
        }

        System.out.print("\nSort by average? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            service.sortByAverageDescending();
            System.out.println("Sorted List:");
            service.displayAllStudents();
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int sid = sc.nextInt();
            Student found = service.searchById(sid);
            if (found != null)
                System.out.printf("Found: %s (Avg: %.2f)%n", found.getName(), found.getAverage());
            else
                System.out.println("Student not found.");
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            service.assignGrades();
            System.out.println("Grades:");
            service.displayGrades();
        }

        System.out.println("\nBye!");
        sc.close();
    }
}
