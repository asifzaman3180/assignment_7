import java.util.*;

/**
 * Full OOP Student Management in one file
 */
public class StudentApp {

    // ---------------- Student class ----------------
    static class Student {
        private String name;
        private int id;
        private int m1, m2, m3;
        private double average;

        public Student(String name, int id, int m1, int m2, int m3) {
            this.name = name;
            this.id = id;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
            calculateAverage();
        }

        public void calculateAverage() {
            this.average = (m1 + m2 + m3) / 3.0;
        }

        public String getName() { return name; }
        public int getId() { return id; }
        public double getAverage() { return average; }

        @Override
        public String toString() {
            return String.format("Name: %s Id: %d Avg: %.2f", name, id, average);
        }
    }

    // ---------------- StudentService class ----------------
    static class StudentService {

        public Optional<Student> findTopper(List<Student> students) {
            if (students == null || students.isEmpty()) return Optional.empty();
            Student topper = students.get(0);
            for (Student s : students) {
                if (s.getAverage() > topper.getAverage()) {
                    topper = s;
                }
            }
            return Optional.of(topper);
        }

        public void sortByAverage(List<Student> students) {
            if (students == null) return;
            students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
        }

        public Optional<Student> searchById(List<Student> students, int id) {
            if (students == null) return Optional.empty();
            for (Student s : students) {
                if (s.getId() == id) return Optional.of(s);
            }
            return Optional.empty();
        }

        public void printGrades(List<Student> students) {
            if (students == null || students.isEmpty()) {
                System.out.println("No students to grade.");
                return;
            }

            for (Student s : students) {
                String grade;
                double avg = s.getAverage();
                if (avg >= 80) grade = "A+";
                else if (avg >= 70) grade = "A";
                else if (avg >= 60) grade = "B";
                else if (avg >= 50) grade = "C";
                else grade = "F";

                System.out.println(s.getName() + " Grade: " + grade);
            }
        }
    }

    // ---------------- MainApp code ----------------
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service = new StudentService();

    public static void main(String[] args) {
        System.out.println("Welcome to the Student Manager");

        int n = readInt("Enter number of students: ", 0, Integer.MAX_VALUE);
        if (n == 0) {
            System.out.println("No students to process. Exiting.");
            return;
        }

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("Student #%d%n", i + 1);
            String name = readString("Enter name: ");
            int id = readInt("Enter id (positive integer): ", 1, Integer.MAX_VALUE);
            int m1 = readInt("Enter marks for subject 1 (0-100): ", 0, 100);
            int m2 = readInt("Enter marks for subject 2 (0-100): ", 0, 100);
            int m3 = readInt("Enter marks for subject 3 (0-100): ", 0, 100);
            students.add(new Student(name, id, m1, m2, m3));
        }

        System.out.println("\nAll Students:");
        students.forEach(System.out::println);

        service.findTopper(students).ifPresentOrElse(
                t -> System.out.printf("\nTopper: %s Avg: %.2f%n", t.getName(), t.getAverage()),
                () -> System.out.println("No topper found")
        );

        if (yesNo("Sort by Average? (y/n): ")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List:");
            students.forEach(s -> System.out.printf("%s %.2f%n", s.getName(), s.getAverage()));
        }

        if (yesNo("\nSearch student by id? (y/n): ")) {
            int searchId = readInt("Enter id to search: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
            service.searchById(students, searchId).ifPresentOrElse(
                    s -> System.out.printf("Found: %s Avg: %.2f%n", s.getName(), s.getAverage()),
                    () -> System.out.println("Not found")
            );
        }

        if (yesNo("\nCalculate grade? (y/n): ")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nBye!");
    }

    // ---------------- Input Helpers ----------------
    private static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.printf("Enter a number between %d and %d.%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private static boolean yesNo(String prompt) {
        System.out.print(prompt);
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!(answer.equals("y") || answer.equals("n") || answer.equals("yes") || answer.equals("no"))) {
            System.out.print("Please enter 'y' or 'n': ");
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer.startsWith("y");
    }
}
