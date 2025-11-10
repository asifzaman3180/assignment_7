public class Student {
    private String name;
    private int id;
    private int m1, m2, m3;
    private double average;

    public Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        calculateAverage();
    }

    private void calculateAverage() {
        average = (m1 + m2 + m3) / 3.0;
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

    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    public void display() {
        System.out.println("Name: " + name + " | ID: " + id + " | Avg: " + average);
    }
}
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public void displayAll() {
        System.out.println("\nAll Students:");
        for (Student s : students) {
            s.display();
        }
    }

    public Student getTopper() {
        if (students.isEmpty()) return null;
        Student top = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > top.getAverage()) top = s;
        }
        return top;
    }

    public void sortByAverage() {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
        System.out.println("\nSorted List (by Average):");
        for (Student s : students) {
            System.out.println(s.getName() + " " + s.getAverage());
        }
    }

    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void displayGrades() {
        System.out.println("\nGrades:");
        for (Student s : students) {
            System.out.println(s.getName() + " Grade: " + s.getGrade());
        }
    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        System.out.print("Enter number of students: ");
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Name: ");
            String name = s.next();
            System.out.print("ID: ");
            int id = s.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            int m1 = s.nextInt();
            int m2 = s.nextInt();
            int m3 = s.nextInt();

            Student st = new Student(name, id, m1, m2, m3);
            manager.addStudent(st);
        }

        manager.displayAll();

        Student top = manager.getTopper();
        if (top != null)
            System.out.println("\nTopper: " + top.getName() + " | Avg: " + top.getAverage());

        System.out.print("\nSort by Average? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) manager.sortByAverage();

        System.out.print("\nSearch student by ID? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int sid = s.nextInt();
            Student found = manager.searchById(sid);
            if (found != null)
                System.out.println("Found: " + found.getName() + " | Avg: " + found.getAverage());
            else
                System.out.println("Not found");
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) manager.displayGrades();

        System.out.println("\nBye!");
        s.close();
    }
}
