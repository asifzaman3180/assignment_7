import java.util.*;

class Student {
    private String name;
    private int id;
    private int marks1, marks2, marks3;
    private double average;
    private String grade;


    public Student(String name, int id, int marks1, int marks2, int marks3) {
        this.name = name;
        this.id = id;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        calculateAverage();
    }

    private void calculateAverage() {
        this.average = (marks1 + marks2 + marks3) / 3.0;
    }

    public void calculateGrade() {
        if (average >= 80) grade = "A+";
        else if (average >= 70) grade = "A";
        else if (average >= 60) grade = "B";
        else if (average >= 50) grade = "C";
        else grade = "F";
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public double getAverage() { return average; }
    public String getGrade() { return grade; }

    public void display() {
        System.out.println("Name: " + name + " | ID: " + id + " | Average: " + average);
    }

    public void displayWithGrade() {
        System.out.println("Name: " + name + " | ID: " + id + " | Average: " + average + " | Grade: " + grade);
    }
}

public class StudentRecord {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = input.nextInt();
        List<Student> students = new ArrayList<>();

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


        System.out.println("\nAll Students:");
        for (Student s : students) {
            s.display();
        }

       
        Student topper = findTopper(students);
        System.out.println("\nTopper: " + topper.getName() + " | Average: " + topper.getAverage());


        System.out.print("\nSort by Average? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            sortByAverage(students);
            System.out.println("\nSorted List (Descending by Average):");
            for (Student s : students) {
                s.display();
            }
        }

  
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

  
    public static Student findTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    public static void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    public static Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }
}
