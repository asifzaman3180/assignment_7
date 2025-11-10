import java.util.*;

public class StudentManager {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();

        String[] names = new String[numStudents];
        int[] ids = new int[numStudents];
        int[] subject1 = new int[numStudents];
        int[] subject2 = new int[numStudents];
        int[] subject3 = new int[numStudents];
        double[] averages = new double[numStudents];

        inputStudents(names, ids, subject1, subject2, subject3, averages);

        displayAllStudents(names, ids, averages);

        int topperIndex = findTopper(averages);
        System.out.println("\nTopper: " + names[topperIndex] + " | Average: " + averages[topperIndex]);

        if (askYesNo("Sort by Average?")) {
            sortByAverage(names, ids, subject1, subject2, subject3, averages);
            displaySortedList(names, averages);
        }

        if (askYesNo("Search student by ID?")) {
            searchStudentById(names, ids, averages);
        }

        if (askYesNo("Calculate grade for all students?")) {
            calculateGrades(names, averages);
        }

        System.out.println("\nBye!");
    }

    // ---- Method Definitions ---- //

    // 1. Input
    static void inputStudents(String[] names, int[] ids, int[] s1, int[] s2, int[] s3, double[] avg) {
        double highestAverage = 0;

        for (int i = 0; i < names.length; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter name: ");
            names[i] = scanner.next();

            System.out.print("Enter ID: ");
            ids[i] = scanner.nextInt();

            System.out.print("Enter marks of 3 subjects: ");
            s1[i] = scanner.nextInt();
            s2[i] = scanner.nextInt();
            s3[i] = scanner.nextInt();

            avg[i] = (s1[i] + s2[i] + s3[i]) / 3.0;
        }
    }

    // 2. Display all students
    static void displayAllStudents(String[] names, int[] ids, double[] averages) {
        System.out.println("\nAll Students:");
        for (int i = 0; i < names.length; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Average: " + averages[i]);
        }
    }

    // 3. Find topper index
    static int findTopper(double[] averages) {
        int topperIndex = 0;
        double highestAverage = averages[0];
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }
        return topperIndex;
    }

    // 4. Sort by average (descending)
    static void sortByAverage(String[] names, int[] ids, int[] s1, int[] s2, int[] s3, double[] averages) {
        for (int i = 0; i < averages.length - 1; i++) {
            for (int j = i + 1; j < averages.length; j++) {
                if (averages[i] < averages[j]) {
                    swap(names, i, j);
                    swap(ids, i, j);
                    swap(s1, i, j);
                    swap(s2, i, j);
                    swap(s3, i, j);
                    swap(averages, i, j);
                }
            }
        }
    }

    // 5. Display sorted list
    static void displaySortedList(String[] names, double[] averages) {
        System.out.println("\nSorted List (by Average):");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " | Average: " + averages[i]);
        }
    }

    // 6. Search by ID
    static void searchStudentById(String[] names, int[] ids, double[] averages) {
        System.out.print("Enter ID to search: ");
        int searchId = scanner.nextInt();
        boolean found = false;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == searchId) {
                System.out.println("Found: " + names[i] + " | Average: " + averages[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // 7. Calculate grades
    static void calculateGrades(String[] names, double[] averages) {
        System.out.println("\nGrades:");
        for (int i = 0; i < names.length; i++) {
            String grade;
            if (averages[i] >= 80)
                grade = "A+";
            else if (averages[i] >= 70)
                grade = "A";
            else if (averages[i] >= 60)
                grade = "B";
            else if (averages[i] >= 50)
                grade = "C";
            else
                grade = "F";

            System.out.println(names[i] + " | Grade: " + grade);
        }
    }

    // 8. Ask yes/no question
    static boolean askYesNo(String message) {
        System.out.print("\n" + message + " (y/n): ");
        String choice = scanner.next();
        return choice.equalsIgnoreCase("y");
    }

    // 9. Swap overloads
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
