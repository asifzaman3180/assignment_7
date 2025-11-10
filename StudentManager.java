import java.util.*;

/**
 * StudentManager class handles student data input, average calculation,
 * sorting, searching, and grade calculation for multiple students.
 */
public class StudentManager {

    /**
     * Main method – program entry point.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take number of students
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        // Arrays to store student data
        String[] names = new String[n];
        int[] ids = new int[n];
        int[] marks1 = new int[n];
        int[] marks2 = new int[n];
        int[] marks3 = new int[n];
        double[] averages = new double[n];

        double topAverage = 0;
        int topIndex = 0;

        // === Input Phase ===
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");

            System.out.print("Enter name: ");
            names[i] = scanner.next();

            System.out.print("Enter ID: ");
            ids[i] = scanner.nextInt();

            System.out.print("Enter marks of 3 subjects: ");
            marks1[i] = scanner.nextInt();
            marks2[i] = scanner.nextInt();
            marks3[i] = scanner.nextInt();

            // Calculate average marks
            averages[i] = (marks1[i] + marks2[i] + marks3[i]) / 3.0;

            // Track topper
            if (averages[i] > topAverage) {
                topAverage = averages[i];
                topIndex = i;
            }
        }

        // === Output Phase ===
        System.out.println("\n--- All Students ---");
        for (int i = 0; i < n; i++) {
            System.out.printf("Name: %s | ID: %d | Average: %.2f%n", names[i], ids[i], averages[i]);
        }

        System.out.printf("%nTopper: %s (Average: %.2f)%n", names[topIndex], averages[topIndex]);

        // === Sort by Average ===
        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(names, ids, marks1, marks2, marks3, averages);
            System.out.println("\n--- Sorted by Average (Descending) ---");
            for (int i = 0; i < n; i++) {
                System.out.printf("%s -> %.2f%n", names[i], averages[i]);
            }
        }

        // === Search by ID ===
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID to search: ");
            int searchId = scanner.nextInt();
            searchStudent(ids, names, averages, searchId);
        }

        // === Grade Calculation ===
        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.println("\n--- Grades ---");
            for (int i = 0; i < n; i++) {
                System.out.printf("%s -> Grade: %s%n", names[i], getGrade(averages[i]));
            }
        }

        System.out.println("\nProgram ended. Goodbye!");
        scanner.close();
    }

    /**
     * Sort students by their average marks in descending order.
     */
    private static void sortByAverage(String[] names, int[] ids, int[] m1, int[] m2, int[] m3, double[] avg) {
        for (int i = 0; i < avg.length - 1; i++) {
            for (int j = i + 1; j < avg.length; j++) {
                if (avg[i] < avg[j]) {
                    swap(names, i, j);
                    swap(ids, i, j);
                    swap(m1, i, j);
                    swap(m2, i, j);
                    swap(m3, i, j);
                    swap(avg, i, j);
                }
            }
        }
    }

    /**
     * Search for a student using their ID.
     */
    private static void searchStudent(int[] ids, String[] names, double[] averages, int searchId) {
        boolean found = false;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == searchId) {
                System.out.printf("Found: %s | Average: %.2f%n", names[i], averages[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found!");
        }
    }

    /**
     * Determine grade based on average marks.
     */
    private static String getGrade(double avg) {
        if (avg >= 80) return "A+";
        else if (avg >= 70) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else return "F";
    }

    // === Helper swap methods for arrays ===
    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
