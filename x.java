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
        this.average = (mark1 + mark2 + mark3) / 3.0;
    }
}

class StudentService {
    public Student findTopper(List<Student> students) {
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

    public String getGrade(double average) {
        if (average >= 80) return "A+";
        else if (average >= 70) return "A";
        else if (average >= 60) return "B";
        else if (average >= 50) return "C";
        else return "F";
    }
}

class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Marks of 3 subjects: ");
            int mark1 = scanner.nextInt();
            int mark2 = scanner.nextInt();
            int mark3 = scanner.nextInt();
            students.add(new Student(name, id, mark1, mark2, mark3));
        }

        System.out.println("\nAll Students:");
        for (Student st : students) {
            System.out.println("Name: " + st.name + " | ID: " + st.id + " | Average: " + st.average);
        }

        Student topper = service.findTopper(students);
        System.out.println("\nTopper: " + topper.name + " | Average: " + topper.average);

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = scanner.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List (by average):");
            for (Student st : students) {
                System.out.println(st.name + " | Average: " + st.average);
            }
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            Student found = service.searchById(students, searchId);
            if (found != null)
                System.out.println("Found: " + found.name + " | Average: " + found.average);
            else
                System.out.println("Not found.");
        }

        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            for (Student st : students) {
                String grade = service.getGrade(st.average);
                System.out.println(st.name + " | Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}