import java.util.*;
import java.util.*;

public class Student {
    private String name;
    private int id;
    private int marks1;
    private int marks2;
    private int marks3;
    private double average;

    public Student(String name, int id, int marks1, int marks2, int marks3) {
        this.name = name;
        this.id = id;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        calculateAverage();
    }

    private void calculateAverage() {
        this.average = (marks1 + marks2 + marks3) / 3.0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getAverage() {
        return average;
    }

    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return "Name: " + name + " | ID: " + id + " | Avg: " + String.format("%.2f", average);
    }
}



public class StudentService {

    public Student findTopper(List<Student> students) {
        if (students.isEmpty()) return null;
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    public void sortByAverageDesc(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void displayAll(List<Student> students) {
        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void displayGrades(List<Student> students) {
        System.out.println("\nGrades:");
        for (Student s : students) {
            System.out.println(s.getName() + " | Grade: " + s.getGrade());
        }
    }
}



public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Name: ");
            String name = sc.next();
            System.out.print("ID: ");
            int id = sc.nextInt();
            System.out.print("Marks in 3 subjects: ");
            int m1 = sc.nextInt();
            int m2 = sc.nextInt();
            int m3 = sc.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        service.displayAll(students);

        Student topper = service.findTopper(students);
        if (topper != null) {
            System.out.println("\nTopper: " + topper.getName() + " | Avg: " + topper.getAverage());
        }

        System.out.print("\nSort by Average? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            service.sortByAverageDesc(students);
            System.out.println("Sorted by Average (High to Low):");
            service.displayAll(students);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int sid = sc.nextInt();
            Student found = service.searchById(students, sid);
            if (found != null)
                System.out.println("Found: " + found);
            else
                System.out.println("Student not found.");
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            service.displayGrades(students);
        }

        System.out.println("\nBye!");
        sc.close();
    }
}

