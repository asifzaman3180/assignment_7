import java.util.*;

class Student {
    private String name;
    private int id;
    private int subject1;
    private int subject2;
    private int subject3;
    private double average;
    private String grade;

    public Student(String name, int id, int subject1, int subject2, int subject3) {
        this.name = name;
        this.id = id;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        calculateAverage();
    }

    private void calculateAverage() {
        this.average = (subject1 + subject2 + subject3) / 3.0;
    }

    public void assignGrade() {
        if (average >= 80) grade = "A+";
        else if (average >= 70) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 50) grade = "C";
        else grade = "F";
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
        return grade;
    }
}

class StudentService {
    public Student findTopper(List<Student> students) {
        Student top = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > top.getAverage()) {
                top = s;
            }
        }
        return top;
    }

    public void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void calculateGrades(List<Student> students) {
        for (Student s : students) {
            s.assignGrade();
        }
    }
}

public class x {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name: ");
            String name = scanner.next();
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            int s1 = scanner.nextInt();
            int s2 = scanner.nextInt();
            int s3 = scanner.nextInt();
            students.add(new Student(name, id, s1, s2, s3));
        }

        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println("Name: " + s.getName() + " | ID: " + s.getId() + " | Average: " + s.getAverage());
        }

        Student topper = service.findTopper(students);
        System.out.println("\nTopper: " + topper.getName() + " | Average: " + topper.getAverage());

        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List (by Average):");
            for (Student s : students) {
                System.out.println(s.getName() + " | Average: " + s.getAverage());
            }
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null) {
                System.out.println("Found: " + found.getName() + " | Average: " + found.getAverage());
            } else {
                System.out.println("Student not found!");
            }
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.calculateGrades(students);
            System.out.println("\nGrades:");
            for (Student s : students) {
                System.out.println(s.getName() + " | Grade: " + s.getGrade());
            }
        }

        System.out.println("\nGoodbye!");
    }
}
