import java.util.*;


class Student {
    String name;
    int id;
    int mark1, mark2, mark3;
    double average;

    Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        calculateAverage();
    }

    
    void calculateAverage() {
        average = (mark1 + mark2 + mark3) / 3.0;
    }
}


class StudentService {

    // Find the topper student
    Student findTopper(ArrayList<Student> students) {
        if (students.isEmpty()) return null;
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.average > topper.average) {
                topper = s;
            }
        }
        return topper;
    }

    void sortByAverage(ArrayList<Student> students) {
        Collections.sort(students, (a, b) -> Double.compare(b.average, a.average));
    }

    
    Student searchById(ArrayList<Student> students, int searchId) {
        for (Student s : students) {
            if (s.id == searchId) {
                return s;
            }
        }
        return null;
    }

    
    void printGrades(ArrayList<Student> students) {
        for (Student s : students) {
            String grade;
            if (s.average >= 80) grade = "A+";
            else if (s.average >= 70) grade = "A";
            else if (s.average >= 60) grade = "B";
            else if (s.average >= 50) grade = "C";
            else grade = "F";
            System.out.println(s.name + " → Grade: " + grade);
        }
    }
}


public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Enter details for student " + (i + 1) + " ---");
            System.out.print("Name: ");
            String name = sc.next();
            System.out.print("ID: ");
            int id = sc.nextInt();
            System.out.print("Enter marks for 3 subjects: ");
            int m1 = sc.nextInt(), m2 = sc.nextInt(), m3 = sc.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println("Name: " + s.name + " | ID: " + s.id + " | Avg: " + s.average);
        }

        
        Student topper = service.findTopper(students);
        if (topper != null)
            System.out.println("\nTopper: " + topper.name + " | Avg: " + topper.average);

        
        System.out.print("\nSort by Average? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List by Average:");
            for (Student s : students) {
                System.out.println(s.name + " | Avg: " + s.average);
            }
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = sc.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found: " + found.name + " | Avg: " + found.average);
            else
                System.out.println("Student not found.");
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        sc.close();
    }
}
