import java.util.*;


public class StudentApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int totalStudents = input.nextInt();

        String[] names = new String[totalStudents];
        int[] ids = new int[totalStudents];
        int[] marks1 = new int[totalStudents];
        int[] marks2 = new int[totalStudents];
        int[] marks3 = new int[totalStudents];
        double[] averages = new double[totalStudents];

        double topAverage = 0;
        int topIndex = 0;

       
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Enter name: ");
            names[i] = input.next();
            System.out.print("Enter ID: ");
            ids[i] = input.nextInt();
            System.out.print("Enter marks of 3 subjects: ");
            marks1[i] = input.nextInt();
            marks2[i] = input.nextInt();
            marks3[i] = input.nextInt();

            
            averages[i] = (marks1[i] + marks2[i] + marks3[i]) / 3.0;

            
            if (averages[i] > topAverage) {
                topAverage = averages[i];
                topIndex = i;
            }
        }

        
        System.out.println("\nAll Students:");
        for (int i = 0; i < totalStudents; i++) {
            System.out.printf("Name: %s | ID: %d | Avg: %.2f%n", names[i], ids[i], averages[i]);
        }

        
        System.out.printf("%nTopper: %s | Avg: %.2f%n", names[topIndex], averages[topIndex]);

        
        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = input.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < totalStudents - 1; i++) {
                for (int j = i + 1; j < totalStudents; j++) {
                    if (averages[i] < averages[j]) {
                        // Swap all values
                        swap(names, i, j);
                        swap(ids, i, j);
                        swap(marks1, i, j);
                        swap(marks2, i, j);
                        swap(marks3, i, j);
                        swap(averages, i, j);
                    }
                }
            }

            System.out.println("\nSorted List by Average:");
            for (int i = 0; i < totalStudents; i++) {
                System.out.printf("%s | Avg: %.2f%n", names[i], averages[i]);
            }
        }

        
        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = input.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = input.nextInt();
            boolean found = false;

            for (int i = 0; i < totalStudents; i++) {
                if (ids[i] == searchId) {
                    System.out.printf("Found: %s | Avg: %.2f%n", names[i], averages[i]);
                    found = true;
                    break;
                }
            }

            if (!found) System.out.println("Student not found.");
        }

       
        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = input.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            System.out.println("\nGrades:");
            for (int i = 0; i < totalStudents; i++) {
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
        input.close();
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
