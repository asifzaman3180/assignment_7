import java.util.*;

class Student {
    String name;
    int id;
    int marks1;
    int marks2;
    int marks3;
    double average;

    Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.marks1 = m1;
        this.marks2 = m2;
        this.marks3 = m3;
        this.average = calculateAverage();
    }

    double calculateAverage() {
        return (marks1 + marks2 + marks3) / 3.0;
    }

    String getGrade() {
        if (average >= 80)
            return "A+";
        else if (average >= 70)
            return "A";
        else if (average >= 60)
            return "B";
        else if (average >= 50)
            return "C";
        else
            return "F";
    }

    public String toString() {
        return "Name: " + name + " ID: " + id + " Average: " + average;
    }
}

class StudentService {
    ArrayList<Student> students = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    void inputStudents() {
        System.out.print("Enter number of students: ");
        int total = scanner.nextInt();

        for (int i = 0; i < total; i++) {
            System.out.println("Enter name:");
            String name = scanner.next();
            System.out.println("Enter ID:");
            int id = scanner.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            int m1 = scanner.nextInt();
            int m2 = scanner.nextInt();
            int m3 = scanner.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }
    }

    void showAllStudents() {
        System.out.println("All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    Student findTopper() {
        if (students.isEmpty()) return null;
        Student top = students.get(0);
        for (Student s : students) {
            if (s.average > top.average) {
                top = s;
            }
        }
        return top;
    }

    void sortByAverage() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).average < students.get(j).average) {
                    Student temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
    }

    void searchById() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.id == id) {
                System.out.println("Found: " + s.name + " Average: " + s.average);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not found");
        }
    }

    void printGrades() {
        for (Student s : students) {
            System.out.println(s.name + " Grade: " + s.getGrade());
        }
    }
}

public class StudentApp {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        service.inputStudents();
        service.showAllStudents();

        Student topper = service.findTopper();
        if (topper != null) {
            System.out.println("Topper: " + topper.name + " Average: " + topper.average);
        }

        System.out.print("Sort by Average? (y/n): ");
        String sortChoice = service.scanner.next();
        if (sortChoice.equals("y")) {
            service.sortByAverage();
            System.out.println("Sorted List:");
            service.showAllStudents();
        }

        System.out.print("Search student by ID? (y/n): ");
        String searchChoice = service.scanner.next();
        if (searchChoice.equals("y")) {
            service.searchById();
        }

        System.out.print("Calculate grade? (y/n): ");
        String gradeChoice = service.scanner.next();
        if (gradeChoice.equals("y")) {
            service.printGrades();
        }

        System.out.println("Bye!");
    }
}

