import java.util.*;

class Student {
    String name;
    int id;
    int m1, m2, m3;
    double avg;

    Student(String name, int id, int m1, int m2, int m3) {
        this.name = name;
        this.id = id;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.avg = (m1 + m2 + m3) / 3.0;
    }
}

public class x {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("Enter no. of students: ");
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            String name = s.next();
            System.out.println("Enter id:");
            int id = s.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            int m1 = s.nextInt();
            int m2 = s.nextInt();
            int m3 = s.nextInt();

            students.add(new Student(name, id, m1, m2, m3));
        }

        // Find topper
        double top = 0;
        Student topper = null;
        for (Student st : students) {
            if (st.avg > top) {
                top = st.avg;
                topper = st;
            }
        }

        System.out.println("All Students:");
        for (Student st : students) {
            System.out.println("Name:" + st.name + " Id:" + st.id + " Avg:" + st.avg);
        }

        if (topper != null)
            System.out.println("Topper: " + topper.name + " Avg:" + topper.avg);

        System.out.println("Sort by Average? y/n");
        String c = s.next();
        if (c.equalsIgnoreCase("y")) {
            students.sort((a, b) -> Double.compare(b.avg, a.avg));
            System.out.println("Sorted List:");
            for (Student st : students) {
                System.out.println(st.name + " " + st.avg);
            }
        }

        System.out.println("Search student by id? y/n");
        String d = s.next();
        if (d.equalsIgnoreCase("y")) {
            System.out.println("Enter id:");
            int sid = s.nextInt();
            boolean found = false;
            for (Student st : students) {
                if (st.id == sid) {
                    System.out.println("Found: " + st.name + " Avg:" + st.avg);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Not found");
        }

        System.out.println("Calculate grade? y/n");
        String g = s.next();
        if (g.equalsIgnoreCase("y")) {
            for (Student st : students) {
                String grade;
                if (st.avg >= 80) grade = "A+";
                else if (st.avg >= 70) grade = "A";
                else if (st.avg >= 60) grade = "B";
                else if (st.avg >= 50) grade = "C";
                else grade = "F";
                System.out.println(st.name + " Grade:" + grade);
            }
        }

        System.out.println("Bye!");
        s.close();
    }
}
