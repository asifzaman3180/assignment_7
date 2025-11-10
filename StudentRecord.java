import java.util.*;

// class for student info
class Student {
    private String name;
    private int id;
    private int marks1, marks2, marks3;
    private double average;
    private String grade;

    // constructor for student
    public Student(String name, int id, int marks1, int marks2, int marks3) {
        this.name = name;
        this.id = id;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        calculateAverage();
    }

    // calc avg marks
    private void calculateAverage() {
        this.average = (marks1 + marks2 + marks3) / 3.0;
    }

    // calc grade depend on avg
    public void calculateGrade() {
        if (average >= 80) grade = "A+";
        else if (average >= 70) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 50) grade = "C";
        else grade = "F";
    }

    // getters
    public String getName() { return name; }
    public int getId() { return id; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }

    // show basic info
    public void display() {
        System.out.println("Name: " + name + " | ID: " + id + " | Average: " + average);
    }

    // show info with grade
    public void displayWithGrade() {
        System.out.println("Name: " + name + " | ID: " + id + " | Average: " + average + " | Grade: " + grade);
    }
}

// main class for record system
public class StudentRecord {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = input.nextInt();

        List<Student> students = new ArrayList<>();

        // input for each student
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = input.next();

            System.out.print("ID: ");
            int id = input.nextInt();

            System.out.print("Marks of 3 subjects: ");
            int m1 = input.nextInt();
            int m2 = input.nextInt();
            int m3 = input.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        // print all
        System.out.println("\nAll Students:");
        for (Student s : students) {
            s.display();
        }

        // find topper
        Student topper = findTopper(students);
        System.out.println("\nTopper: " + topper.getName() + " | Average: " + topper.getAverage());

        // ask for sorting
        System.out.print("\nSort by Average? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            sortByAverage(students);
            System.out.println("\nSorted List (Descending by Average):");
            for (Student s : students) {
                s.display();
            }
        }

        // ask for search
        System.out.print("\nSearch student by ID? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = input.nextInt();
            Student found = searchById(students, searchId);
            if (found != null) {
                System.out.println("Found:");
                found.display();
            } else {
                System.out.println("Student not found.");
            }
        }

        // ask for grade calc
        System.out.print("\nCalculate grades? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            for (Student s : students) {
                s.calculateGrade();
                s.displayWithGrade();
            }
        }

        System.out.println("\nBye!");
        input.close();
    }

    // find topper student
    public static Student findTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    // sort student by avg
    public static void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    // search student by id
    public static Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }
}
