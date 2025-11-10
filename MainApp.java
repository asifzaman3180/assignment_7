import java.util.ArrayList;
import java.util.Scanner;
<<<<<<< HEAD

// Student class to store student info and calculate average
class Student {
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

    private void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }

    public double getAverage() { return average; }
    public int getId() { return id; }
    public String getName() { return name; }

    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Avg: " + average;
    }
}

// Service class for student operations
class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) { students.add(s); }

    public Student findTopper() {
        Student topper = null;
        double topAvg = -1;
        for (Student s : students) {
            if (s.getAverage() > topAvg) {
                topAvg = s.getAverage();
                topper = s;
            }
        }
        return topper;
    }

    public void sortByAverageDescending() {
        students.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));
    }

    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void printAllStudents() {
        for (Student s : students) System.out.println(s);
    }

    public void printGrades() {
        for (Student s : students) System.out.println(s.getName() + " Grade: " + s.getGrade());
    }
}

// Main application class
public class Assignment7 {
    private StudentService service = new StudentService();
    private Scanner scanner = new Scanner(System.in);

=======
// Student class to store student info and calculate average 
public class MainApp {
>>>>>>> documentation
    public static void main(String[] args) {
        Assignment7 app = new Assignment7();
        app.run();
    }

    public void run() {
        System.out.println("Enter number of students: ");
        int n = safeNextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter ID:");
            int id = safeNextInt();
            System.out.println("Enter marks of 3 subjects:");
            int m1 = safeNextInt();
            int m2 = safeNextInt();
            int m3 = safeNextInt();

            Student s = new Student(name, id, m1, m2, m3);
            service.addStudent(s);
        }

        System.out.println("\nAll Students:");
        service.printAllStudents();

        Student topper = service.findTopper();
        System.out.println("\nTopper: " + topper.getName() + " Avg: " + topper.getAverage());

        System.out.println("\nSort by Average? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverageDescending();
            System.out.println("Sorted List:");
            service.printAllStudents();
        }

        System.out.println("\nSearch student by ID? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("Enter ID:");
            int sid = safeNextInt();
            Student s = service.searchById(sid);
            if (s != null) System.out.println("Found: " + s);
            else System.out.println("Not found");
        }

        System.out.println("\nCalculate grade? y/n");
        if (scanner.next().equalsIgnoreCase("y")) service.printGrades();

        System.out.println("\nBye!");
    }

    private int safeNextInt() {
        while (true) {
            try { return Integer.parseInt(scanner.next()); }
            catch (NumberFormatException e) { System.out.println("Invalid input. Enter an integer:"); }
        }
    }
}
