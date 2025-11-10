import java.util.*;

public class StudentRecords {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int noOfStudents = scanner.nextInt();

        String[] name = new String[noOfStudents];
        int[] studentId = new int[noOfStudents];
        int[] mark1 = new int[noOfStudents];
        int[] mark2 = new int[noOfStudents];
        int[] mark3 = new int[noOfStudents];
        double[] avg = new double[noOfStudents];

        int topIndex = inputStudents(scanner, noOfStudents, name, studentId, mark1, mark2, mark3, avg);
        displayAllStudents(noOfStudents, name, studentId, avg);
        displayTopper(name, avg, topIndex);

        sortByAverage(scanner, noOfStudents, name, studentId, mark1, mark2, mark3, avg);
        searchById(scanner, noOfStudents, name, studentId, avg);
        calculateGrades(scanner, noOfStudents, name, avg);

        System.out.println("\nBye!");
        scanner.close();
    }

    // Method 1: Input students and calculate average
    public static int inputStudents(Scanner scanner, int n, String[] name, int[] id, int[] m1, int[] m2, int[] m3, double[] avg) {
        double top = 0;
        int topIndex = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter name: ");
            name[i] = scanner.next();

            System.out.print("Enter ID: ");
            id[i] = scanner.nextInt();

            System.out.println("Enter marks of 3 subjects:");
            m1[i] = scanner.nextInt();
            m2[i] = scanner.nextInt();
            m3[i] = scanner.nextInt();

            avg[i] = (m1[i] + m2[i] + m3[i]) / 3.0;

            if (avg[i] > top) {
                top = avg[i];
                topIndex = i;
            }
        }
        return topIndex;
    }

    // Method 2: Display all students
    public static void displayAllStudents(int n, String[] name, int[] id, double[] avg) {
        System.out.println("\nAll Students:");
        for (int i = 0; i < n; i++) {
            System.out.println("Name: " + name[i] +
                               " | ID: " + id[i] +
                               " | Average: " + avg[i]);
        }
    }

    // Method 3: Display topper
    public static void displayTopper(String[] name, double[] avg, int topIndex) {
        System.out.println("\nTopper: " + name[topIndex] +
                           " | Average: " + avg[topIndex]);
    }

    // Method 4: Sort by average
    public static void sortByAverage(Scanner scanner, int n, String[] name, int[] id, int[] m1, int[] m2, int[] m3, double[] avg) {
        System.out.print("\nSort by Average? (y/n): ");
        String isSorted = scanner.next();

        if (isSorted.equalsIgnoreCase("y")) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (avg[i] < avg[j]) {
                        swap(name, i, j);
                        swap(id, i, j);
                        swap(m1, i, j);
                        swap(m2, i, j);
                        swap(m3, i, j);
                        swap(avg, i, j);
                    }
                }
            }

            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < n; i++) {
                System.out.println("Name: " + name[i] + " | Avg: " + avg[i]);
            }
        }
    }

    // Overloaded swap methods
    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Method 5: Search by student ID
    public static void searchById(Scanner scanner, int n, String[] name, int[] id, double[] avg) {
        System.out.print("\nSearch student by ID? (y/n): ");
        String isSearching = scanner.next();

        if (isSearching.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchedId = scanner.nextInt();
            boolean isFound = false;

            for (int i = 0; i < n; i++) {
                if (id[i] == searchedId) {
                    System.out.println("Found: " + name[i] +
                                       " | Avg: " + avg[i]);
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Student not found!");
            }
        }
    }

    // Method 6: Calculate grade
    public static void calculateGrades(Scanner scanner, int n, String[] name, double[] avg) {
        System.out.print("\nCalculate grades? (y/n): ");
        String calculateGrade = scanner.next();

        if (calculateGrade.equalsIgnoreCase("y")) {
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

                System.out.println(name[i] + " | Grade: " + grade);
            }
        }
    }
}
