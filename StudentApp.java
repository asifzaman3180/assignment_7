import java.util.*;

//Student class - holds info about one student.
//Name, ID, marks, average, and grade calculation.
class Student {
    String name;
    int id;
    int marks1;
    int marks2;
    int marks3;
    double average;

    // Constructor to set student details.
    Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.marks1 = m1;
        this.marks2 = m2;
        this.marks3 = m3;
        this.average = calculateAverage();
    }

    //Calculate the average marks.
    double calculateAverage() {
        return (marks1 + marks2 + marks3) / 3.0;
    }

    //Return grade according to average.
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

    //toString method for printing student info.
    public String toString() {
        return "Name: " + name + " ID: " + id + " Average: " + average;
    }
}

//StudentService class handles all student-related operations.
class StudentService {
    ArrayList<Student> students = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // Take student details as input from user.
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

    //Show all student details.
    void showAllStudents() {
        System.out.println("All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    //Find the student with highest average.
     
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

    // Sort students by average marks (descending order).

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

    //Search a student by ID.
     
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

    //Print grades for all students.
    void printGrades() {
        for (Student s : students) {
            System.out.println(s.name + " Grade: " + s.getGrade());
        }
    }
}

//Main class to run the whole application.

public class StudentApp {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        // taking all student data
        service.inputStudents();

        // show all students
        service.showAllStudents();

        // show topper info
        Student topper = service.findTopper();
        if (topper != null) {
            System.out.println("Topper: " + topper.name + " Average: " + topper.average);
        }

        // sorting students by average
        System.out.print("Sort by Average? (y/n): ");
        String sortChoice = service.scanner.next();
        if (sortChoice.equals("y")) {
            service.sortByAverage();
            System.out.println("Sorted List:");
            service.showAllStudents();
        }

        // search by ID
        System.out.print("Search student by ID? (y/n): ");
        String searchChoice = service.scanner.next();
        if (searchChoice.equals("y")) {
            service.searchById();
        }

        // show grades
        System.out.print("Calculate grade? (y/n): ");
        String gradeChoice = service.scanner.next();
        if (gradeChoice.equals("y")) {
            service.printGrades();
        }

        System.out.println("Bye!");
    }
}
