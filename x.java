import java.util.*;

class Student {
    private String name;
    private int id;
    private int mark1, mark2, mark3;
    private double average;

    // Constructor
    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
    }

    // Calculate average
    public void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }

    // Getters
    public String getName() { return name; }
    public int getId() { return id; }
    public double getAverage() { return average; }

    // Display info
    public void displayInfo() {
        System.out.println("Name: " + name + " | ID: " + id + " | Average: " + average);
    }

    // Grade calculation
    public String getGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }
}

public class StudentRecords {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        inputStudents();
        displayAllStudents();
        displayTopper();
        sortByAverage();
        searchById();
        calculateGrades();
        System.out.println("\nBye!");
        scanner.close();
    }

    // 1️⃣ Input students
    public static void inputStudents() {
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter name: ");
            String name = scanner.next();

            System.out.print("Enter ID: ");
            int id = scanner.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            int mark1 = scanner.nextInt();
            int mark2 = scanner.nextInt();
            int mark3 = scanner.nextInt();

            students.add(new Student(name, id, mark1, mark2, mark3));
        }
    }

    // 2️⃣ Display all students
    public static void displayAllStudents() {
        System.out.println("\nAll Students:");
        for (Student s : students) {
            s.displayInfo();
        }
    }

    // 3️⃣ Display topper
    public static void displayTopper() {
        if (students.isEmpty()) return;
        Student topper = students.get(0);

        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }

        System.out.println("\nTopper: " + topper.getName() +
                           " | Average: " + topper.getAverage());
    }

    // 4️⃣ Sort by average
    public static void sortByAverage() {
        System.out.print("\nSort by Average? (y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("y")) return;

        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));

        System.out.println("\nSorted List (by Average):");
        for (Student s : students) {
            s.displayInfo();
        }
    }

    // 5️⃣ Search by ID
    public static void searchById() {
        System.out.print("\nSearch student by ID? (y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("y")) return;

        System.out.print("Enter ID: ");
        int searchId = scanner.nextInt();

        boolean found = false;
        for (Student s : students) {
            if (s.getId() == searchId) {
                System.out.println("Found: " + s.getName() +
                                   " | Avg: " + s.getAverage());
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Student not found!");
    }

    // 6️⃣ Calculate and show grades
    public static void calculateGrades() {
        System.out.print("\nCalculate grades? (y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("y")) return;

        System.out.println("\nGrades:");
        for (Student s : students) {
            System.out.println(s.getName() + " | Grade: " + s.getGrade());
        }
    }
}
