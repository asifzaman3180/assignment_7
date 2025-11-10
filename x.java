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
        return (mark1 + mark2 + mark3) / 3.0;
    }

    public String calculateGrade() {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }
}

class StudentService {

    public void displayAll(List<Student> students) {
        for (Student s : students) {
            System.out.printf("Name: %s Id: %d Avg: %.2f%n", s.name, s.id, s.average);
        }
    }

    public Student findTopper(List<Student> students) {
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
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter id:");
            int id = scanner.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            int m3 = scanner.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        System.out.println("All Students:");
        service.displayAll(students);

        Student topper = service.findTopper(students);
        System.out.printf("Topper: %s Avg: %.2f%n", topper.name, topper.average);

        System.out.println("Sort by Average? y/n");
        String choice = scanner.next();
        if (choice.equals("y")) {
            service.sortByAverage(students);
            System.out.println("Sorted List:");
            service.displayAll(students);
        }

        System.out.println("Search student by id?");
        String searchChoice = scanner.next();
        if (searchChoice.equals("y")) {
            System.out.println("Enter id:");
            int searchId = scanner.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.printf("Found: %s Avg: %.2f%n", found.name, found.average);
            else
                System.out.println("Not found");
        }

        System.out.println("Calculate grade?");
        String gradeChoice = scanner.next();
        if (gradeChoice.equals("y")) {
            service.printGrades(students);
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
