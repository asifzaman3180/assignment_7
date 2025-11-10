import java.util.*;

class Student {
    String name;
    int id;
    int mark1;
    int mark2;
    int mark3;
    double average;

    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.average = calculateAverage();
    }

    public double calculateAverage() {
        double total = mark1 + mark2 + mark3;
        return total / 3.0; 
    }

    public String calculateGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return String.format("Name: %s | ID: %d | Marks: [%d, %d, %d] | Avg: %.2f",
                name, id, mark1, mark2, mark3, average);
    }
}

class StudentService {

    public void displayAll(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Student findTopper(List<Student> students) {
        if (students.isEmpty()) return null;
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.average > topper.average) topper = s;
        }
        return topper;
    }

    public void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.average, a.average));
    }

    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.id == id) return s;
        }
        return null;
    }

    public void printGrades(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students to show grades for.");
            return;
        }
        for (Student s : students) {
            System.out.println(s.name + " Grade: " + s.calculateGrade());
        }
    }
}

public class x {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = safeIntInput(scanner);

        if (n <= 0) {
            System.out.println("Invalid number of students. Exiting...");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter id:");
            int id = safeIntInput(scanner);
            System.out.println("Enter marks of 3 subjects:");
            int m1 = safeIntInput(scanner);
            int m2 = safeIntInput(scanner);
            int m3 = safeIntInput(scanner);

            students.add(new Student(name, id, m1, m2, m3));
        }

        System.out.println("\nAll Students:");
        service.displayAll(students);

        Student topper = service.findTopper(students);
        if (topper != null)
            System.out.printf("Topper: %s | Avg: %.2f%n", topper.name, topper.average);
        else
            System.out.println("No topper found (empty list).");

        System.out.println("\nSort by Average? y/n");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("Sorted List:");
            service.displayAll(students);
        }

        System.out.println("\nSearch student by id? y/n");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.println("Enter id:");
            int searchId = safeIntInput(scanner);
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found: " + found);
            else
                System.out.println("Student not found.");
        }

        System.out.println("\nCalculate grade? y/n");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        scanner.close();
    }

    public static int safeIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer: ");
                scanner.next();
            }
        }
    }
}
