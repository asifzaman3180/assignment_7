import java.util.*;

class Student {
    private String name;
    private int id;
    private int[] marks = new int[3];
    private double average;

    public Student(String name, int id, int[] marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
        this.average = calculateAverage();
    }

    public double calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return marks.length > 0 ? (sum / (double) marks.length) : 0.0;
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
        return String.format("Name: %s | ID: %d | Average: %.2f", name, id, average);
    }
}

class StudentService {

    public Student findTopper(List<Student> students) {
        return students.stream().max(Comparator.comparingDouble(Student::getAverage)).orElse(null);
    }

    public void sortByAverage(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getAverage).reversed());
    }

    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }
    
    public void printGrades(List<Student> students) {
        for (Student s : students) {
            double avg = s.getAverage();
            String grade;

            if (avg >= 80) grade = "A+";
            else if (avg >= 70) grade = "A";
            else if (avg >= 60) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "F";

            System.out.println(s.getName() + " | Grade: " + grade);
        }
    }
}

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();

            System.out.print("ID: ");
            int id = scanner.nextInt();

            int[] marks = new int[3];
            System.out.print("Enter marks of 3 subjects: ");
            for (int j = 0; j < 3; j++) {
                marks[j] = scanner.nextInt();
            }

            students.add(new Student(name, id, marks));
        }

        System.out.println("\nAll Students:");
        students.forEach(System.out::println);

        Student topper = service.findTopper(students);
        if (topper != null) {
            System.out.println("\nTopper: " + topper.getName() + " | Average: " + topper.getAverage());
        }

        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List:");
            students.forEach(System.out::println);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            Student found = service.searchById(students, id);
            if (found != null) System.out.println("Found: " + found);
            else System.out.println("Not found!");
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
