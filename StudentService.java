package mypackage;

import java.util.*;

public class StudentService {

    void calculateAverage(List<Student> students) {
        for (Student s : students) s.calculateAverage();
    }

    Student findTopper(List<Student> students) {
        Student topper = students.get(0);
        for (Student s : students)
            if (s.average > topper.average)
                topper = s;
        return topper;
    }

    void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.average, a.average));
    }

    Student searchById(List<Student> students, int id) {
        for (Student s : students)
            if (s.id == id) return s;
        return null;
    }

    void printGrades(List<Student> students) {
        for (Student s : students) {
            String grade;
            if (s.average >= 80) grade = "A+";
            else if (s.average >= 70) grade = "A";
            else if (s.average >= 60) grade = "B";
            else if (s.average >= 50) grade = "C";
            else grade = "F";
            System.out.println(s.name + " → " + grade);
        }
    }
}
