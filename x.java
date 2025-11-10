import java.util.*;

class Student {
    private String name;
    private int id;
    private int mark1, mark2, mark3;
    private double average;
    private String grade;

    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
        this.grade = "";
    }

    public void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
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
        return "Name: " + name + " ID: " + id + " Avg: " + average;
    }
}

class StudentService {
    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Student findTopper() {
        if (students.isEmpty()) return null;
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    public void sortByAverage() {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void printGrades() {
        for (Student s : students) {
            s.calculateGrade();
            System.out.println(s.getName() + " Grade: " + s.getGrade());
        }
    }

    public boolean hasStudents() {
        return !students.isEmpty();
    }
}

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.println("Enter number of students:");
        int numStudents = 0;
        try {
            numStudents = scanner.nextInt();
            if (numStudents <= 0) {
                System.out.println("Number of students must be greater than 0.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Exiting.");
            return;
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter ID:");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID. Skipping this student.");
                scanner.nextLine();
                continue;
            }
            System.out.println("Enter marks of 3 subjects:");
            int m1 = safeNextInt(scanner);
            int m2 = safeNextInt(scanner);
            int m3 = safeNextInt(scanner);
            service.addStudent(new Student(name, id, m1, m2, m3));
        }

        System.out.println("\nAll Students:");
        service.printAllStudents();

        Student topper = service.findTopper();
        if (topper != null) {
            System.out.println("\nTopper: " + topper.getName() + " Avg: " + topper.getAverage());
        }

        System.out.println("\nSort by Average? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverage();
            System.out.println("\nSorted List:");
            service.printAllStudents();
        }

        System.out.println("\nSearch student by ID? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("Enter ID:");
            int searchId = safeNextInt(scanner);
            Student s = service.searchById(searchId);
            if (s != null) System.out.println("Found: " + s);
            else System.out.println("Not found");
        }

        System.out.println("\nCalculate grade? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.printGrades();
        }

        System.out.println("\nBye!");
    }

    private static int safeNextInt(Scanner scanner) {
        int val = 0;
        try {
            val = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Defaulting to 0.");
            scanner.nextLine();
        }
        return val;
    }
}
