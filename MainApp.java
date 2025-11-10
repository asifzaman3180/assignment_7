import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1));
            System.out.print("Name: "); String name = scanner.next();
            System.out.print("ID: "); int id = scanner.nextInt();
            System.out.print("Marks of 3 subjects: "); 
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            int m3 = scanner.nextInt();

            service.addStudent(new Student(name, id, m1, m2, m3));
        }

        service.printAllStudents();

        Student topper = service.findTopper();
        System.out.printf("\nTopper: %s | Avg: %.2f%n", topper.getName(), topper.getAverage());

        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.sortByAverageDescending();
            System.out.println("\nSorted List:");
            service.printAllStudents();
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int sid = scanner.nextInt();
            Student s = service.searchById(sid);
            if (s != null) System.out.println("Found: " + s);
            else System.out.println("Student not found.");
        }

        System.out.print("\nCalculate Grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            service.printGrades();
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
