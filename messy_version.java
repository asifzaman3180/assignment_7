import java.util.*;

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

    public String getGrade() {
        if (avg >= 80) return "A+";
        else if (avg >= 70) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else return "F";
    }

    @Override
    public String toString() {
        return "Name: " + name + " Id: " + id + " Avg: " + String.format("%.2f", avg);
    }
}

public class X {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Enter name: ");
            String name = s.next();
            System.out.print("Enter id: ");
            int id = s.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            int m1 = s.nextInt();
            int m2 = s.nextInt();
            int m3 = s.nextInt();
            students.add(new Student(name, id, m1, m2, m3));
        }

        // Display all students
        System.out.println("\nAll Students:");
        for (Student st : students) {
            System.out.println(st);
        }

        // Find topper
        Student topper = students.get(0);
        for (Student st : students) {
            if (st.avg > topper.avg) topper = st;
        }
        System.out.println("\nTopper: " + topper.name + " Avg: " + topper.avg);

        // Sort by average
        System.out.print("\nSort by Average? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            students.sort((a, b) -> Double.compare(b.avg, a.avg));
            System.out.println("Sorted List:");
            for (Student st : students) {
                System.out.println(st.name + " " + st.avg);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by id? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            System.out.print("Enter id: ");
            int sid = s.nextInt();
            boolean found = false;
            for (Student st : students) {
                if (st.id == sid) {
                    System.out.println("Found: " + st.name + " Avg: " + st.avg);
                    found = true;
                }
            }
            if (!found) System.out.println("Not found.");
        }

        // Calculate grade
        System.out.print("\nCalculate grade? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            for (Student st : students) {
                System.out.println(st.name + " Grade: " + st.getGrade());
            }
        }

        System.out.println("\nBye!");
        s.close();
    }
}

