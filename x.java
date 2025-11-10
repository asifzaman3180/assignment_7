import java.util.*;

/**
 * Represents a student with name, ID, marks, average, and grade.
 */
class Student {
    private String name;
    private int id;
    private int mark1, mark2, mark3;
    private double average;
    private String grade;

    /**
     * Constructs a Student with name, ID, and marks.
     *
     * @param name  Name of the student
     * @param id    ID of the student
     * @param mark1 Marks in subject 1
     * @param mark2 Marks in subject 2
     * @param mark3 Marks in subject 3
     */
    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
        this.grade = "";
    }

    /**
     * Calculates the average marks of the student.
     */
    public void calculateAverage() {
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }

    /**
     * Calculates the grade of the student based on average.
     */
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

    /**
     * Returns a string representation of the student.
     *
     * @return Student details as a string
     */
    @Override
    public String toString() {
        return "Name: " + name + " ID: " + id + " Avg: " + average;
    }
}

/**
 * Handles operations on a list of students, such as sorting, searching, and grading.
 */
class StudentService {
    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    /**
     * Adds a student to the list.
     *
     * @param s Student to add
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    /**
     * Prints details of all students.
     */
    public void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Finds the student with the highest average.
     *
     * @return Topper student, or null if no students
     */
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

    /**
     * Sorts the students by average in descending order.
     */
    public void sortByAverage() {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    /**
     * Searches for a student by ID.
     *
     * @param id ID to search for
     * @return Student if found, else null
     */
    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    /**
     * Prints grades of all students after calculating them.
     */
    public void printGrades() {
        for (Student s : students) {
            s.calculateGrade();
            System.out.println(s.getName() + " Grade: " + s.getGrade());
        }
    }

    /**
     * Checks if there are any students in the list.
     *
     * @return true if students exist, false otherwise
     */
    public boolean hasStudents() {
        return !students.isEmpty();
    }
}

/**
 * Main application class that handles user input/output.
 */
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

    /**
     * Reads an integer safely from Scanner, defaults to 0 if invalid.
     *
     * @param scanner Scanner object
     * @return integer input or 0 if invalid
     */
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
