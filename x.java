import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int studentCount = scanner.nextInt();

        String[] names = new String[studentCount];
        int[] ids = new int[studentCount];
        int[] marks1 = new int[studentCount];
        int[] marks2 = new int[studentCount];
        int[] marks3 = new int[studentCount];
        double[] averages = new double[studentCount];

        double highestAverage = 0;
        int topperIndex = 0;

        // Input student data
        for (int i = 0; i < studentCount; i++) {
            System.out.print("Enter name: ");
            names[i] = scanner.next();

            System.out.print("Enter id: ");
            ids[i] = scanner.nextInt();

            System.out.print("Enter marks for 3 subjects: ");
            marks1[i] = scanner.nextInt();
            marks2[i] = scanner.nextInt();
            marks3[i] = scanner.nextInt();

            averages[i] = (marks1[i] + marks2[i] + marks3[i]) / 3.0;

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }

        // Display all students
        System.out.println("All Students:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Name: " + names[i] + " Id: " + ids[i] + " Avg: " + averages[i]);
        }

        // Display topper
        System.out.println("Topper: " + names[topperIndex] + " Avg: " + averages[topperIndex]);

        // Sort
        System.out.print("Sort by Average? (y/n): ");
        String sortChoice = scanner.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = i + 1; j < studentCount; j++) {
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

            System.out.println("Sorted List:");
            for (int i = 0; i < studentCount; i++) {
                System.out.println(names[i] + " " + averages[i]);
            }
        }

        // Search
        System.out.print("Search student by id? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter id: ");
            int searchId = scanner.nextInt();
            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
                if (ids[i] == searchId) {
                    System.out.println("Found: " + names[i] + " Avg: " + averages[i]);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Not found");
        }

        // Grades
        System.out.print("Calculate grade? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < studentCount; i++) {
                String grade;
                if (averages[i] >= 80) grade = "A+";
                else if (averages[i] >= 70) grade = "A";
                else if (averages[i] >= 60) grade = "B";
                else if (averages[i] >= 50) grade = "C";
                else grade = "F";

                System.out.println(names[i] + " Grade: " + grade);
            }
        }

        System.out.println("Bye!");
    }

    // helper to swap elements in arrays
    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
}
