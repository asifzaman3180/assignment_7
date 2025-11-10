import java.util.*;

public class Student_info {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number of students: ");
        int n = s.nextInt();

        String[] nm = new String[n];
        int[] id = new int[n];
        int[] m1 = new int[n];
        int[] m2 = new int[n];
        int[] m3 = new int[n];
        double[] avg = new double[n];

        inputStudentData(n, nm, id, m1, m2, m3, avg);

        displayAllStudents(n, nm, id, avg);

        int topIndex = findTopper(n, avg);
        System.out.println("Topper: " + nm[topIndex] + " Avg: " + avg[topIndex]);

        System.out.print("Sort by Average? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            System.out.println("Sorted List:");
            for (int i = 0; i < n; i++) {
                System.out.println(nm[i] + " " + avg[i]);
            }
        }

        System.out.print("Search student by id? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            searchById(n, id, nm, avg);
        }

        System.out.print("Calculate grade? (y/n): ");
        if (s.next().equalsIgnoreCase("y")) {
            calculateGrades(n, nm, avg);
        }

        System.out.println("Bye!");
    }

    // ------------------- Functions -------------------

    static void inputStudentData(int n, String[] nm, int[] id, int[] m1, int[] m2, int[] m3, double[] avg) {
        for (int i = 0; i < n; i++) {
            System.out.println("Enter name:");
            nm[i] = s.next();
            System.out.println("Enter id:");
            id[i] = s.nextInt();
            System.out.println("Enter marks of 3 subjects:");
            m1[i] = s.nextInt();
            m2[i] = s.nextInt();
            m3[i] = s.nextInt();
            avg[i] = (m1[i] + m2[i] + m3[i]) / 3.0;
        }
    }

    static void displayAllStudents(int n, String[] nm, int[] id, double[] avg) {
        System.out.println("\nAll Students:");
        for (int i = 0; i < n; i++) {
            System.out.println("Name: " + nm[i] + " Id: " + id[i] + " Avg: " + avg[i]);
        }
    }

    static int findTopper(int n, double[] avg) {
        double top = avg[0];
        int topIndex = 0;
        for (int i = 1; i < n; i++) {
            if (avg[i] > top) {
                top = avg[i];
                topIndex = i;
            }
        }
        return topIndex;
    }

    static void searchById(int n, int[] id, String[] nm, double[] avg) {
        System.out.print("Enter id: ");
        int sid = s.nextInt();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (id[i] == sid) {
                System.out.println("Found: " + nm[i] + " Avg: " + avg[i]);
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Not found");
    }

    static void calculateGrades(int n, String[] nm, double[] avg) {
        System.out.println("\nGrades:");
        for (int i = 0; i < n; i++) {
            String grade;
            if (avg[i] >= 80)
                grade = "A+";
            else if (avg[i] >= 70)
                grade = "A";
            else if (avg[i] >= 60)
                grade = "B";
            else if (avg[i] >= 50)
                grade = "C";
            else
                grade = "F";
            System.out.println(nm[i] + " Grade: " + grade);
        }
    }
}
