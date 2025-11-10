import java.util.*;

public class StudentManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        String[] names = new String[n];
        int[] ids = new int[n];
        int[] marks1 = new int[n];
        int[] marks2 = new int[n];
        int[] marks3 = new int[n];
        double[] averages = new double[n];

        double topAverage = 0;
        int topIndex = 0;

        // Input student details
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

            averages[i] = (marks1[i] + marks2[i] + marks3[i]) / 3.0;

            if (averages[i] > topAverage) {
                topAverage = averages[i];
                topIndex = i;
            }
        }

        // Display all students
        System.out.println("\n--- All Students ---");
        for (int i = 0; i < n; i++) {
            System.out.printf("Name: %s | ID: %d | Average: %.2f%n", names[i], ids[i], averages[i]);
        }

        // Display topper
        System.out.printf("%nTopper: %s (Average: %.2f)%n", names[topIndex], averages[topIndex]);

        // Sort by average
        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (averages[i] < averages[j]) {
                        swap(names, i, j);
                        swap(ids, i, j);
                        swap(marks1, i, j);
                        swap(marks2, i, j);
                        swap(marks3, i, j);
                        swap(averages, i, j);
                    }
                }
            }

            System.out.println("\n--- Sorted by Average (Descending) ---");
            for (int i = 0; i < n; i++) {
                System.out.printf("%s -> %.2f%n", names[i], averages[i]);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID to search: ");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < n; i++) {
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

        // Calculate grade
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

    // Swap overloads for arrays
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

    // Determine grade based on average marks
    private static String getGrade(double avg) {
        if (avg >= 80) return "A+";
        else if (avg >= 70) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else return "F";
    }
}
