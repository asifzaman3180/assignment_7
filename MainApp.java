package mypackage;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = in.next();
            System.out.print("ID: ");
            int id = in.nextInt();
            System.out.print("Marks (3 subjects): ");
            int m1 = in.nextInt(), m2 = in.nextInt(), m3 = in.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        service.calculateAverage(students);

        System.out.println("\nAll Students:");
        for (Student s : students) System.out.println(s);

        Student topper = service.findTopper(students);
        System.out.println("\nTopper: " + topper.name + " (Avg: " + topper.average + ")");

        System.out.print("\nSort by Average? (y/n): ");
        if (in.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List:");
            for (Student s : students) System.out.println(s);
        }

        System.out.print("\nSearch by ID? (y/n): ");
        if (in.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = in.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null) System.out.println("Found: " + found);
            else System.out.println("Student not found!");
        }

        System.out.print("\nPrint Grades? (y/n): ");
        if (in.next().equalsIgnoreCase("y")) {
            service.printGrades(students);
        }

        System.out.println("\nProgram Ended. Bye!");
        in.close();
    }
}
