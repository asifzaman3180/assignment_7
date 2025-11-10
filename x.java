import java.util.*;
public class X {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numberOfStudents = scanner.nextInt();


        String[] names = new String[numberOfStudents];
        int[] studentIds = new int[numberOfStudents];
        int[] marks1 = new int[numberOfStudents];
        int[] marks2 = new int[numberOfStudents];
        int[] marks3 = new int[numberOfStudents];
        double[] averages = new double[numberOfStudents];

        double highestAverage = 0;
        int topperIndex = 0;


        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1));

            System.out.print("Name: ");
            names[i] = scanner.next();

            System.out.print("ID: ");
            studentIds[i] = scanner.nextInt();

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


        System.out.println("\nAll Students:");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.printf("Name: %s | ID: %d | Avg: %.2f%n",
                    names[i], studentIds[i], averages[i]);
        }


        System.out.printf("%nTopper: %s | Avg: %.2f%n", names[topperIndex], averages[topperIndex]);

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = scanner.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numberOfStudents - 1; i++) {
                for (int j = i + 1; j < numberOfStudents; j++) {
                    if (averages[i] < averages[j]) {
                        swap(names, i, j);
                        swap(studentIds, i, j);
                        swap(marks1, i, j);
                        swap(marks2, i, j);
                        swap(marks3, i, j);
                        swap(averages, i, j);
                    }
                }
            }

            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.printf("%s | Avg: %.2f%n", names[i], averages[i]);
            }
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < numberOfStudents; i++) {
                if (studentIds[i] == searchId) {
                    System.out.printf("Found: %s | Avg: %.2f%n", names[i], averages[i]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }
        }

        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = scanner.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < numberOfStudents; i++) {
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

        System.out.println("\nBye!");
        scanner.close();
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
