import java.util.*;

public class StudentManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int studentCount = scanner.nextInt();

        String[] names = new String[studentCount];
        int[] ids = new int[studentCount];
        int[] subject1 = new int[studentCount];
        int[] subject2 = new int[studentCount];
        int[] subject3 = new int[studentCount];
        double[] averages = new double[studentCount];

        double highestAverage = 0;
        int topperIndex = 0;

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

            averages[i] = (subject1[i] + subject2[i] + subject3[i]) / 3.0;

            if (averages[i] > highestAverage) {
                highestAverage = averages[i];
                topperIndex = i;
            }
        }

        // Display all students
        System.out.println("\nAll Students:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Average: " + averages[i]);
        }

        System.out.println("\nTopper: " + names[topperIndex] + " | Average: " + averages[topperIndex]);

        // Sort by average
        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = scanner.next();
        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = i + 1; j < studentCount; j++) {
                    if (averages[i] < averages[j]) {
                        // Swap all associated data
                        String tempName = names[i];
                        names[i] = names[j];
                        names[j] = tempName;

                        int tempId = ids[i];
                        ids[i] = ids[j];
                        ids[j] = tempId;

                        int tempSub1 = subject1[i];
                        subject1[i] = subject1[j];
                        subject1[j] = tempSub1;

                        int tempSub2 = subject2[i];
                        subject2[i] = subject2[j];
                        subject2[j] = tempSub2;

                        int tempSub3 = subject3[i];
                        subject3[i] = subject3[j];
                        subject3[j] = tempSub3;

                        double tempAvg = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List (by Average):");
            for (int i = 0; i < studentCount; i++) {
                System.out.println(names[i] + " | Average: " + averages[i]);
            }
        }

        // Search by ID
        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();
        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID to search: ");
            int searchId = scanner.nextInt();
            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
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

        // Calculate grades
        System.out.print("\nCalculate grades? (y/n): ");
        String gradeChoice = scanner.next();
        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < studentCount; i++) {
                String grade;
                if (averages[i] >= 80) grade = "A+";
                else if (averages[i] >= 70) grade = "A";
                else if (averages[i] >= 60) grade = "B";
                else if (averages[i] >= 50) grade = "C";
                else grade = "F";

                System.out.println(names[i] + " | Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
