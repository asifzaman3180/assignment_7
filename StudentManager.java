import java.util.*;

public class StudentManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        String[] names = new String[n];
        int[] ids = new int[n];
        double[] averages = new double[n];
        int[][] marks = new int[n][3];

        inputStudents(scanner, n, names, ids, marks, averages);
        displayAllStudents(n, names, ids, averages);

        int topIndex = findTopper(averages);
        System.out.println("\nTopper: " + names[topIndex] + " | Average: " + averages[topIndex]);

        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(n, names, ids, averages);
            displayAllStudents(n, names, ids, averages);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            searchById(n, id, names, ids, averages);
        }

        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            printGrades(n, names, averages);
        }

        System.out.println("\nBye!");
        scanner.close();
    }

    static void inputStudents(Scanner sc, int n, String[] names, int[] ids, int[][] marks, double[] averages) {
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            names[i] = sc.next();

            System.out.print("ID: ");
            ids[i] = sc.nextInt();

            System.out.print("Enter marks of 3 subjects: ");
            marks[i][0] = sc.nextInt();
            marks[i][1] = sc.nextInt();
            marks[i][2] = sc.nextInt();

            averages[i] = calculateAverage(marks[i]);
        }
    }

    static double calculateAverage(int[] marks) {
        return (marks[0] + marks[1] + marks[2]) / 3.0;
    }

    static void displayAllStudents(int n, String[] names, int[] ids, double[] averages) {
        System.out.println("\nAll Students:");
        for (int i = 0; i < n; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Average: " + averages[i]);
        }
    }

    static int findTopper(double[] averages) {
        int topIndex = 0;
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > averages[topIndex]) topIndex = i;
        }
        return topIndex;
    }

    static void sortByAverage(int n, String[] names, int[] ids, double[] averages) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (averages[i] < averages[j]) {
                    // swap
                    String tmpName = names[i];
                    names[i] = names[j];
                    names[j] = tmpName;

                    int tmpId = ids[i];
                    ids[i] = ids[j];
                    ids[j] = tmpId;

                    double tmpAvg = averages[i];
                    averages[i] = averages[j];
                    averages[j] = tmpAvg;
                }
            }
        }
    }

    static void searchById(int n, int id, String[] names, int[] ids, double[] averages) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ids[i] == id) {
                System.out.println("Found: " + names[i] + " | Average: " + averages[i]);
                found = true;
            }
        }
        if (!found) System.out.println("Student not found!");
    }

    static void printGrades(int n, String[] names, double[] averages) {
        for (int i = 0; i < n; i++) {
            double avg = averages[i];
            String grade;
            if (avg >= 80) grade = "A+";
            else if (avg >= 70) grade = "A";
            else if (avg >= 60) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "F";
            System.out.println(names[i] + " | Grade: " + grade);
        }
    }
}
