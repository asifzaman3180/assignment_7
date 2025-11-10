import java.util.*;

class Student {
    private int id;
    private String name;
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

    public void calculateGrade() {
        if (average >= 80) grade = "A+";
        else if (average >= 70) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 50) grade = "C";
        else grade = "F";
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
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Avg: " + average;
    }
}

public class StudentApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Student> students = inputStudents();

        displayStudents(students);

        Student topper = findTopper(students);
        System.out.println("Topper: " + topper);

        System.out.println("Sort by Average? y/n");
        if (scanner.next().equals("y")) {
            students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
            displayStudents(students);
        }

        System.out.println("Search student by ID?");
        if (scanner.next().equals("y")) {
            searchStudentById(students);
        }

        System.out.println("Bye!");
    }

    private static List<Student> inputStudents() {
        System.out.println("Enter number of students: ");
        int n = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter ID:");
            int id = scanner.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            int[] marks = {scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
            students.add(new Student(name, id, marks));
        }
        return students;
    }

    private static void displayStudents(List<Student> students) {
        System.out.println("All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static Student findTopper(List<Student> students) {
        return Collections.max(students, Comparator.comparingDouble(Student::getAverage));
    }

    private static void searchStudentById(List<Student> students) {
        System.out.println("Enter ID:");
        int searchId = scanner.nextInt();
        for (Student s : students) {
            if (s.getId() == searchId) {
                System.out.println("Found: " + s);
                System.out.println("Calculate grade?");
                if (scanner.next().equals("y")) {
                    for (Student stu : students) {
                        stu.calculateGrade();
                        System.out.println(stu.getName() + " Grade: " + stu.getGrade());
                    }
                }
                return;
            }
        }
        System.out.println("Not found");
    }
}
