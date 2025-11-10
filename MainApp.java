import java.util.*;

class Student {
    private String name;
    private int id;
    private int ma1, ma2, ma3;
    private double average;

    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.ma1 = ma1;
        this.ma2 = ma2;
        this.ma3 = ma3;
        calculateAverage();
    }

    private void calculateAverage() {
        average = (ma1 + ma2 + ma3) / 3.0;
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
        return "Name: " + name + " | ID: " + id + " | Avg: " + average;
    }
}

class StudentService {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void inputStudents() {
        System.out.print("Enter number of students: ");
        int n = getValidInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name: ");
            String name = scanner.next();
            System.out.print("Enter ID: ");
            int id = getValidInt();
            System.out.print("Enter marks of 3 subjects: ");
            int m1 = getValidInt();
            int m2 = getValidInt();
            int m3 = getValidInt();
            students.add(new Student(name, id, m1, m2, m3));
        }
    }

    public void showAllStudents() {
        System.out.println("All Students:");
        for (Student s : students) System.out.println(s);
    }

    public void showTopper() {
        if (students.isEmpty()) return;
        Student top = Collections.max(students, Comparator.comparingDouble(Student::getAverage));
        System.out.println("Topper: " + top.getName() + " | Avg: " + top.getAverage());
    }

    public void sortByAverage() {
        System.out.print("Sort by average? (y/n): ");
        if (!scanner.next().equalsIgnoreCase("y")) return;
        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
        System.out.println("Sorted List:");
        for (Student s : students) System.out.println(s.getName() + " " + s.getAverage());
    }

    public void searchById() {
        System.out.print("Search student by ID? (y/n): ");
        if (!scanner.next().equalsIgnoreCase("y")) return;
        System.out.print("Enter ID: ");
        int id = getValidInt();
        boolean found = false;
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Found: " + s.getName() + " | Avg: " + s.getAverage());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Not found");
    }

    public void calculateGrades() {
        System.out.print("Calculate grades? (y/n): ");
        if (!scanner.next().equalsIgnoreCase("y")) return;
        for (Student s : students)
            System.out.println(s.getName() + " | Grade: " + s.getGrade());
    }

    private int getValidInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input, enter an integer: ");
                scanner.next();
            }
        }
    }
}

public class MainApp {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        service.inputStudents();
        service.showAllStudents();
        service.showTopper();
        service.sortByAverage();
        service.searchById();
        service.calculateGrades();
        System.out.println("Bye!");
    }
}
