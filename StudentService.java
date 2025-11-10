import java.util.*;

public class StudentService {

    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void printAllStudents() {
        System.out.println("\nAll Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Student findTopper() {
        Student topper = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > topper.getAverage()) {
                topper = s;
            }
        }
        return topper;
    }

    public void sortByAverageDescending() {
        students.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
    }

    public Student searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void printGrades() {
        for (Student s : students) {
            double avg = s.getAverage();
            String grade;
            if (avg >= 80) grade = "A+";
            else if (avg >= 70) grade = "A";
            else if (avg >= 60) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "F";
            System.out.println(s.getName() + " → Grade: " + grade);
        }
    }
}
