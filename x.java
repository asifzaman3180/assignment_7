import java.util.*;

//Student class
class Student {
    String name;
    int id;
    int m1, m2, m3;
    double avg;

    public Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.avg = (m1 + m2 + m3) / 3.0;
    }
}

//studentservice class

class StudentService {

    
    public void displayAll(List<Student> students) {
        for (Student st : students) {
            System.out.println("Name: " + st.name + "  Id: " + st.id + "  Avg: " + st.avg);
        }
    }

    
    public Student findTopper(List<Student> students) {
        Student top = students.get(0);
        for (Student st : students) {
            if (st.avg > top.avg)
                top = st;
        }
        return top;
    }

    
    public void sortByAverage(List<Student> students) {
        students.sort((a, b) -> Double.compare(b.avg, a.avg));
    }

    
    public Student searchById(List<Student> students, int id) {
        for (Student st : students) {
            if (st.id == id)
                return st;
        }
        return null;
    }

   
    public void calculateGrades(List<Student> students) {
        for (Student st : students) {
            String grade;
            if (st.avg >= 80)
                grade = "A+";
            else if (st.avg >= 70)
                grade = "A";
            else if (st.avg >= 60)
                grade = "B";
            else if (st.avg >= 50)
                grade = "C";
            else
                grade = "F";
            System.out.println(st.name + "  Grade: " + grade);
        }
    }
}

//MainApp class
public class MainApp{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.print("Enter number of students: ");
        int n = s.nextInt();

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = s.next();
            System.out.print("Id: ");
            int id = s.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            int m1 = s.nextInt();
            int m2 = s.nextInt();
            int m3 = s.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        System.out.println("\nAll Students:");
        service.displayAll(students);

        Student top = service.findTopper(students);
        System.out.println("\nTopper: " + top.name + "  Avg: " + top.avg);

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = s.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            service.sortByAverage(students);
            System.out.println("\nSorted List:");
            service.displayAll(students);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = s.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int sid = s.nextInt();
            Student found = service.searchById(students, sid);
            if (found != null)
                System.out.println("Found: " + found.name + "  Avg: " + found.avg);
            else
                System.out.println("Not found!");
        }

        System.out.print("\nCalculate Grade? (y/n): ");
        String gradeChoice = s.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            service.calculateGrades(students);
        }

        System.out.println("\nBye!");
        s.close();
    }
}
