import java.util.*;

public class StudentManager {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number of students: ");
        int studentCount = scanner.nextInt();

        String[] names = new String[studentCount];
        int[] ids = new int[studentCount];
        int[] subject1 = new int[studentCount];
        int[] subject2 = new int[studentCount];
        int[] subject3 = new int[studentCount];
        double[] averages = new double[studentCount];

        // Input student details
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            names[i] = scanner.next();
            System.out.print("ID: ");
            ids[i] = scanner.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            subject1[i] = scanner.nextInt();
            subject2[i] = scanner.nextInt();
            subject3[i] = scanner.nextInt();
        }

        // Calculate averages
        calculateAverage(subject1, subject2, subject3, averages);

        // Display all students
        System.out.println("\nAll Students:");
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("Name: %-10s | ID: %-5d | Average: %.2f%n", names[i], ids[i], averages[i]);
        }

        // Find topper
        findTopper(names, averages);

        // Sort by average
        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(names, ids, subject1, subject2, subject3, averages);
            System.out.println("\nSorted by Average (Descending):");
            for (int i = 0; i < studentCount; i++) {
                System.out.printf("%-10s | Avg: %.2f%n", names[i], averages[i]);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            searchById(names, ids, averages);
        }

        // Print grades
        System.out.print("\nPrint grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            printGrades(names, averages);
        }

        System.out.println("\nBye!");
        scanner.close();
    }

    // -------------------------------------------------
    // METHODS
    // -------------------------------------------------

    static void calculateAverage(int[] s1, int[] s2, int[] s3, double[] avg) {
        for (int i = 0; i < avg.length; i++) {
            avg[i] = (s1[i] + s2[i] + s3[i]) / 3.0;
        }
    }

    static void findTopper(String[] names, double[] avg) {
        double highest = avg[0];
        int topperIndex = 0;
        for (int i = 1; i < avg.length; i++) {
            if (avg[i] > highest) {
                highest = avg[i];
                topperIndex = i;
            }
        }
        System.out.println("\nTopper: " + names[topperIndex] + " | Average: " + highest);
    }

    static void sortByAverage(String[] names, int[] ids, int[] s1, int[] s2, int[] s3, double[] avg) {
        for (int i = 0; i < avg.length - 1; i++) {
            for (int j = i + 1; j < avg.length; j++) {
                if (avg[i] < avg[j]) {
                    swap(names, i, j);
                    swap(ids, i, j);
                    swap(s1, i, j);
                    swap(s2, i, j);
                    swap(s3, i, j);
                    swap(avg, i, j);
                }
            }
        }
    }

    static void searchById(String[] names, int[] ids, double[] avg) {
        System.out.print("Enter ID to search: ");
        int searchId = scanner.nextInt();
        boolean found = false;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == searchId) {
                System.out.printf("Found: %-10s | Average: %.2f%n", names[i], avg[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    static void printGrades(String[] names, double[] avg) {
        System.out.println("\nGrades:");
        for (int i = 0; i < names.length; i++) {
            String grade;
            if (avg[i] >= 80) grade = "A+";
            else if (avg[i] >= 70) grade = "A";
            else if (avg[i] >= 60) grade = "B";
            else if (avg[i] >= 50) grade = "C";
            else grade = "F";

            System.out.println(names[i] + " | Grade: " + grade);
        }
    }

    // -------------------------------------------------
    // SWAP OVERLOADS
    // -------------------------------------------------
    static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
