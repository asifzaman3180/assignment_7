import java.util.*;

public class X {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        String[] names = new String[n];
        int[] ids = new int[n];
        int[] m1 = new int[n];
        int[] m2 = new int[n];
        int[] m3 = new int[n];
        double[] averages = new double[n];


        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1));

            System.out.print("Name: ");
            names[i] = scanner.next();

            System.out.print("ID: ");
            ids[i] = scanner.nextInt();

            System.out.print("Marks in 3 subjects: ");
            m1[i] = scanner.nextInt();
            m2[i] = scanner.nextInt();
            m3[i] = scanner.nextInt();

            averages[i] = calculateAverage(m1[i], m2[i], m3[i]);
        }


        printAllStudents(names, ids, averages);

        int topperIndex = findTopper(averages);
        System.out.printf("%nTopper: %s | Avg: %.2f%n", names[topperIndex], averages[topperIndex]);

        System.out.print("\nSort by Average? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            sortByAverage(names, ids, m1, m2, m3, averages);
            System.out.println("\nSorted List (by Average):");
            printAllStudents(names, ids, averages);
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int sid = scanner.nextInt();
            searchById(names, ids, averages, sid);
        }


        System.out.print("\nCalculate grades? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            printGrades(names, averages);
        }

        System.out.println("\nBye!");
        scanner.close();
    }


    private static double calculateAverage(int m1, int m2, int m3) {
        return (m1 + m2 + m3) / 3.0;
    }

    private static int findTopper(double[] averages) {
        int index = 0;
        double top = averages[0];
        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > top) {
                top = averages[i];
                index = i;
            }
        }
        return index;
    }

    private static void sortByAverage(String[] names, int[] ids,
                                      int[] m1, int[] m2, int[] m3, double[] averages) {
        for (int i = 0; i < averages.length - 1; i++) {
            for (int j = i + 1; j < averages.length; j++) {
                if (averages[i] < averages[j]) {
                    swap(names, i, j);
                    swap(ids, i, j);
                    swap(m1, i, j);
                    swap(m2, i, j);
                    swap(m3, i, j);
                    swap(averages, i, j);
                }
            }
        }
    }

    private static void searchById(String[] names, int[] ids, double[] averages, int searchId) {
        boolean found = false;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == searchId) {
                System.out.printf("Found: %s | Avg: %.2f%n", names[i], averages[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private static void printGrades(String[] names, double[] averages) {
        for (int i = 0; i < averages.length; i++) {
            String grade;
            double avg = averages[i];

            if (avg >= 80) grade = "A+";
            else if (avg >= 70) grade = "A";
            else if (avg >= 60) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "F";

            System.out.printf("%s → Grade: %s%n", names[i], grade);
        }
    }

    private static void printAllStudents(String[] names, int[] ids, double[] averages) {
        System.out.println("\nAll Students:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("Name: %s | ID: %d | Avg: %.2f%n", names[i], ids[i], averages[i]);
        }
    }


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
