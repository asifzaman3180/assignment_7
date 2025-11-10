import java.util.*;

class Student {
    String name;
    int id;
    int mark1, mark2, mark3;
    double average;

    public Student(String name, int id, int mark1, int mark2, int mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | ID: " + id + " | Average: " + average;
    }
}

class StudentService {
    public void calculateAverage(List<Student> students) {
        for (Student s : students) {
            int totalSubjects = 3;
            double totalMarks = s.mark1 + s.mark2 + s.mark3;
            if (totalSubjects > 0) {
                s.average = totalMarks / totalSubjects;
            } else {
                s.average = 0;
            }
        }
    }

    public Student findTopper(List<Student> students) {
        if (students.isEmpty()) return null;
        Student topStudent = students.get(0);
        for (Student s : students) {
            if (s.average > topStudent.average) {
                topStudent = s;
            }
        }
        return topStudent;
    }

    public void sortByAverage(List<Student> students) {
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

    public Student searchById(List<Student> students, int id) {
        for (Student s : students) {
            if (s.id == id) return s;
        }
        return null;
    }

    public void printGrades(List<Student> students) {
        for (Student s : students) {
            String grade;
            if (s.average >= 80) grade = "A+";
            else if (s.average >= 70) grade = "A";
            else if (s.average >= 60) grade = "B";
            else if (s.average >= 50) grade = "C";
            else grade = "F";
            System.out.println(s.name + " | Grade: " + grade);
        }
    }
}

class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = 0;
        try {
            numStudents = scanner.nextInt();
            if (numStudents <= 0) {
                System.out.println("Invalid number of students. Exiting.");
                return;
            }
        } 
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number. Exiting.");
            return;
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();

            int id;
            try {
                System.out.print("ID: ");
                id = scanner.nextInt();
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid ID. Skipping this student.");
                scanner.nextLine();
                continue;
            }

            int mark1, mark2, mark3;
            try {
                System.out.print("Marks of 3 subjects: ");
                mark1 = scanner.nextInt();
                mark2 = scanner.nextInt();
                mark3 = scanner.nextInt();
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid marks. Skipping this student.");
                scanner.nextLine();
                continue;
            }

            students.add(new Student(name, id, mark1, mark2, mark3));
        }

        if (students.isEmpty()) {
            System.out.println("No valid student data entered. Exiting.");
            return;
        }

        service.calculateAverage(students);

        System.out.println("\nAll Students:");
        for (Student st : students) {
            System.out.println(st);
        }

        Student topper = service.findTopper(students);
        if (topper != null)
            System.out.println("\nTopper: " + topper.name + " | Average: " + topper.average);
        else
            System.out.println("\nNo topper found (empty list).");

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = scanner.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List (by average):");
            for (Student st : students) {
                System.out.println(st);
            }
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            try {
                int searchId = scanner.nextInt();
                Student found = service.searchById(students, searchId);
                if (found != null)
                    System.out.println("Found: " + found);
                else
                    System.out.println("Student with ID " + searchId + " not found.");
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid ID input.");
            }
        }

        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            service.printGrades(students);
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
