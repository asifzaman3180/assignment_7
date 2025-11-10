import java.util.*;

public class x {
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

        double topAverage = 0;
        int topIndex = 0;

        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            names[i] = scanner.next();

            System.out.print("ID: ");
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

        System.out.println("\nAll Students:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Name: " + names[i] + " | ID: " + ids[i] + " | Average: " + averages[i]);
        }

        System.out.println("\nTopper: " + names[topIndex] + " | Average: " + averages[topIndex]);

        System.out.print("\nSort by Average? (y/n): ");
        String sortChoice = scanner.next();

        if (sortChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = i + 1; j < studentCount; j++) {
                    if (averages[i] < averages[j]) {
                        // Swap all details
                        String tempName = names[i];
                        names[i] = names[j];
                        names[j] = tempName;

                        int tempId = ids[i];
                        ids[i] = ids[j];
                        ids[j] = tempId;

                        double tempAvg = averages[i];
                        averages[i] = averages[j];
                        averages[j] = tempAvg;
                    }
                }
            }

            System.out.println("\nSorted List:");
            for (int i = 0; i < studentCount; i++) {
                System.out.println(names[i] + " | Average: " + averages[i]);
            }
        }

        System.out.print("\nSearch student by ID? (y/n): ");
        String searchChoice = scanner.next();

        if (searchChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter ID: ");
            int searchId = scanner.nextInt();
            boolean found = false;

            for (int i = 0; i < studentCount; i++) {
                if (ids[i] == searchId) {
                    System.out.println("Found: " + names[i] + " | Average: " + averages[i]);
                    found = true;
                }
            }

            if (!found) System.out.println("Not found!");
        }

        System.out.print("\nCalculate grade? (y/n): ");
        String gradeChoice = scanner.next();

        if (gradeChoice.equalsIgnoreCase("y")) {
            for (int i = 0; i < studentCount; i++) {
                String grade;
                double avg = averages[i];

                if (avg >= 80) grade = "A+";
                else if (avg >= 70) grade = "A";
                else if (avg >= 60) grade = "B";
                else if (avg >= 50) grade = "C";
                else grade = "F";

                System.out.println(names[i] + " | Grade: " + grade);
            }
        }

        System.out.println("\nBye!");
        scanner.close();
    }
}
